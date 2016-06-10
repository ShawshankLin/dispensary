/*
 */

package com.dispensary.project.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import net.sf.json.JsonConfig;

import org.apache.poi.hdf.extractor.data.LST;
import org.apache.struts2.dispatcher.RequestMap;
import org.hibernate.Hibernate;
import org.hibernate.Session;

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
import com.dispensary.project.utils.FloatJsonValueProcessor;
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.vo.query.*;
import com.google.gson.Gson;

import javax.annotation.Resource;

public class DrugBasicInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugBasicInfo/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugBasicInfo/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugBasicInfo/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugBasicInfo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugBasicInfo/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugBasicInfo/list.do";
	
	private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private SymptomManager symptomManager;
	@Resource private DrugunitManager drugunitManager;
	@Resource private SupplierManager supplierManager;
	@Resource private DrugSymptomRelationManager drugSymptomRelationManager;
	private DrugBasicInfo drugBasicInfo;
	java.lang.Integer id = null;
	private String[] items;
	//接受多个症状
	private String symptoms;
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public void prepare() throws Exception {
		drugBasicInfo = new DrugBasicInfo();
		/*if (isNullOrEmptyString(id)) {
			drugBasicInfo = new DrugBasicInfo();
		} else {
			drugBasicInfo = (DrugBasicInfo)drugBasicInfoManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugBasicInfoManager(DrugBasicInfoManager manager) {
		this.drugBasicInfoManager = manager;
	}	
	
	public Object getModel() {
		return drugBasicInfo;
	}
	
	public void setDrugId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugBasicInfoQuery query = newQuery(DrugBasicInfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = drugBasicInfoManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	/**删除对象*/
	public String delete(){
		String drugId=getRequest().getParameter("drugId");
		drugBasicInfoManager.removeById(Integer.parseInt(drugId));
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/** 进入新增页面*/
	public String create() {
		getRelative();
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		drugBasicInfoManager.save(drugBasicInfo);
		//添加药品症状
		if(symptoms!=null){
			String[] symptom=symptoms.replace(" ","").split(",");
			for(int i=0;i<symptom.length;i++){
				DrugSymptomRelation dsr=new DrugSymptomRelation();
				dsr.setDrugId(drugBasicInfo.getDrugId());
				System.out.println(symptom[i]);
				dsr.setSymptomId(Integer.valueOf(symptom[i]));
				drugSymptomRelationManager.saveOrUpdate(dsr);
			}
		}
		showMessage("添加药品成功！");
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String drugId=getRequest().getParameter("drugId");
		if(drugId!=null){
			DrugBasicInfo drug=drugBasicInfoManager.getById(Integer.parseInt(drugId));
			getRequest().setAttribute("drug", drug);
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		drugBasicInfoManager.update(this.drugBasicInfo);
		//添加药品症状
		if(symptoms!=null){
			DrugSymptomRelationQuery dsrQuery=new DrugSymptomRelationQuery();
			dsrQuery.setDrugId(drugBasicInfo.getDrugId());
			List<DrugSymptomRelation> dsrList=drugSymptomRelationManager.findAll(dsrQuery);
			for(int i=0;i<dsrList.size();i++){
				DrugSymptomRelation dsr=dsrList.get(i);
				drugSymptomRelationManager.removeById(dsr.getId());
			}
			String[] symptom=symptoms.replace(" ","").split(",");
			for(int i=0;i<symptom.length;i++){
				DrugSymptomRelation dsr=new DrugSymptomRelation();
				dsr.setDrugId(drugBasicInfo.getDrugId());
				System.out.println(symptom[i]);
				dsr.setSymptomId(Integer.valueOf(symptom[i]));
				drugSymptomRelationManager.saveOrUpdate(dsr);
			}
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("drugId"));
			drugBasicInfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		DrugBasicInfoQuery drugQuery=new DrugBasicInfoQuery();
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
				if(FunctionLib.isNum(searchInfo)){
					drugQuery.setDrugId((Integer.parseInt(searchInfo)));
				}
			}
			if("drugName".equals(searchType)){
				drugQuery.setDrugName(searchInfo);
			}
			if("drugPingyin".equals(searchType)){
				drugQuery.setDrugPingyin(searchInfo);
			}
			if("supplierId".equals(searchType)){
				drugQuery.setSupplierIdModelTag(searchInfo);
			}
		}
		Page page = drugBasicInfoManager.findPage(drugQuery);
		savePage(page,drugQuery);
		return LIST_JSP;
		
	}
	public void getRelative(){
		List<Symptom> symptomList =  symptomManager.findAll();
		requestMap.put("symptomList",symptomList);
		List<Drugunit> drugunitList =  drugunitManager.findAll();
		requestMap.put("drugunitList",drugunitList);
		List<Supplier> supplierList =  supplierManager.findAll();
		requestMap.put("supplierList",supplierList);
	}
	
	
	/** 查找所有药品信息(或已过时)*/
	public String getDrugJSON() {
		DrugBasicInfoQuery drugQuery=new DrugBasicInfoQuery();
		//drugQuery.setDrugName(drugBasicInfo.getDrugName());
		if(drugBasicInfo.getDrugId()!=null){
			drugQuery.setDrugId(drugBasicInfo.getDrugId());
		}
		if(drugBasicInfo.getSupplierId()!=null){
			drugQuery.setSupplierId(drugBasicInfo.getSupplierId());
		}
		List<DrugBasicInfo> list = drugBasicInfoManager.findAll(drugQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			DrugBasicInfo drugBasicInfo = list.get(i);
			DrugBasicInfoQuery query = new DrugBasicInfoQuery();
			BeanUtils.copyProperties(query, drugBasicInfo);
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Float.class, new FloatJsonValueProcessor());  
			JSONObject jsonobj = JSONObject.fromObject(query,jsonConfig);
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
	
	//IPO生成报表
	public void getDrugsReport() throws IOException {
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
		  String title[] = {DrugBasicInfo.ALIAS_DRUG_ID, DrugBasicInfo.ALIAS_DRUG_NAME,
					DrugBasicInfo.ALIAS_DRUG_PINGYIN, DrugBasicInfo.ALIAS_DRUG_EFFECT,
					DrugBasicInfo.ALIAS_DRUG_KICK_BACK, DrugBasicInfo.ALIAS_DRUG_NOTE,
					DrugBasicInfo.ALIAS_QUANTITY_UNIT, DrugBasicInfo.ALIAS_DRUG_USAGE,
					DrugBasicInfo.ALIAS_DRUG_SPEC, DrugBasicInfo.ALIAS_DRUG_TABU,DrugBasicInfo.ALIAS_COST_PRICE,
					DrugBasicInfo.ALIAS_RETAIL_PRICE,DrugBasicInfo.ALIAS_PRODUCTION_DATE,DrugBasicInfo.ALIAS_PERIOD_OF_VALIDITY,
					DrugBasicInfo.ALIAS_UP_LIMIT,DrugBasicInfo.ALIAS_DOWN_LIMIT,DrugBasicInfo.ALIAS_UP_LIMIT1,
					DrugBasicInfo.ALIAS_DOWN_LIMIT1,DrugBasicInfo.ALIAS_SYMPTOM_ID,DrugBasicInfo.ALIAS_IS_DRUG,DrugBasicInfo.ALIAS_SUPPLIER_ID};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<DrugBasicInfo> drugList = drugBasicInfoManager.findAll();
		  Iterator it = drugList.iterator();
		  while (it.hasNext()) {
			  DrugBasicInfo drug =(DrugBasicInfo) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(drug.getDrugId()+"");
			  list.add(drug.getDrugName()+"");
			  list.add(drug.getDrugPingyin()+"");
			  list.add(drug.getDrugEffectModel().getModelTagValue()+"");
			  list.add(drug.getDrugKickBackModel().getModelTagValue()+"");
			  list.add(drug.getDrugNote()+"");
			  list.add(drug.getQuantityUnitModel().getModelTagValue()+"");
			  list.add(drug.getDrugUsage()+"");
			  list.add(drug.getDrugSpec()+"");
			  list.add(drug.getDrugTabu()+"");
			  list.add(drug.getCostPrice()+"");
			  list.add(drug.getRetailPrice()+"");
			  list.add(drug.getProductionDateString()+"");
			  list.add(drug.getPeriodOfValidity()+"");
			  list.add(drug.getUpLimit()+"");
			  list.add(drug.getDownLimit()+"");
			  list.add(drug.getUpLimit1()+"");
			  list.add(drug.getDownLimit1()+"");
			  //list.add(drug.getSymptomIdModel().getModelTagValue()+"");
			  if(drug.getIsDrug()==1){
				  list.add("是");
			  }else{
				  list.add("否");
			  }
			  
			  list.add(drug.getSupplierIdModel().getModelTagValue()+"");
			  lists.add(list);
		}
		  System.out.println(lists);
	  // 生成excel文件(保存在服务器机上)
	  
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/DrugBasicInfo/list.do");
	  }	
	}
	
	
}
