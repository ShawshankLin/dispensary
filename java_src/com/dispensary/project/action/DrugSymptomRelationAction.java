/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.dispatcher.RequestMap;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;
import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.dispensary.project.model.*;
import com.dispensary.project.dao.*;
import com.dispensary.project.service.*;
import com.dispensary.project.utils.FloatJsonValueProcessor;
import com.dispensary.project.vo.query.*;
import com.google.gson.Gson;

import javax.annotation.Resource;

public class DrugSymptomRelationAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugSymptomRelation/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugSymptomRelation/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugSymptomRelation/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugSymptomRelation/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugSymptomRelation/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugSymptomRelation/list.do";
	
	private DrugSymptomRelationManager drugSymptomRelationManager;
	@Resource private SymptomManager symptomManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	private DrugSymptomRelation drugSymptomRelation;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			drugSymptomRelation = new DrugSymptomRelation();
		} else {
			drugSymptomRelation = (DrugSymptomRelation)drugSymptomRelationManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugSymptomRelationManager(DrugSymptomRelationManager manager) {
		this.drugSymptomRelationManager = manager;
	}	
	
	public Object getModel() {
		return drugSymptomRelation;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugSymptomRelationQuery query = newQuery(DrugSymptomRelationQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugSymptomRelationManager.findPage(query);
		savePage(page,query);
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
		drugSymptomRelationManager.save(drugSymptomRelation);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		drugSymptomRelationManager.update(this.drugSymptomRelation);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			drugSymptomRelationManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Symptom> symptomList =  symptomManager.findAll();
		requestMap.put("symptomList",symptomList);
		List<DrugBasicInfo> drugBasicInfoList =  drugBasicInfoManager.findAll();
		requestMap.put("drugBasicInfoList",drugBasicInfoList);
	}
	/**
	 * 得到症状-所以药品
	 * @return
	 */
	public String getSymptomDrugJSON(){		
		DrugSymptomRelationQuery query=new DrugSymptomRelationQuery();
		String symptomId=getRequest().getParameter("symptomId");
		if(null!=symptomId){
			query.setSymptomId(Integer.valueOf(symptomId));
			List<DrugSymptomRelation> list=drugSymptomRelationManager.findAll(query);
			JSONArray jsonArray = new JSONArray();
			List<DrugBasicInfoQuery> newList=new ArrayList<DrugBasicInfoQuery>();
			for (int i = 0; i < list.size(); i++) {
				DrugSymptomRelation dsr = list.get(i);
				DrugBasicInfo drug=drugBasicInfoManager.getById(dsr.getDrugId());
				DrugBasicInfoQuery drugQuery=new DrugBasicInfoQuery();
				BeanUtils.copyProperties(drugQuery, drug);
				newList.add(drugQuery);
			}
			Gson gson=new Gson();
			String gsonStr=gson.toJson(newList);
			System.out.println(gsonStr);
			PrintWriter out = null;
			try {
				out = getResponse().getWriter();
				//System.out.println(jsonArray.toString());
				out.println(gsonStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public String getOtherSymptomDrugJSON(){
		String symptomId=getRequest().getParameter("symptomId");
		if(symptomId!=null){
			List<DrugBasicInfoQuery> otherDrugList=new ArrayList<DrugBasicInfoQuery>();
			List<Integer> list=drugSymptomRelationManager.findAllExcept(Integer.parseInt(symptomId));
			for(int i=0;i<list.size();i++){
				int drugId=list.get(i);
				DrugBasicInfo drug=drugBasicInfoManager.getById(drugId);
				DrugBasicInfoQuery drugQuery=new DrugBasicInfoQuery();
				BeanUtils.copyProperties(drugQuery, drug);
				otherDrugList.add(drugQuery);
			}
			Gson gson=new Gson();
			String gsonStr=gson.toJson(otherDrugList);
			System.out.println(gsonStr);
			PrintWriter out = null;
			try {
				out = getResponse().getWriter();
				//System.out.println(jsonArray.toString());
				out.println(gsonStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 得到药品-所有症状
	 * @return
	 */
	public String getDrugSymptomJSON(){
		DrugSymptomRelationQuery dsrQuery=new DrugSymptomRelationQuery();
		String drugId=getRequest().getParameter("drugId");
		if(drugId!=null){
			dsrQuery.setDrugId(Integer.parseInt(drugId));
		}
		List<DrugSymptomRelation> list=drugSymptomRelationManager.findAll(dsrQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			DrugSymptomRelation dsr = list.get(i);
			DrugSymptomRelationQuery dsrq = new DrugSymptomRelationQuery();
			BeanUtils.copyProperties(dsrq, dsr);
			Symptom symptom=symptomManager.getById(dsr.getSymptomId());
			dsrq.setSymptomIdModelTag(symptom.getSymptomName());
			JSONObject jsonobj = JSONObject.fromObject(dsrq);
			jsonArray.add(jsonobj);
		}
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			System.out.println(jsonArray.toString());
			out.println(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
