/*
 */

package com.dispensary.project.action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;
import java.util.Map.Entry;

import javacommon.base.*;
import javacommon.excel.Excel;
import javacommon.excel.Files;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;

import com.dispensary.project.model.*;
import com.dispensary.project.service.*;
import com.dispensary.project.utils.Application;
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.vo.query.*;


public class DispensarystaffAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Dispensarystaff/query.jsp";
	protected static final String LIST_JSP= "/pages/Dispensarystaff/list.jsp";
	protected static final String CREATE_JSP = "/pages/Dispensarystaff/create.jsp";
	protected static final String EDIT_JSP = "/pages/Dispensarystaff/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Dispensarystaff/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Dispensarystaff/list.do";
	
	private DispensarystaffManager dispensarystaffManager;
	private Dispensarystaff dispensarystaff;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		dispensarystaff = new Dispensarystaff();
		/*if (isNullOrEmptyString(id)) {
			dispensarystaff = new Dispensarystaff();
		} else {
			dispensarystaff = (Dispensarystaff)dispensarystaffManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDispensarystaffManager(DispensarystaffManager manager) {
		this.dispensarystaffManager = manager;
	}	
	
	public Object getModel() {
		return dispensarystaff;
	}
	
	public void setMeStId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行显示 */
	public String list() {
		DispensarystaffQuery query = newQuery(DispensarystaffQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = dispensarystaffManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		DispensarystaffQuery staffQuery=new DispensarystaffQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("meStId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					staffQuery.setMeStId((Integer.parseInt(searchInfo)));
				}
			}
			if("meStName".equals(searchType)){
				staffQuery.setMeStName(searchInfo);
			}
		}
		Page page = dispensarystaffManager.findPage(staffQuery);
		savePage(page,staffQuery);
		return LIST_JSP;
		
	}
	/**查找医务人员是否存在信息*/
	public String getStaffJSON(){
		DispensarystaffQuery staffQuery=new DispensarystaffQuery();
		staffQuery.setMeStId(dispensarystaff.getMeStId());
		List<Dispensarystaff> staffs=dispensarystaffManager.findAll(staffQuery);
		Dispensarystaff staff=null;
		DispensarystaffQuery query=null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			if(staffs.size()==1){
				staff=staffs.get(0);
				query=new DispensarystaffQuery();
				BeanUtils.copyProperties(query, staff);
				JSONObject jsonobj = JSONObject.fromObject(query);
				out.write(jsonobj.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/** 查询所有医务人员信息 */
	public String getStaffInfos(){
		DispensarystaffQuery dispensarystaffQuery=new DispensarystaffQuery();
		dispensarystaffQuery.setMeStId(dispensarystaff.getMeStId());
		dispensarystaffQuery.setMeStName(dispensarystaff.getMeStName());
		List<Dispensarystaff> list = dispensarystaffManager.findAll(dispensarystaffQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Dispensarystaff dispensarystaff = list.get(i);
			DispensarystaffQuery query = new DispensarystaffQuery();
			BeanUtils.copyProperties(query, dispensarystaff);
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
		dispensarystaffManager.save(dispensarystaff);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String meStId=getRequest().getParameter("meStId");
		DispensarystaffQuery staffQuery=new DispensarystaffQuery();
		staffQuery.setMeStId(Integer.parseInt(meStId));
		List<Dispensarystaff> list=dispensarystaffManager.findAll(staffQuery);
		Dispensarystaff staff=new Dispensarystaff();
		for(int i=0;i<list.size();i++){
			staff=list.get(0);
		}
		getRequest().setAttribute("staff", staff);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		dispensarystaffManager.update(this.dispensarystaff);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String meStId=getRequest().getParameter("meStId");
		dispensarystaffManager.removeById(Integer.parseInt(meStId));
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("meStId"));
			dispensarystaffManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	}
	//查询当前在线医务人员
	public String getOnlineUser(){
		ServletContext servletContext=getRequest().getServletContext();
		if(servletContext!=null){
			Application application=(Application) servletContext.getAttribute("Application");
			if(application!=null){
				Map<Integer, Userinfo> onlineMap=application.getOnlineUser();
				Iterator iterator=onlineMap.entrySet().iterator();
				List<Dispensarystaff> staffs=new ArrayList<Dispensarystaff>();
				while(iterator.hasNext()){
					Entry entry = (Entry)iterator.next();
					Dispensarystaff staff=dispensarystaffManager.getById(((Userinfo)entry.getValue()).getMeStId());
					staffs.add(staff);
				}
				Page page=new Page(1, 1, staffs.size(), staffs);
				getRequest().setAttribute("page", page);
			}
		}
		return LIST_JSP;
	}
	//IPO生成报表
	public void getStaffReport() throws IOException {
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
		  String title[] = {Dispensarystaff.ALIAS_ME_ST_ID, Dispensarystaff.ALIAS_ME_ST_NAME,
					Dispensarystaff.ALIAS_SEX, Dispensarystaff.ALIAS_AGE,
					Dispensarystaff.ALIAS_EDUCATION, Dispensarystaff.ALIAS_ADDRESS,
					Dispensarystaff.ALIAS_TEL, Dispensarystaff.ALIAS_MOBILE,
					Dispensarystaff.ALIAS_EMAIL, Dispensarystaff.ALIAS_WORK_PLACE,Dispensarystaff.ALIAS_NOTE};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Dispensarystaff> staffList = dispensarystaffManager.findAll();
		  Iterator it = staffList.iterator();
		  while (it.hasNext()) {
			  Dispensarystaff staff =(Dispensarystaff) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(staff.getMeStId()+"");
			  list.add(staff.getMeStName()+"");
			  if(staff.getSex()==1){
				  list.add("男");
			  }else{
				  list.add("女");
			  }
			  list.add(staff.getAge()+"");
			  list.add(staff.getEducation()+"");
			  list.add(staff.getAddress()+"");
			  list.add(staff.getTel()+"");
			  list.add(staff.getMobile()+"");
			  list.add(staff.getEmail()+"");
			  list.add(staff.getWorkPlace()+"");
			  list.add(staff.getNote()+"");
			  lists.add(list);
		}
		  System.out.println(lists);
	  // 生成excel文件(保存在服务器机上)
	  
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Dispensarystaff/list.do");
	  }	
	}

}
