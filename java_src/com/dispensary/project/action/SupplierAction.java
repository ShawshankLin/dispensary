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

public class SupplierAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Supplier/query.jsp";
	protected static final String LIST_JSP= "/pages/Supplier/list.jsp";
	protected static final String CREATE_JSP = "/pages/Supplier/create.jsp";
	protected static final String EDIT_JSP = "/pages/Supplier/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Supplier/show.jsp";
	protected static final String STOCK_IN_JSP = "/pages/DrugStockIn/create.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Supplier/list.do";

	private SupplierManager supplierManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private DrugPlaceManager drugPlaceManager;
	private Supplier supplier;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		supplier = new Supplier();
		/*if (isNullOrEmptyString(id)) {
			supplier = new Supplier();
		} else {
			supplier = (Supplier)supplierManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setSupplierManager(SupplierManager manager) {
		this.supplierManager = manager;
	}	
	
	public Object getModel() {
		return supplier;
	}
	
	public void setSupplierId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		SupplierQuery query = newQuery(SupplierQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = supplierManager.findPage(query);
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
		supplierManager.save(supplier);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}

	/**进入更新页面*/
	public String edit() {
		getRelative();
		String supplierId=getRequest().getParameter("supplierId");
		if(supplierId!=null){
			Supplier sup=supplierManager.getById(Integer.parseInt(supplierId));
			getRequest().setAttribute("sup", sup);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		supplierManager.update(this.supplier);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		SupplierQuery supQuery=new SupplierQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("supplierId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					supQuery.setSupplierId((Integer.parseInt(searchInfo)));
				}
			}
			if("supplierName".equals(searchType)){
				supQuery.setSupplierName(searchInfo);
			}
			if("pingyin".equals(searchType)){
				supQuery.setPingyin(searchInfo);
			}
		}
		Page page = supplierManager.findPage(supQuery);
		savePage(page,supQuery);
		return LIST_JSP;
		
	}
	/** 查询供应商信息及相关药品*/
	public String searchSup(){
		SupplierQuery supQuery=new SupplierQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("supplierId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					supQuery.setSupplierId((Integer.parseInt(searchInfo)));
				}
			}
			if("supplierName".equals(searchType)){
				supQuery.setSupplierName(searchInfo);
			}
			if("pingyin".equals(searchType)){
				supQuery.setPingyin(searchInfo);
			}
		}
		List<Supplier> supList=supplierManager.findAll(supQuery);
		if(supList.size()==1){
			//获得供应商信息
			Supplier sup=supList.get(0);
			getRequest().setAttribute("sup", sup);
		}
		return STOCK_IN_JSP;
	}
	/**删除对象*/
	public String delete(){
		String supplierId=getRequest().getParameter("supplierId");
		supplierManager.removeById(Integer.parseInt(supplierId));
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**批量删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("supplierId"));
			supplierManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	
	}
	/** 查找所有供应商信息*/
	public String getSupJSON() {
		SupplierQuery supQuery=new SupplierQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("supplierId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					supQuery.setSupplierId((Integer.parseInt(searchInfo)));
				}
			}
			if("supplierName".equals(searchType)){
				supQuery.setSupplierName(searchInfo);
			}
			if("pingyin".equals(searchType)){
				supQuery.setPingyin(searchInfo);
			}
		}
		List<Supplier> list = supplierManager.findAll(supQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Supplier sup = list.get(i);
			SupplierQuery query = new SupplierQuery();
			BeanUtils.copyProperties(query, sup);
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
	/**查找药品信息*/
	public String getSupplierInfo(){
		SupplierQuery supplierQuery=new SupplierQuery();
		supplierQuery.setSupplierId(supplier.getSupplierId());
		List<Supplier> suppliers=supplierManager.findAll(supplierQuery);
		Supplier supplier=null;
		SupplierQuery query=null;
		if(suppliers.size()==1){
			supplier=suppliers.get(0);
			query=new SupplierQuery();
			BeanUtils.copyProperties(query, supplier);
		}
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			JSONObject jsonobj = JSONObject.fromObject(query);
			out.write(jsonobj.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	//IPO生成excel
	public void getSupReport(){
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
		  String title[] = {Supplier.ALIAS_SUPPLIER_ID, Supplier.ALIAS_SUPPLIER_NAME,
					Supplier.ALIAS_CONTACTS, Supplier.ALIAS_PINGYIN,
					Supplier.ALIAS_ADDRESS, Supplier.ALIAS_USER_TEL,
					Supplier.ALIAS_USER_MOBILE, Supplier.ALIAS_USER_EMAIL,
					Supplier.ALIAS_NOTE};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Supplier> supList = supplierManager.findAll();
		  Iterator it = supList.iterator();
		  while (it.hasNext()) {
			  Supplier sup =(Supplier) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(sup.getSupplierId()+"");
			  list.add(sup.getSupplierName()+"");
			  list.add(sup.getContacts()+"");
			  list.add(sup.getPingyin()+"");
			  list.add(sup.getAddress()+"");
			  list.add(sup.getUserTel()+"");
			  list.add(sup.getUserMobile()+"");
			  list.add(sup.getUserEmail()+"");
			  list.add(sup.getNote()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Supplier/list.do");
	  }	
	}
}
