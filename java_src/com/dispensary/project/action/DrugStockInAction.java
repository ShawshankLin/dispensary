/*
 */

package com.dispensary.project.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import com.dispensary.project.utils.MySession;
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class DrugStockInAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "inDate desc"; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/DrugStockIn/query.jsp";
	protected static final String LIST_JSP= "/pages/DrugStockIn/list.jsp";
	protected static final String CREATE_JSP = "/pages/DrugStockIn/create.jsp";
	protected static final String EDIT_JSP = "/pages/DrugStockIn/edit.jsp";
	protected static final String SHOW_JSP = "/pages/DrugStockIn/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/DrugStockIn/list.do";
	
	private DrugStockInManager drugStockInManager;
	@Resource private DispensarystaffManager dispensarystaffManager;
	@Resource private SupplierManager supplierManager;
	@Resource private DrugStockInDetailManager drugStockInDetailManager;
	@Resource private DrugStockInfoManager drugStockInfoManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	private DrugStockIn drugStockIn;
	java.lang.Integer id = null;
	private String[] items;
	private Set<DrugStockInDetail> drugStockInDetails=new HashSet<DrugStockInDetail>();
	
	public Set<DrugStockInDetail> getDrugStockInDetails() {
		return drugStockInDetails;
	}

	public void setDrugStockInDetails(Set<DrugStockInDetail> drugStockInDetails) {
		this.drugStockInDetails = drugStockInDetails;
	}
	public void prepare() throws Exception {
		drugStockIn = new DrugStockIn();
		/*if (isNullOrEmptyString(id)) {
			drugStockIn = new DrugStockIn();
		} else {
			drugStockIn = (DrugStockIn)drugStockInManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setDrugStockInManager(DrugStockInManager manager) {
		this.drugStockInManager = manager;
	}	
	
	public Object getModel() {
		return drugStockIn;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		DrugStockInQuery query = newQuery(DrugStockInQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = drugStockInManager.findPage(query);
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
	
	/** 生成新的订单号 */
	public String getStockInId(){
		String stockInId=drugStockInManager.getStockInId();
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			out.println(stockInId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 保存新增对象 */
	public String save() {
		String stockInId=null;
		if(drugStockIn.getStockInId()==null||"".equals(drugStockIn.getStockInId())){
			stockInId=drugStockInManager.getStockInId();
		}else{
			stockInId=drugStockIn.getStockInId();
			DrugStockInQuery query=new DrugStockInQuery();
			query.setStockInId(stockInId);
			List<DrugStockIn> list=drugStockInManager.findAll(query);
			if(list.isEmpty()){
				showMessage("输入的入库单据编号不存在！");
				return null;
			}
		}
		String serialNum=drugStockInManager.getSerialNum();		
		Iterator<DrugStockInDetail> iterator=drugStockInDetails.iterator();
		//Set<DrugStockInDetail> set=new HashSet<DrugStockInDetail>();
		while(iterator.hasNext()){
			//配置入库明细
			DrugStockInDetail detail=iterator.next();
			detail.setId(null);
			detail.setSerialNumber(serialNum);
			DrugBasicInfo drug=drugBasicInfoManager.getById(detail.getDrugId());
			detail.setInPrice(drug.getRetailPrice()*detail.getInStocks());
			//添加库存量
			DrugStockInfoQuery drugStockInfoQuery=new DrugStockInfoQuery();
			drugStockInfoQuery.setDrugId(detail.getDrugId());
			drugStockInfoQuery.setDrugPlaceId(detail.getInPlaceId());
			List<DrugStockInfo> drugStockInfos=drugStockInfoManager.findAll(drugStockInfoQuery);
			if(drugStockInfos.size()==1){
				DrugStockInfo drugStockInfo=drugStockInfos.get(0);
				drugStockInfo.setCurAmount(drugStockInfo.getCurAmount()+detail.getInStocks());
				drugStockInfoManager.update(drugStockInfo);
			}else{
				DrugStockInfo drugStockInfo=new DrugStockInfo();
				drugStockInfo.setDrugId(detail.getDrugId());
				drugStockInfo.setDrugPlaceId(detail.getInPlaceId());
				drugStockInfo.setCurAmount(detail.getInStocks());
				drugStockInfoManager.save(drugStockInfo);
			}
		}
		drugStockIn.setStockInId(stockInId);
		drugStockIn.setInDate(new Date());
		drugStockIn.setSerialNumber(serialNum);
		drugStockIn.setDrugStockInDetails(drugStockInDetails);
		MySession mySession=(MySession) getRequest().getSession().getAttribute("MySession");
		if(mySession!=null){
			Userinfo user=mySession.getUserinfo();
			if(user.getMeStId()!=null){
				drugStockIn.setOperatorId(user.getMeStId());
			}
		}
		drugStockInManager.save(drugStockIn);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		DrugStockInQuery inQuery=new DrugStockInQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("stockInId".equals(searchType)){
				inQuery.setStockInId(searchInfo);
			}
			if("serialNumber".equals(searchType)){
				inQuery.setSerialNumber(searchInfo);
			}
			if("inDate".equals(searchType)){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				Date date=null;
				try {
					date = format.parse(searchInfo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inQuery.setInDate(date);
			}
		}
		Page page = drugStockInManager.findPage(inQuery);
		savePage(page,inQuery);
		return LIST_JSP;
	}
	/**进入更新页面*/
	public String edit() {
		getRelative();
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		drugStockInManager.update(this.drugStockIn);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			drugStockInManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			drugStockInManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	public void getRelative(){
		List<Dispensarystaff> dispensarystaffList =  dispensarystaffManager.findAll();
		requestMap.put("dispensarystaffList",dispensarystaffList);
		List<Supplier> supplierList =  supplierManager.findAll();
		requestMap.put("supplierList",supplierList);
	}
	/** 查找入库单信息*/
	public String getInOrderInfos() {
		DrugStockInQuery drugStockInQuery=new DrugStockInQuery();
		if(drugStockIn.getStockInId()!=null){
			drugStockInQuery.setStockInId(drugStockIn.getStockInId());
		}
		if(drugStockIn.getSerialNumber()!=null){
			drugStockInQuery.setSerialNumber(drugStockIn.getSerialNumber());
		}
		List<DrugStockIn> list = drugStockInManager.findAll(drugStockInQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			DrugStockIn drugStockIn = list.get(i);
			DrugStockInQuery query = new DrugStockInQuery();
			BeanUtils.copyProperties(query, drugStockIn);
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
	public void getInReport() throws IOException {
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
		  String title[] = {DrugStockIn.ALIAS_STOCK_IN_ID,DrugStockIn.ALIAS_SERIAL_NUMBER,DrugStockIn.ALIAS_IN_DATE,
					DrugStockIn.ALIAS_TOTAL,DrugStockIn.ALIAS_SUM,DrugStockIn.ALIAS_OPERATOR_ID,
					DrugStockIn.ALIAS_SUPPLIER_ID};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<DrugStockIn> stuList = drugStockInManager.findAll();
		  Iterator it = stuList.iterator();
		  while (it.hasNext()) {
			  DrugStockIn in =(DrugStockIn) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(in.getStockInId()+"");
			  list.add(in.getSerialNumber()+"");
			  list.add(in.getInDateString());
			  list.add(in.getTotal()+"");
			  list.add(in.getSum()+"");
			  list.add(in.getOperatorIdModel().getMeStName()+"");
			  list.add(in.getSupplierIdModel().getSupplierName()+"");
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
