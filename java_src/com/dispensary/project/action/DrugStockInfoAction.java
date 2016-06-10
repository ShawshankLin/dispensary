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
import java.util.HashSet;
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
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class DrugStockInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugStockInfo/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugStockInfo/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugStockInfo/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugStockInfo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugStockInfo/show.jsp";
	protected static final String SHOW_DRUG_WARN_JSP = "/pages/DrugStockInfo/drugWarn.jsp";
	protected static final String CHANGE_DRUG_PLACE="/pages/DrugStockInfo/changeDrugPlace.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugStockInfo/list.do";
	
	private DrugStockInfoManager drugStockInfoManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private DrugPlaceManager drugPlaceManager;
	private DrugStockInfo drugStockInfo;
	java.lang.Integer id = null;
	private String[] items;
	

	public void prepare() throws Exception {
		drugStockInfo = new DrugStockInfo();
		/*if (isNullOrEmptyString(id)) {
			drugStockInfo = new DrugStockInfo();
		} else {
			drugStockInfo = (DrugStockInfo)drugStockInfoManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugStockInfoManager(DrugStockInfoManager manager) {
		this.drugStockInfoManager = manager;
	}	
	
	public Object getModel() {
		return drugStockInfo;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugStockInfoQuery query = newQuery(DrugStockInfoQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugStockInfoManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		DrugStockInfoQuery stockQuery=new DrugStockInfoQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("drugId".equals(searchType)){
				stockQuery.setDrugId(Integer.parseInt(searchInfo));
			}
			if("drugName".equals(searchType)){
				stockQuery.setDrugIdModelTag(searchInfo);
			}
			if("drugPlaceId".equals(searchType)){
				stockQuery.setDrugPlaceId(Integer.parseInt(searchInfo));
			}
		}
		Page page = drugStockInfoManager.findPage(stockQuery);
		savePage(page,stockQuery);
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
		drugStockInfoManager.save(drugStockInfo);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String id=getRequest().getParameter("id");
		if(id!=null){
			DrugStockInfo drugStockInfo=drugStockInfoManager.getById(Integer.parseInt(id));
			getRequest().setAttribute("drugStockInfo", drugStockInfo);
		}
		return CHANGE_DRUG_PLACE;
	}
	
	/**保存更新对象*/
	public String update() {
		drugStockInfoManager.update(this.drugStockInfo);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			drugStockInfoManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			drugStockInfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<DrugBasicInfo> drugBasicInfoList =  drugBasicInfoManager.findAll();
		requestMap.put("drugBasicInfoList",drugBasicInfoList);
		List<DrugPlace> drugPlaceList =  drugPlaceManager.findAll();
		requestMap.put("drugPlaceList",drugPlaceList);
	}
	/**更改药品库存位置*/
	public String changeDrugPlace(){
		String changeDrugPlaceId=getRequest().getParameter("changeDrugPlaceId");
		if(changeDrugPlaceId!=null){
			DrugStockInfo dsi=drugStockInfoManager.getById(drugStockInfo.getId());
			if(dsi.getCurAmount()-drugStockInfo.getCurAmount()<0){
				showMessage("查过当前位置药品已有数量!");
			}else{
				dsi.setCurAmount(dsi.getCurAmount()-drugStockInfo.getCurAmount());
				drugStockInfoManager.saveOrUpdate(dsi);
			}
			DrugStockInfoQuery query=new DrugStockInfoQuery();
			query.setDrugId(drugStockInfo.getDrugId());
			query.setDrugPlaceId(Integer.parseInt(changeDrugPlaceId));
			List<DrugStockInfo> drugStockInfoList=drugStockInfoManager.findAll(query);
			DrugStockInfo dsi2=null;
			if(drugStockInfoList.size()==1){
				dsi2=drugStockInfoList.get(0);
				drugStockInfoManager.saveOrUpdate(dsi2);
			}else {
				dsi2=new DrugStockInfo();
				dsi2.setDrugId(drugStockInfo.getDrugId());
				dsi2.setDrugPlaceId(Integer.parseInt(changeDrugPlaceId));
				dsi2.setCurAmount(drugStockInfo.getCurAmount());
				drugStockInfoManager.save(dsi2);
			}
		}
		return LIST_ACTION;	
	}
	/** 显示药品有效期 */
	public String showDrugWarn() {
		Page page = drugStockInfoManager.findExpiredDrugs(1,1);
		getRequest().setAttribute("page", page);
		return SHOW_DRUG_WARN_JSP;
		/*DrugStockInfoQuery query = newQuery(DrugStockInfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = drugStockInfoManager.findPage(query);
		savePage(page,query);
		return SHOW_DRUG_WARN_JSP;*/
	}
	
	/* 获取症状对应的药品 */
	public String getSymptomDrugsJSON() {
		String symptomId=getRequest().getParameter("symptomId");
		List<DrugStockInfo> drugStockList=drugStockInfoManager.findAll();
		List<DrugBasicInfo> drugList=new ArrayList<DrugBasicInfo>();
		for(int i=0;i<drugStockList.size();i++){
			DrugStockInfo drugStockInfo=drugStockList.get(i);
			int drugId=drugStockInfo.getDrugId();
			DrugBasicInfoQuery drugQuery=new DrugBasicInfoQuery();
			drugQuery.setDrugId(drugId);
			if(symptomId!=null){
				drugQuery.setSymptomId(Integer.parseInt(symptomId));
			}
			List<DrugBasicInfo> drug=drugBasicInfoManager.findAll(drugQuery);
			if(drug.size()==1){
				drugList.add(drug.get(0));
			}
		}
		JSONArray jsonArray = new JSONArray();		
		for (int i = 0; i < drugList.size(); i++) {
			DrugBasicInfo drugBasicInfo = drugList.get(i);
			DrugBasicInfoQuery query = new DrugBasicInfoQuery();
			BeanUtils.copyProperties(query, drugBasicInfo);
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
	public void getStockReport() throws IOException {
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
		 try {
			f.createNewFile();
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
	  
	      //标题数组
		  String title[] = {DrugStockInfo.ALIAS_DRUG_ID,DrugStockInfo.ALIAS_DRUG_PLACE_ID,DrugStockInfo.ALIAS_CUR_AMOUNT,};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<DrugStockInfo> kucunList = drugStockInfoManager.findAll();
		  Iterator it = kucunList.iterator();
		  while (it.hasNext()) {
			  DrugStockInfo kucun =(DrugStockInfo) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(kucun.getDrugIdModel().getModelTagValue()+"");
			  list.add(kucun.getDrugPlaceIdModel().getModelTagValue()+"");
			  list.add(kucun.getCurAmount()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	  try {
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }	
	}
}
