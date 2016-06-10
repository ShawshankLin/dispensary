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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.dispatcher.RequestMap;

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
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class SymptomAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Symptom/query.jsp";
	protected static final String LIST_JSP= "/pages/Symptom/list.jsp";
	protected static final String CREATE_JSP = "/pages/Symptom/create.jsp";
	protected static final String EDIT_JSP = "/pages/Symptom/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Symptom/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Symptom/list.do";
	
	private SymptomManager symptomManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private DrugSymptomRelationManager drugSymptomRelationManager;
	private Symptom symptom;
	java.lang.Integer id = null;
	private String[] items;

	//用于接收症状药品
	private String drugs;

	public String getDrugs() {
		return drugs;
	}

	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}

	public void prepare() throws Exception {
		symptom = new Symptom();
		/*if (isNullOrEmptyString(id)) {
			symptom = new Symptom();
		} else {
			symptom = (Symptom) symptomManager.getById(id);
		}*/
	}

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setSymptomManager(SymptomManager manager) {
		this.symptomManager = manager;
	}	
	
	public Object getModel() {
		return symptom;
	}
	
	public void setSymptomId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		SymptomQuery query = newQuery(SymptomQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = symptomManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		SymptomQuery symptomQuery=new SymptomQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("symptomId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					symptomQuery.setSymptomId(Integer.parseInt(searchInfo));
				}
			}
			if("symptomName".equals(searchType)){
				symptomQuery.setSymptomName(searchInfo);
			}
		}
		Page page = symptomManager.findPage(symptomQuery);
		savePage(page,symptomQuery);
		return LIST_JSP;
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
		symptomManager.save(symptom);
		//添加症状药品
		if(drugs!=null){
			String[] drug=drugs.replace(" ","").split(",");
			for(int i=0;i<drug.length;i++){
				DrugSymptomRelation dsr=new DrugSymptomRelation();
				dsr.setSymptomId(symptom.getSymptomId());
				System.out.println(drug[i]);
				dsr.setDrugId(Integer.valueOf(drug[i]));
				drugSymptomRelationManager.saveOrUpdate(dsr);
			}
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String symptomId=getRequest().getParameter("symptomId");
		if(symptomId!=null){
			Symptom symptom=symptomManager.getById(Integer.parseInt(symptomId));
			getRequest().setAttribute("symptom", symptom);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		symptomManager.update(this.symptom);
		//添加症状药品
		if(drugs!=null){
			DrugSymptomRelationQuery dsrQuery=new DrugSymptomRelationQuery();
			dsrQuery.setSymptomId(symptom.getSymptomId());
			List<DrugSymptomRelation> dsrList=drugSymptomRelationManager.findAll(dsrQuery);
			for(int i=0;i<dsrList.size();i++){
				DrugSymptomRelation dsr=dsrList.get(i);
				drugSymptomRelationManager.removeById(dsr.getId());
			}
			String[] drug=drugs.replace(" ","").split(",");
			for(int i=0;i<drug.length;i++){
				DrugSymptomRelation dsr=new DrugSymptomRelation();
				dsr.setSymptomId(symptom.getSymptomId());
				System.out.println(drug[i]);
				dsr.setDrugId(Integer.valueOf(drug[i]));
				drugSymptomRelationManager.saveOrUpdate(dsr);
			}
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String symptomId=getRequest().getParameter("symptomId");
		if(symptomId!=null){
			symptomManager.removeById(Integer.parseInt(symptomId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("symptomId"));
			symptomManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<DrugBasicInfo> drugList = drugBasicInfoManager.findAll();
		requestMap.put("drugList",drugList);
	}


	/** 获取所有症状信息 */
	public String getSymptomInfos() {
		SymptomQuery symptomQuery=new SymptomQuery();
		symptomQuery.setSymptomName(symptom.getSymptomName());
		List<Symptom> list = symptomManager.findAll(symptomQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Symptom symptom = list.get(i);
			SymptomQuery query = new SymptomQuery();
			BeanUtils.copyProperties(query, symptom);
			JSONObject jsonobj = JSONObject.fromObject(query);
			jsonArray.add(jsonobj);
		}
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			//System.out.println(jsonArray.toString());
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 执行查症状信息 */
	public String getSymptom() {	
		SymptomQuery symptomQuery = new SymptomQuery();
		symptomQuery.setSymptomId(symptom.getSymptomId());
		List<Symptom> list = symptomManager.findAll(symptomQuery);
		Symptom symptom = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			symptom = (Symptom)list.get(0);
			SymptomQuery query=new SymptomQuery();
			BeanUtils.copyProperties(query, symptom);
			JSONObject jsonobj = JSONObject.fromObject(query);
			out.write(jsonobj.toString());
		}else{
			out.println("<script type='text/javascript'>alert('无此症状信息!');</script>");
		}
		return null;
	}
	public void getSymptomReport() throws IOException {
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
		  String title[] = {Symptom.ALIAS_SYMPTOM_ID,Symptom.ALIAS_SYMPTOM_NAME};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Symptom> symptomList = symptomManager.findAll();
		  Iterator it = symptomList.iterator();
		  while (it.hasNext()) {
			  Symptom symptom =(Symptom) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(symptom.getSymptomId()+"");
			  list.add(symptom.getSymptomName()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	 
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Symptom/list.do");
	  }	
	}

}
