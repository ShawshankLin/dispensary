/*
 */

package com.dispensary.project.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class DrugStockInDetailAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugStockInDetail/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugStockInDetail/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugStockInDetail/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugStockInDetail/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugStockInDetail/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugStockInDetail/list.do";
	
	private DrugStockInDetailManager drugStockInDetailManager;
	@Resource private DrugPlaceManager drugPlaceManager;
	@Resource private DrugStockInManager drugStockInManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	private DrugStockInDetail drugStockInDetail;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			drugStockInDetail = new DrugStockInDetail();
		} else {
			drugStockInDetail = (DrugStockInDetail)drugStockInDetailManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugStockInDetailManager(DrugStockInDetailManager manager) {
		this.drugStockInDetailManager = manager;
	}	
	
	public Object getModel() {
		return drugStockInDetail;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugStockInDetailQuery query = newQuery(DrugStockInDetailQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugStockInDetailManager.findPage(query);
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
		drugStockInDetailManager.save(drugStockInDetail);
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
		drugStockInDetailManager.update(this.drugStockInDetail);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			drugStockInDetailManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			drugStockInDetailManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		DrugStockInDetailQuery inDetailQuery=new DrugStockInDetailQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("serialNumber".equals(searchType)){
				inDetailQuery.setSerialNumber(searchInfo);
			}
		}
		Page page = drugStockInDetailManager.findPage(inDetailQuery);
		savePage(page,inDetailQuery);
		return LIST_JSP;
	}
	public void getRelative(){
		List<DrugPlace> drugPlaceList =  drugPlaceManager.findAll();
		requestMap.put("drugPlaceList",drugPlaceList);
		List<DrugStockIn> drugStockInList =  drugStockInManager.findAll();
		requestMap.put("drugStockInList",drugStockInList);
		List<DrugBasicInfo> drugBasicInfoList =  drugBasicInfoManager.findAll();
		requestMap.put("drugBasicInfoList",drugBasicInfoList);
	}
	public void getInDetailReport() throws IOException {
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
		  String title[] = {DrugStockInDetail.ALIAS_SERIAL_NUMBER,DrugStockInDetail.ALIAS_DRUG_ID,DrugStockInDetail.ALIAS_IN_PLACE_ID,
				  DrugStockInDetail.ALIAS_IN_PRICE,DrugStockInDetail.ALIAS_IN_STOCKS};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<DrugStockInDetail> inDetailList = drugStockInDetailManager.findAll();
		  Iterator it = inDetailList.iterator();
		  while (it.hasNext()) {
			  DrugStockInDetail inDetai =(DrugStockInDetail) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(inDetai.getSerialNumber()+"");
			  list.add(inDetai.getDrugIdModel().getDrugName()+"");
			  list.add(inDetai.getInPlaceIdModel().getDrugPlace());
			  list.add(inDetai.getInPrice()+"");
			  list.add(inDetai.getInStocks()+"");
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
