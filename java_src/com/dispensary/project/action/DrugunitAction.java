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

public class DrugunitAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "drugUnitId asc"; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Drugunit/query.jsp";
	protected static final String LIST_JSP= "/pages/Drugunit/list.jsp";
	protected static final String CREATE_JSP = "/pages/Drugunit/create.jsp";
	protected static final String EDIT_JSP = "/pages/Drugunit/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Drugunit/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Drugunit/list.do";
	
	private DrugunitManager drugunitManager;
	private Drugunit drugunit;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		drugunit = new Drugunit();
		/*if (isNullOrEmptyString(id)) {
			drugunit = new Drugunit();
		} else {
			drugunit = (Drugunit)drugunitManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugunitManager(DrugunitManager manager) {
		this.drugunitManager = manager;
	}	
	
	public Object getModel() {
		return drugunit;
	}
	
	public void setDrugUnitId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugunitQuery query = newQuery(DrugunitQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugunitManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		DrugunitQuery unitQuery=new DrugunitQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("drugUnitId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					unitQuery.setDrugUnitId((Integer.parseInt(searchInfo)));
				}
			}
			if("drugUnitName".equals(searchType)){
				unitQuery.setDrugUnitName(searchInfo);
			}
		}
		Page page = drugunitManager.findPage(unitQuery);
		savePage(page,unitQuery);
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
		drugunitManager.save(drugunit);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String drugUnitId=getRequest().getParameter("drugUnitId");
		if(drugUnitId!=null){
			Drugunit unit=drugunitManager.getById(Integer.parseInt(drugUnitId));
			getRequest().setAttribute("unit", unit);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		drugunitManager.update(this.drugunit);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String drugUnitId=getRequest().getParameter("drugUnitId");
		if(drugUnitId!=null){
			drugunitManager.removeById(Integer.parseInt(drugUnitId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("drugUnitId"));
			drugunitManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	
	}
	/* 查找当个药品单位json */
	public String getDrugunitJSON() {	
		DrugunitQuery drugunitQuery = new DrugunitQuery();
		drugunitQuery.setDrugUnitId(drugunit.getDrugUnitId());
		List<Drugunit> list = drugunitManager.findAll(drugunitQuery);
		Drugunit drugunit = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			if (list.size() == 1) {
				drugunit = (Drugunit)list.get(0);
				DrugunitQuery query=new DrugunitQuery();
				BeanUtils.copyProperties(query, drugunit);
				JSONObject jsonobj = JSONObject.fromObject(query);
				out.write(jsonobj.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/** 查找所有药品单位json */
	public String getDrugunitsJSON() {
		List<Drugunit> list = drugunitManager.findAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Drugunit drugunit = list.get(i);
			DrugunitQuery query = new DrugunitQuery();
			BeanUtils.copyProperties(query, drugunit);
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
	public void getUnitReport() throws IOException {
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
		  String title[] = {Drugunit.ALIAS_DRUG_UNIT_ID,Drugunit.ALIAS_DRUG_UNIT_NAME};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Drugunit> unitList = drugunitManager.findAll();
		  Iterator it = unitList.iterator();
		  while (it.hasNext()) {
			  Drugunit unit =(Drugunit) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(unit.getDrugUnitId()+"");
			  list.add(unit.getDrugUnitName()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	 
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Drugunit/list.do");
	  }	
	}
}
