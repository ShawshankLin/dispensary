/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.vo.query.*;
import com.google.gson.Gson;

import javax.annotation.Resource;

public class FeetypeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Feetype/query.jsp";
	protected static final String LIST_JSP= "/pages/Feetype/list.jsp";
	protected static final String CREATE_JSP = "/pages/Feetype/create.jsp";
	protected static final String EDIT_JSP = "/pages/Feetype/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Feetype/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Feetype/list.do";
	
	private FeetypeManager feetypeManager;
	@Resource DrugBasicInfoManager drugBasicInfoManager;
	private Feetype feetype;
	java.lang.Integer id = null;
	private String[] items;
	
	//接收批量添加的费用类型-药品
	private String drugs;

	public String getDrugs() {
		return drugs;
	}

	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}

	public void prepare() throws Exception {
		feetype = new Feetype();
		/*if (isNullOrEmptyString(id)) {
			feetype = new Feetype();
		} else {
			feetype = (Feetype)feetypeManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setFeetypeManager(FeetypeManager manager) {
		this.feetypeManager = manager;
	}	
	
	public Object getModel() {
		return feetype;
	}
	
	public void setFeeTypeId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		FeetypeQuery query = newQuery(FeetypeQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = feetypeManager.findPage(query);
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
	/**
	  * TODO用一句话描述作用
	  * @return
	  */
	public String save() {
		feetypeManager.save(feetype);
		//添加费用类型药品
		if(drugs!=null){
			String[] drug=drugs.replace(" ","").split(",");
			for(int i=0;i<drug.length;i++){
				DrugBasicInfo drugBasicInfo=drugBasicInfoManager.getById(Integer.parseInt(drug[i]));
				drugBasicInfo.setFeeTypeId(feetype.getFeeTypeId());
				drugBasicInfoManager.update(drugBasicInfo);
			}
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String feeTypeId=getRequest().getParameter("feeTypeId");
		if(feeTypeId!=null){
			Feetype feetype=feetypeManager.getById(Integer.parseInt(feeTypeId));
			getRequest().setAttribute("feetype", feetype);
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		feetypeManager.update(this.feetype);
		//先删除药品的费用类型值
		DrugBasicInfoQuery query=new DrugBasicInfoQuery();
		query.setFeeTypeId(feetype.getFeeTypeId());
		List<DrugBasicInfo> list=drugBasicInfoManager.findAll(query);
		for(int i=0;i<list.size();i++){
			DrugBasicInfo drug=list.get(i);
			drug.setFeeTypeId(null);
			drugBasicInfoManager.update(drug);
		}
		if(drugs!=null){
			String[] drug=drugs.replace(" ","").split(",");
			for(int i=0;i<drug.length;i++){
				DrugBasicInfo drugBasicInfo=drugBasicInfoManager.getById(Integer.parseInt(drug[i]));
				drugBasicInfo.setFeeTypeId(feetype.getFeeTypeId());
				drugBasicInfoManager.update(drugBasicInfo);
			}
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String feeTypeId=getRequest().getParameter("feeTypeId");
		feetypeManager.removeById(Integer.parseInt(feeTypeId));
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("feeTypeId"));
			feetypeManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<DrugBasicInfo> drugList = drugBasicInfoManager.findAll();
		requestMap.put("drugList",drugList);
	}
	/** 执行搜索 */
	public String search(){
		FeetypeQuery feetypeQuery=new FeetypeQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("feeTypeId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					feetypeQuery.setFeeTypeId(Integer.parseInt(searchInfo));
				}
			}
			if("feeTypeName".equals(searchType)){
				feetypeQuery.setFeeTypeName(searchInfo);
			}
		}
		Page page = feetypeManager.findPage(feetypeQuery);
		savePage(page,feetypeQuery);
		return LIST_JSP;
	}
	public String getFeetypeDrugJSON(){
		DrugBasicInfoQuery query=new DrugBasicInfoQuery();
		int feeTypeId=feetype.getFeeTypeId();
		query.setFeeTypeId(feeTypeId);
		List<DrugBasicInfo> list=drugBasicInfoManager.findAll(query);
		List<DrugBasicInfoQuery> newList=new ArrayList<DrugBasicInfoQuery>();
		for (int i = 0; i < list.size(); i++) {
			DrugBasicInfo drug= list.get(i);
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
		return null;
	}
}
