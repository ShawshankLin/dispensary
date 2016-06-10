/*
 */

package com.dispensary.project.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.RequestMap;
import org.hibernate.Query;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.excel.Excel;
import javacommon.excel.Files;
import javacommon.util.*;
import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.dispensary.project.model.*;
import com.dispensary.project.dao.*;
import com.dispensary.project.service.*;
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;
import javax.interceptor.Interceptors;


public class StudentAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Student/query.jsp";
	protected static final String LIST_JSP= "/pages/Student/list.jsp";
	protected static final String CREATE_JSP = "/pages/Student/create.jsp";
	protected static final String EDIT_JSP = "/pages/Student/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Student/show.jsp";
	protected static final String VISIT_JSP = "/pages/Treatment/visit.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Student/list.do";
	
	private StudentManager studentManager;
	private Student student;
	java.lang.String id = null;
	private String[] items;

	
	//获得上传文件
	private File upload;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	//获得上传文件名
	private String uploadFileName;

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void prepare() throws Exception {
		student = new Student();
		/*if (isNullOrEmptyString(id)) {
			student = new Student();
		} else {
			student = (Student) studentManager.getById(id);
		}*/
	}

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setStudentManager(StudentManager manager) {
		this.studentManager = manager;
	}	
	
	public Object getModel() {
		return student;
	}
	
	public void setStuNum(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行显示 */
	public String list() {
		StudentQuery query = newQuery(StudentQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = studentManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		StudentQuery stuQuery=new StudentQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("stuNum".equals(searchType)){
				stuQuery.setStuNum(searchInfo);
			}
			if("stuName".equals(searchType)){
				stuQuery.setStuName(searchInfo);
			}
			if("idcard".equals(searchType)){
				stuQuery.setIdcard(searchInfo);
			}
		}
		Page page = studentManager.findPage(stuQuery);
		savePage(page,stuQuery);
		return LIST_JSP;
	}
	/** 就诊搜索*/
	public String visitSearch(){
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("stuNum".equals(searchType)){
				Student student=studentManager.getById(searchInfo);
				if(student!=null){
					getRequest().setAttribute("stu", student);
					//Set<PatiCaseHistory> cases=student.getPatiCaseHistorys();
					//requestMap.put("cases", cases);
				}
			}
		}
		return VISIT_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		getRelative();
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		studentManager.save(student);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String stuNum=getRequest().getParameter("stuNum");
		if(stuNum!=null){
			Student stu=studentManager.getById(stuNum);
			getRequest().setAttribute("stu", stu);
		}	
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		studentManager.update(this.student);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String stuNum=getRequest().getParameter("stuNum");
		studentManager.removeById(stuNum);
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("stuNum"));
			studentManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	}

	/* 执行ajax查找单个学生信息 */
	public String searchStu() {	
		Student stu=studentManager.getById(student.getStuNum());
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			if (stu!=null) {
				StudentQuery query=new StudentQuery();
				BeanUtils.copyProperties(query, stu);
				JSONObject jsonobj = JSONObject.fromObject(query);
				System.out.println(jsonobj.toString());
				out.write(jsonobj.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/** 查询所有学生信息 */
	public String getStuJSON(){
		StudentQuery stuQuery=new StudentQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("stuNum".equals(searchType)){
				stuQuery.setStuNum(searchInfo);
			}
			if("stuName".equals(searchType)){
				stuQuery.setStuName(searchInfo);
			}
			if("idcard".equals(searchType)){
				stuQuery.setIdcard(searchInfo);
			}
		}
		List<Student> list = studentManager.findAll(stuQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Student student = list.get(i);
			StudentQuery query = new StudentQuery();
			BeanUtils.copyProperties(query, student);
			JSONObject jsonobj = JSONObject.fromObject(query);
			jsonArray.add(jsonobj);
		}
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//下载学生报表
	/*public void getStuReport(){
		String filename=Student.TABLE_ALIAS+"报表";
		String sheetname=Student.TABLE_ALIAS+"报表";
		String[] title={Student.ALIAS_STU_NUM,Student.ALIAS_STU_NAME,Student.ALIAS_SEX,
				Student.ALIAS_BIRTH_DATE,Student.ALIAS_IDCARD,Student.ALIAS_TEL,
				Student.ALIAS_DEPARTMENT,Student.ALIAS_MAJOR,Student.ALIAS_STU_CLASS,Student.ALIAS_ADDRESS};
		String path=getRequest().getServletContext().getRealPath("")+"\\download\\";
		List<Student> list = studentManager.findAll();
		new JexcelDownload().writeStuExc(filename,sheetname,title,list,path);
		HttpServletResponse response=getResponse();
		response.setContentType("application/octet-stream;charset=UTF-8");
		String ecoding=null;
		try {
			ecoding=URLEncoder.encode(filename,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //下载  
        response.setHeader("Content-disposition", "attachment;filename="+ecoding+".xls"); //文件名，在这下载了是乱码  
        BufferedInputStream bis=null; 
        ServletOutputStream sos=null;
        BufferedOutputStream bos=null;
		try {
			bis = new BufferedInputStream(new FileInputStream(path+filename+".xls"));
			sos = response.getOutputStream();
			bos= new BufferedOutputStream(sos);
			byte[] buffer = new byte[1024];   
	        int len = -1;   
	        while ((len = bis.read(buffer)) != -1) {   
	            bos.write(buffer, 0, len);   
	        }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bos.close();
				sos.close();   
		        bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        
		}
	}*/
	public void getStuReport() throws IOException {
		  try {
			  // 创建当前日子
	     Date date = new Date();
	     // 格式化日期 
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	     // 格式化日期(产生文件名)
	     String filename = sdf.format(date);
	     // 获得ServletContext对象
	     ServletContext servletContext = getRequest().getServletContext();
	     String realpath=servletContext.getRealPath("download") + "\\" + filename+ ".xls";
	     System.out.println(realpath);
	     // 创建文件
	     File f = new File(realpath);
	     f.getParentFile().mkdir();
		 try {
			f.createNewFile();
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
	  
	      //标题数组
		  String title[] = {Student.ALIAS_STU_NUM,Student.ALIAS_STU_NAME,Student.ALIAS_SEX,
					Student.ALIAS_BIRTH_DATE,Student.ALIAS_IDCARD,Student.ALIAS_TEL,
					Student.ALIAS_DEPARTMENT,Student.ALIAS_MAJOR,Student.ALIAS_STU_CLASS,Student.ALIAS_ADDRESS};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Student> stuList = studentManager.findAll();
		  Iterator it = stuList.iterator();
		  while (it.hasNext()) {
			  Student stu =(Student) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(stu.getStuNum()+"");
			  list.add(stu.getStuName()+"");
			  if(stu.getSex()==1){
				  list.add("男");
			  }else{
				  list.add("女");
			  }
			  list.add(stu.getBirthDateString()+"");
			  list.add(stu.getIdcard()+"");
			  list.add(stu.getTel()+"");
			  list.add(stu.getDepartment()+"");
			  list.add(stu.getMajor()+"");
			  list.add(stu.getStuClass()+"");
			  list.add(stu.getAddress()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Student/list.do");
	  }	
	}
	//导出report
	public void exportReport(){
		//导入文件
		String savePath="temp\\excel\\";
		String tarFileName=this.uploadFile(this.upload, this.uploadFileName, savePath);       
	}
}
