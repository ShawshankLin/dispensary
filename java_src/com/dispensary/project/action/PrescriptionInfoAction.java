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

import org.apache.struts2.dispatcher.RequestMap;
import org.omg.CORBA.PRIVATE_MEMBER;

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
import com.dispensary.project.utils.MySession;
import com.dispensary.project.vo.query.*;
import com.google.gson.Gson;

import javax.annotation.Resource;

public class PrescriptionInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "persDate desc,state asc"; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/PrescriptionInfo/query.jsp";
	protected static final String LIST_JSP= "/pages/PrescriptionInfo/list.jsp";
	protected static final String CREATE_JSP = "/pages/PrescriptionInfo/create.jsp";
	protected static final String EDIT_JSP = "/pages/PrescriptionInfo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/PrescriptionInfo/show.jsp";
	protected static final String CONFIRM_JSP = "/pages/Receipt/receipt_view.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/PrescriptionInfo/list.do";
	
	private PrescriptionInfoManager prescriptionInfoManager;
	@Resource private PatiCaseHistoryManager patiCaseHistoryManager;
	@Resource private ReceiptManager receiptManager;
	private PrescriptionInfo prescriptionInfo;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		prescriptionInfo = new PrescriptionInfo();
		/*if (isNullOrEmptyString(id)) {
			prescriptionInfo = new PrescriptionInfo();
		} else {
			prescriptionInfo = (PrescriptionInfo)prescriptionInfoManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setPrescriptionInfoManager(PrescriptionInfoManager manager) {
		this.prescriptionInfoManager = manager;
	}	
	
	public Object getModel() {
		return prescriptionInfo;
	}
	
	public void setPresId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		PrescriptionInfoQuery query = newQuery(PrescriptionInfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = prescriptionInfoManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		PrescriptionInfoQuery presQuery=new PrescriptionInfoQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("presId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					presQuery.setPresId(Integer.parseInt(searchInfo));
				}
			}
			if("caseId".equals(searchType)){
				presQuery.setCaseId(searchInfo);
			}
			if("state".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					presQuery.setState(Integer.parseInt(searchInfo));
				}
			}
			if("persDate".equals(searchType)){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				Date date=null;
				try {
					date = format.parse(searchInfo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				presQuery.setPersDateString(date);
			}
		}
		Page page = prescriptionInfoManager.findPage(presQuery);
		savePage(page,presQuery);
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
		prescriptionInfoManager.save(prescriptionInfo);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String presId=getRequest().getParameter("presId");
		if(presId!=null){
			PrescriptionInfo pres=prescriptionInfoManager.getById(Integer.parseInt(presId));
			getRequest().setAttribute("pres", pres);
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String updateState() {
		if(prescriptionInfo.getPresId()!=null){
			ReceiptQuery receiptQuery=new ReceiptQuery();
			receiptQuery.setPresId(prescriptionInfo.getPresId());
			List<Receipt> receiptsList=receiptManager.findAll(receiptQuery);
			if(receiptsList.size()!=1){
				PrescriptionInfo pres=prescriptionInfoManager.getById(prescriptionInfo.getPresId());
				//生成收据单
				Receipt receipt=new Receipt();
				receipt.setPresId(pres.getPresId());
				receipt.setFeeSum(pres.getDrugSum());
				MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
				if(mySession!=null){
					receipt.setMeStId(mySession.getUserinfo().getMeStId());
				}
				pres.setState(prescriptionInfo.getState());
				Set<Receipt> receipts=new HashSet<Receipt>();
				receipts.add(receipt);
				pres.setReceipts(receipts);
				prescriptionInfoManager.update(pres);
			}
			return "!/pages/Receipt/edit.do?presId="+prescriptionInfo.getPresId();
		}
		return LIST_ACTION;
		
	}
	/**保存更新对象*/
	public String update() {
		prescriptionInfoManager.update(this.prescriptionInfo);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		Integer presId=Integer.parseInt(getRequest().getParameter("presId"));
		if(presId!=null){
			//删除处方与病历的级联，防止出现级联错误，，此方法为暂时规避
			PrescriptionInfo pres=prescriptionInfoManager.getById(presId);
			PatiCaseHistory patiCaseHistory=pres.getCaseIdModel();
			patiCaseHistory.getPrescriptionInfos().remove(pres);
			prescriptionInfoManager.removeById(id);
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("presId"));
			//删除处方与病历的级联，防止出现级联错误，，此方法为暂时规避
			PrescriptionInfo pres=prescriptionInfoManager.getById(id);
			PatiCaseHistory patiCaseHistory=pres.getCaseIdModel();
			patiCaseHistory.getPrescriptionInfos().remove(pres);
			prescriptionInfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<PatiCaseHistory> patiCaseHistoryList =  patiCaseHistoryManager.findAll();
		requestMap.put("patiCaseHistoryList",patiCaseHistoryList);
	}
	/**获取处方id*/
	public int getPresId(){
		return prescriptionInfoManager.getPresId();
	}
	/**
	 * 
	  * TODO用一句话描述作用
	  * @return
	 */
	public String getPresByCaseIdJSON(){
		PrescriptionInfoQuery presQuery=new PrescriptionInfoQuery();
		presQuery.setCaseId(prescriptionInfo.getCaseId());
		List<PrescriptionInfo> presList=prescriptionInfoManager.findAll(presQuery);
		List<PrescriptionInfoQuery> queryList=new ArrayList<PrescriptionInfoQuery>();
		for(int i=0;i<presList.size();i++){
			PrescriptionInfo pres=presList.get(i);
			PrescriptionInfoQuery query=new PrescriptionInfoQuery();
			BeanUtils.copyProperties(query,pres);
			queryList.add(query);
		}
		Gson gson=new Gson();
		String presStr=gson.toJson(queryList);
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.println(presStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void getPresReport() throws IOException {
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
		  String title[] = {PrescriptionInfo.ALIAS_PRES_ID,PrescriptionInfo.ALIAS_CASE_ID,PrescriptionInfo.ALIAS_PERS_DATE,
				  PrescriptionInfo.ALIAS_STATE,PrescriptionInfo.ALIAS_DRUG_SUM};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<PrescriptionInfo> caseList = prescriptionInfoManager.findAll();
		  Iterator it = caseList.iterator();
		  while (it.hasNext()) {
			  PrescriptionInfo pres =(PrescriptionInfo) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(pres.getPresId()+"");
			  list.add(pres.getCaseId()+"");
			  list.add(pres.getPersDateString());
			  list.add(pres.getState()+"");
			  list.add(pres.getDrugSum()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	 
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/PrescriptionInfo/list.do");
	  }	
	}
}
