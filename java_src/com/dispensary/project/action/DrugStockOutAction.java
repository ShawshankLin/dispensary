/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

public class DrugStockOutAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugStockOut/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugStockOut/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugStockOut/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugStockOut/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugStockOut/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugStockOut/list.do";
	
	private DrugStockOutManager drugStockOutManager;
	@Resource private DispensarystaffManager dispensarystaffManager;
	@Resource private DrugStockOutDetailManager drugStockOutDetailManager;
	private DrugStockOut drugStockOut;
	java.lang.Integer id = null;
	private String[] items;
	private Set<DrugStockOutDetail> drugStockOutDetails=new HashSet<DrugStockOutDetail>();
	public Set<DrugStockOutDetail> getDrugStockOutDetails() {
		return drugStockOutDetails;
	}

	public void setDrugStockOutDetails(Set<DrugStockOutDetail> drugStockOutDetails) {
		this.drugStockOutDetails = drugStockOutDetails;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			drugStockOut = new DrugStockOut();
		} else {
			drugStockOut = (DrugStockOut)drugStockOutManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugStockOutManager(DrugStockOutManager manager) {
		this.drugStockOutManager = manager;
	}	
	
	public Object getModel() {
		return drugStockOut;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugStockOutQuery query = newQuery(DrugStockOutQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugStockOutManager.findPage(query);
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
		drugStockOutManager.save(drugStockOut);
		Iterator<DrugStockOutDetail> iterator=drugStockOutDetails.iterator();
		while(iterator.hasNext()){
			DrugStockOutDetail detail=iterator.next();
			detail.setSerialNumber(drugStockOut.getSerialNumber());
			drugStockOutDetailManager.save(detail);
		}
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
		drugStockOutManager.update(this.drugStockOut);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			drugStockOutManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Dispensarystaff> dispensarystaffList =  dispensarystaffManager.findAll();
		requestMap.put("dispensarystaffList",dispensarystaffList);
	}
	/** 生成新的出库单号 */
	public String getStockOutId(){
		String stockOutId=drugStockOutManager.getStockOutId();
		System.out.println(stockOutId);
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			out.println(stockOutId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/** 生成新的流水号 */
	public String getSerialNum(){
		String id=getRequest().getParameter("stockOutId");
		String stockOutId=drugStockOutManager.getSerialNum(id);
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			out.println(stockOutId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/** 查找出库单信息*/
	public String getOutOrderInfos() {
		DrugStockOutQuery drugStockOutQuery=new DrugStockOutQuery();
		if(drugStockOut.getStockOutId()!=null){
			drugStockOutQuery.setStockOutId(drugStockOut.getStockOutId());
		}
		if(drugStockOut.getSerialNumber()!=null){
			drugStockOutQuery.setSerialNumber(drugStockOut.getSerialNumber());
		}
		List<DrugStockOut> list = drugStockOutManager.findAll(drugStockOutQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			DrugStockOut drugStockOut = list.get(i);
			DrugStockOutQuery query = new DrugStockOutQuery();
			BeanUtils.copyProperties(query, drugStockOut);
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
}
