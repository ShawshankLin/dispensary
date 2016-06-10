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

public class DrugPlaceAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugPlace/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugPlace/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugPlace/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugPlace/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugPlace/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugPlace/list.do";
	
	private DrugPlaceManager drugPlaceManager;
	private DrugPlace drugPlace;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		drugPlace = new DrugPlace();
		/*if (isNullOrEmptyString(id)) {
			drugPlace = new DrugPlace();
		} else {
			drugPlace = (DrugPlace)drugPlaceManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugPlaceManager(DrugPlaceManager manager) {
		this.drugPlaceManager = manager;
	}	
	
	public Object getModel() {
		return drugPlace;
	}
	
	public void setDrugPlaceId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugPlaceQuery query = newQuery(DrugPlaceQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugPlaceManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		DrugPlaceQuery placeQuery=new DrugPlaceQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("drugPlaceId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					placeQuery.setDrugPlaceId(Integer.parseInt(searchInfo));
				}
			}
			if("drugPlace".equals(searchType)){
				placeQuery.setDrugPlace(searchInfo);
			}
		}
		Page page = drugPlaceManager.findPage(placeQuery);
		savePage(page,placeQuery);
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
		drugPlaceManager.save(drugPlace);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String drugPlaceId=getRequest().getParameter("drugPlaceId");
		if(drugPlaceId!=null){
			DrugPlace place=drugPlaceManager.getById(Integer.parseInt(drugPlaceId));
			getRequest().setAttribute("place", place);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		drugPlaceManager.update(this.drugPlace);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String drugPlaceId=getRequest().getParameter("drugPlaceId");
		if(drugPlaceId!=null){
			drugPlaceManager.removeById(Integer.parseInt(drugPlaceId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("drugPlaceId"));
			drugPlaceManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	}

	/** 获取药品存放位置的状态空或未满，库房类型 */
	public String getDrugPlaceJSON(){
		List<DrugPlace> list = drugPlaceManager.findAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			DrugPlace drugPlace = list.get(i);
			DrugPlaceQuery query = new DrugPlaceQuery();
			BeanUtils.copyProperties(query, drugPlace);
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
	public void getPlaceReport() throws IOException {
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
		  String title[] = {DrugPlace.ALIAS_DRUG_PLACE_ID,DrugPlace.ALIAS_DRUG_PLACE,
					DrugPlace.ALIAS_STATUS,DrugPlace.ALIAS_PLACE_TYPE,DrugPlace.ALIAS_IS_STOREROOM};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<DrugPlace> userList = drugPlaceManager.findAll();
		  Iterator it = userList.iterator();
		  while (it.hasNext()) {
			  DrugPlace place =(DrugPlace) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(place.getDrugPlaceId()+"");
			  list.add(place.getDrugPlace()+"");
			  if(place.getStatus()==1){
				  list.add("空");
			  }else if(place.getStatus()==2){
				  list.add("未满");
			  }else {
				list.add("已满");
			  }
			  list.add(place.getPlaceType()+"");
			  if(place.getIsStoreroom()==1){
				  list.add("库房");
			  }else{
				  list.add("药房");
			  }
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	 
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/DrugPlace/list.do");
	  }	
	}
}
