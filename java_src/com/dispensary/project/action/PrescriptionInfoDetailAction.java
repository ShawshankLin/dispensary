/*
 */

package com.dispensary.project.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javassist.runtime.Inner;

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

import javax.annotation.Resource;

public class PrescriptionInfoDetailAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/PrescriptionInfoDetail/query.jsp";
	protected static final String LIST_JSP= "/pages/PrescriptionInfoDetail/list.jsp";
	protected static final String CREATE_JSP = "/pages/PrescriptionInfoDetail/create.jsp";
	protected static final String EDIT_JSP = "/pages/PrescriptionInfoDetail/edit.jsp";
	protected static final String SHOW_JSP = "/pages/PrescriptionInfoDetail/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/PrescriptionInfoDetail/list.do";
	
	private PrescriptionInfoDetailManager prescriptionInfoDetailManager;
	@Resource private DrugBasicInfoManager drugBasicInfoManager;
	@Resource private PrescriptionInfoManager prescriptionInfoManager;
	private PrescriptionInfoDetail prescriptionInfoDetail;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		prescriptionInfoDetail = new PrescriptionInfoDetail();
		/*if (isNullOrEmptyString(id)) {
			prescriptionInfoDetail = new PrescriptionInfoDetail();
		} else {
			prescriptionInfoDetail = (PrescriptionInfoDetail)prescriptionInfoDetailManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setPrescriptionInfoDetailManager(PrescriptionInfoDetailManager manager) {
		this.prescriptionInfoDetailManager = manager;
	}	
	
	public Object getModel() {
		return prescriptionInfoDetail;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		PrescriptionInfoDetailQuery query = newQuery(PrescriptionInfoDetailQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = prescriptionInfoDetailManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		PrescriptionInfoDetailQuery presDetailQuery=new PrescriptionInfoDetailQuery();
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
					presDetailQuery.setPresId(Integer.parseInt(searchInfo));
				}
			}
			if("drugId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					presDetailQuery.setDrugId(Integer.parseInt(searchInfo));
				}
			}
		}
		Page page = prescriptionInfoDetailManager.findPage(presDetailQuery);
		savePage(page,presDetailQuery);
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
		prescriptionInfoDetailManager.save(prescriptionInfoDetail);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String id=getRequest().getParameter("id");
		PrescriptionInfoDetailQuery presDetailQuery=new PrescriptionInfoDetailQuery();
		if(id!=null){
			presDetailQuery.setId(Integer.parseInt(id));
		}
		List<PrescriptionInfoDetail> list=prescriptionInfoDetailManager.findAll(presDetailQuery);
		PrescriptionInfoDetail presDetail=new PrescriptionInfoDetail();
		for(int i=0;i<list.size();i++){
			presDetail=list.get(0);
		}
		getRequest().setAttribute("presDetail", presDetail);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		prescriptionInfoDetailManager.update(this.prescriptionInfoDetail);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			prescriptionInfoDetailManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			prescriptionInfoDetailManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<DrugBasicInfo> drugBasicInfoList =  drugBasicInfoManager.findAll();
		requestMap.put("drugBasicInfoList",drugBasicInfoList);
		List<PrescriptionInfo> prescriptionInfoList =  prescriptionInfoManager.findAll();
		requestMap.put("prescriptionInfoList",prescriptionInfoList);
	}

}
