/*
 */

package com.dispensary.project.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.dispatcher.RequestMap;
import org.springframework.web.util.WebUtils;

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

import javax.annotation.Resource;
public class ReceiptAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "recDate desc"; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Receipt/query.jsp";
	protected static final String LIST_JSP= "/pages/Receipt/list.jsp";
	protected static final String CREATE_JSP = "/pages/Receipt/create.jsp";
	protected static final String EDIT_JSP = "/pages/Receipt/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Receipt/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Receipt/list.do";
	
	private ReceiptManager receiptManager;
	@Resource private DispensarystaffManager dispensarystaffManager;
	@Resource private PrescriptionInfoManager prescriptionInfoManager;
	@Resource private PrescriptionInfoDetailManager prescriptionInfoDetailManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private FeetypeManager feetypeManager;
	@Resource private PatiCaseHistoryManager patiCaseHistoryManager;
	@Resource private DrugStockInfoManager drugStockInfoManager;
	private Receipt receipt;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		receipt = new Receipt();
		/*if (isNullOrEmptyString(id)) {
			receipt = new Receipt();
		} else {
			receipt = (Receipt)receiptManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setReceiptManager(ReceiptManager manager) {
		this.receiptManager = manager;
	}	
	
	public Object getModel() {
		return receipt;
	}
	
	public void setRecId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public MySession mySession;
	
	public MySession getMySession() {
		MySession mySession=(MySession)WebUtils.getSessionAttribute(getRequest(), "MySession");
		if(mySession!=null){
			return mySession;
		}else{
			showMessage2("页面长时间不活动，已推出", "/login.jsp");
			return null;
		}
	}

	public void setMySession(MySession mySession) {
		this.mySession = mySession;
	}
	/** 执行搜索 */
	public String list() {
		ReceiptQuery query = newQuery(ReceiptQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = receiptManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		ReceiptQuery recQuery=new ReceiptQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("recId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					recQuery.setRecId(Integer.parseInt(searchInfo));
				}
			}
			if("presId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					recQuery.setPresId(Integer.parseInt(searchInfo));
				}
			}
			if("meStId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					recQuery.setMeStId(Integer.parseInt(searchInfo));
				}
			}
			/*if("idcard".equals(searchType)){
				caseQuery.setVisitDateBegin(searchInfo);
			}*/
		}
		Page page = receiptManager.findPage(recQuery);
		savePage(page,recQuery);
		return LIST_JSP;
	}
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		getRelative();
		int presId=Integer.parseInt(getRequest().getParameter("presId"));
		//查找处方
		PrescriptionInfo pres=prescriptionInfoManager.getById(presId);
		//查找处方明细
		PrescriptionInfoDetailQuery detailQuery=new PrescriptionInfoDetailQuery();
		detailQuery.setPresId(presId);
		List<PrescriptionInfoDetail> details=prescriptionInfoDetailManager.findAll(detailQuery);
		//创建收据单实体
		Receipt receipt=new Receipt();
		receipt.setPresId(presId);
		receipt.setFeeSum(pres.getDrugSum());
		for(int i=0;i<details.size();i++){
			PrescriptionInfoDetail detail=details.get(i);
			DrugBasicInfo drug=drugBasicInfoManager.getById(detail.getDrugId());
			if(drug.getFeeTypeId()!=null){
				switch (drug.getFeeTypeId()) {
				case 1:
					receipt.setDrugFee((float)(Math.round((receipt.getDrugFee()+drug.getCostPrice())*100))/100);
					break;
				case 2:
					receipt.setRegisterFee((float)(Math.round((receipt.getRegisterFee()+drug.getCostPrice())*100))/100);
					break;
				case 3:
					receipt.setTransferFee((float)(Math.round((receipt.getTransferFee()+drug.getCostPrice())*100))/100);
					break;
				case 4:
					receipt.setPhysicalFee((float)(Math.round((receipt.getPhysicalFee()+drug.getCostPrice())*100))/100);
					break;
				case 5:
					receipt.setOxygenFee((float)(Math.round((receipt.getOxygenFee()+drug.getCostPrice())*100))/100);
					break;
				case 6:
					receipt.setEmergencyFee((float)(Math.round((receipt.getEmergencyFee()+drug.getCostPrice())*100))/100);
					break;
				case 7:
					receipt.setInjectionFee((float)(Math.round((receipt.getInjectionFee()+drug.getCostPrice())*100))/100);
					break;
				case 8:
					receipt.setNebulizationFee((float)(Math.round((receipt.getNebulizationFee()+drug.getCostPrice())*100))/100);
					break;
				default:
					break;
				}
			}
		}
		getRequest().setAttribute("receipt", receipt);
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		try {
			receipt.setMeStId(getMySession().getUserinfo().getMeStId());
			receipt.setRecDate(new Date());
			receiptManager.save(receipt);
			//更新处方单状态
			PrescriptionInfo pres=prescriptionInfoManager.getById(receipt.getPresId());
			pres.setState(1);
			prescriptionInfoManager.saveOrUpdate(pres);
			//更新库存状态
			Set<PrescriptionInfoDetail> presDetails=pres.getPrescriptionInfoDetails();
			Iterator iterator=presDetails.iterator();
			while(iterator.hasNext()){
				PrescriptionInfoDetail detail=(PrescriptionInfoDetail)iterator.next();
				DrugStockInfoQuery query=new DrugStockInfoQuery();
				query.setDrugId(detail.getDrugId());
				List<DrugStockInfo> dsiList=drugStockInfoManager.findAll(query);
				//判断库存药品不存在
				if(dsiList.isEmpty()){
					showMessage("药房没有该类药品，请核对后再出售");
					return null;
				}else{
					DrugStockInfo dsi=dsiList.get(0);
					//判断库存不足
					if(dsi.getCurAmount()<detail.getAmount()){
						showMessage("药品  "+dsi.getDrugIdModel().getDrugName()+"的数量不足！/n当前库存数量为"+dsi.getCurAmount());
						return null;
					}else{
						dsi.setCurAmount(dsi.getCurAmount()-detail.getAmount());
						drugStockInfoManager.update(dsi);
					}
					
				}
			}
			showMessage2("保存收据单成功！", "/pages/Receipt/list.do");
		} catch (Exception e) {
			// TODO: handle exception
			showMessage2("保存收据单失败！", "/pages/Receipt/list.do");
		}
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		ReceiptQuery recQuery=new ReceiptQuery();
		if(receipt.getRecId()!=null){
			recQuery.setRecId(receipt.getRecId());
		}
		if(receipt.getPresId()!=null){
			recQuery.setPresId(receipt.getPresId());
		}
		List<Receipt> receipts=receiptManager.findAll(recQuery);
		if(receipts.size()==1){
			Receipt rec=receipts.get(0);
			getRequest().setAttribute("receipt", rec);
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		if(receipt.getMeStId()==null){
			MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
			Userinfo userinfo=mySession.getUserinfo();
			receipt.setMeStId(userinfo.getMeStId());
		}
		if(receipt.getRecDate()==null){
			receipt.setRecDate(new Date());
		}
		receiptManager.update(this.receipt);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String recId=getRequest().getParameter("recId");
		if(recId!=null){
			receiptManager.removeById(Integer.parseInt(recId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("recId"));
			receiptManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Dispensarystaff> dispensarystaffList =  dispensarystaffManager.findAll();
		requestMap.put("dispensarystaffList",dispensarystaffList);
		List<PrescriptionInfo> prescriptionInfoList =  prescriptionInfoManager.findAll();
		requestMap.put("prescriptionInfoList",prescriptionInfoList);
	}
	/**查询收据单*/
	public String getReceipt() {	
		ReceiptQuery receiptQuery = new ReceiptQuery();
		receiptQuery.setRecId(receipt.getRecId());
		receiptQuery.setPresId(receipt.getPresId());
		receiptQuery.setMeStId(receipt.getMeStId());
		List<Receipt> list = receiptManager.findAll(receiptQuery);
		Receipt receipt = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			receipt = (Receipt)list.get(0);
			JSONObject jsonobj = JSONObject.fromObject(receipt);
			//System.out.println(jsonobj.toString());
			out.write(jsonobj.toString());
		}else{
			out.write("alert('无此收据单信息')");
		}
		return null;
	}
	//导出收据报表
	public void getRecReport() throws IOException {
		try {
		// 创建当前日子
	     Date date = new Date();
	     // 格式化日期 
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	     // 格式化日期(产生文件名)
	     String filename = sdf.format(date);
	     // 获得ServletContext对象
	     ServletContext servletContext = getRequest().getServletContext();	     
	     // 创建文件
	     File f = new File(servletContext.getRealPath("download"),filename+".xls");
	     System.out.println(f.getParentFile().mkdir());
	     f.getParentFile().mkdir();
		 try {
			f.createNewFile();
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
	  
	      //标题数组
		  String title[] = {Receipt.ALIAS_REC_ID,Receipt.ALIAS_PRES_ID,Receipt.ALIAS_DRUG_FEE,Receipt.ALIAS_EMERGENCY_FEE,
				  Receipt.ALIAS_INJECTION_FEE,Receipt.ALIAS_NEBULIZATION_FEE,Receipt.ALIAS_OXYGEN_FEE,Receipt.ALIAS_PHYSICAL_FEE,
				  Receipt.ALIAS_REGISTER_FEE,Receipt.ALIAS_TRANSFER_FEE,Receipt.ALIAS_FEE_SUM,Receipt.ALIAS_ME_ST_ID,Receipt.ALIAS_REC_DATE};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Receipt> caseList=receiptManager.findAll();
		  Iterator it = caseList.iterator();
		  while (it.hasNext()) {
			  Receipt receipt =(Receipt) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(receipt.getRecId()+"");
			  list.add(receipt.getPresId()+"");
			  list.add(receipt.getDrugFee()+"");
			  list.add(receipt.getEmergencyFee()+"");
			  list.add(receipt.getNebulizationFee()+"");
			  list.add(receipt.getOxygenFee()+"");
			  list.add(receipt.getPhysicalFee()+"");
			  list.add(receipt.getRegisterFee()+"");
			  list.add(receipt.getTransferFee()+"");
			  list.add(receipt.getFeeSum()+"");
			  list.add(receipt.getMeStIdModel().getMeStName()+"");
			  list.add(receipt.getRecDateString());
			  lists.add(list);
		}
		  // 生成excel文件(保存在服务器机上)
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  		showMessage2("导出excel失败！", "/pages/Receipt/list.do");
	  }	
	}
	//导出单例收据报表
	public void getSingleRecReport() throws IOException{
		if(receipt.getRecId()!=null){
			Receipt rec=receiptManager.getById(receipt.getRecId());
			System.out.println(getRequest().getServletContext().getRealPath("")+"\\template\\复件 收据 (version 1).xls");
			File file=new File(getRequest().getServletContext().getRealPath("")+"\\template\\复件 收据 (version 1).xls");
			if(!file.exists()){
				showMessage("收据单模板文件不存在！");
			}else{
				InputStream is=new FileInputStream(file);
				HSSFWorkbook wb = new HSSFWorkbook(is);
				HSSFSheet sheet= wb.getSheetAt(0);
				
				//填写学生姓名
				HSSFRow row = sheet.getRow(3);
				PrescriptionInfo pres=rec.getPresIdModel();
				PatiCaseHistoryQuery pchQuery=new PatiCaseHistoryQuery();
				pchQuery.setCaseId(pres.getCaseId());
				PatiCaseHistory pch=patiCaseHistoryManager.findAll(pchQuery).get(0);
				row.getCell(1).setCellValue(pch.getStuNumModel().getStuName());
				HSSFCellStyle numCellStyle = wb.createCellStyle();
				numCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		        
				//填写费用类型
				row = sheet.getRow(5);
				row.getCell(3).setCellValue(rec.getRegisterFee());
				row.getCell(3).setCellStyle(numCellStyle);
		            
				row = sheet.getRow(6);
				row.getCell(3).setCellValue(rec.getDrugFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(7);
				row.getCell(3).setCellValue(rec.getTransferFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(8);
				row.getCell(3).setCellValue(rec.getPhysicalFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(10);
				row.getCell(3).setCellValue(rec.getOxygenFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(11);
				row.getCell(3).setCellValue(rec.getEmergencyFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(12);
				row.getCell(3).setCellValue(rec.getInjectionFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(13);
				row.getCell(3).setCellValue(rec.getNebulizationFee());
				row.getCell(3).setCellStyle(numCellStyle);
				
				row = sheet.getRow(14);
				row.getCell(1).setCellValue(rec.getMeStIdModel().getMeStName());
				
				row = sheet.getRow(15);
				row.getCell(1).setCellValue(rec.getRecDateString());
				row.getCell(3).setCellValue(rec.getFeeSum());
				row.getCell(3).setCellStyle(numCellStyle);
				// 创建当前日子
			     Date date = new Date();
			     // 格式化日期 
			     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			     // 格式化日期(产生文件名)
			     String filename = sdf.format(date);
			     // 获得ServletContext对象
			     ServletContext servletContext = getRequest().getServletContext();	     
			     // 创建文件
			     File f = new File(servletContext.getRealPath("download"),filename+".xls");
			     System.out.println(f.getParentFile().mkdir());
			     f.getParentFile().mkdir();
				 try {
					f.createNewFile();
					wb.write(new FileOutputStream(f));
					Files.exportFile(getResponse(), f, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  		showMessage2("导出excel失败！", "/pages/Receipt/list.do");
				}
			}
		}
	}
}
