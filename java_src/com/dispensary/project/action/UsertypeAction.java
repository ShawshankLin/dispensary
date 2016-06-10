/*
 */

package com.dispensary.project.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

public class UsertypeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Usertype/query.jsp";
	protected static final String LIST_JSP= "/pages/Usertype/list.jsp";
	protected static final String CREATE_JSP = "/pages/Usertype/create.jsp";
	protected static final String EDIT_JSP = "/pages/Usertype/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Usertype/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Usertype/list.do";
	
	private UsertypeManager usertypeManager;
	private Usertype usertype;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		usertype = new Usertype();
		/*if (isNullOrEmptyString(id)) {
			usertype = new Usertype();
		} else {
			usertype = (Usertype)usertypeManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUsertypeManager(UsertypeManager manager) {
		this.usertypeManager = manager;
	}	
	
	public Object getModel() {
		return usertype;
	}
	
	public void setUserTypeId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UsertypeQuery query = newQuery(UsertypeQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = usertypeManager.findPage(query);
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
		usertypeManager.save(usertype);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String userTypeId=getRequest().getParameter("userTypeId");
		if(userTypeId!=null){
			Usertype usertype=usertypeManager.getById(Integer.parseInt(userTypeId));
			getRequest().setAttribute("usertype", usertype);
		}	
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		usertypeManager.update(this.usertype);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String userTypeId=getRequest().getParameter("userTypeId");
		if(userTypeId!=null){
			usertypeManager.removeById(Integer.parseInt(userTypeId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("userTypeId"));
			usertypeManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	}
	/* 执行查找用户类型信息 */
	public String getUsertype() {	
		UsertypeQuery usertypeQuery = new UsertypeQuery();
		usertypeQuery.setUserTypeId(usertype.getUserTypeId());
		List<Usertype> list = usertypeManager.findAll(usertypeQuery);
		Usertype type = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			type = (Usertype)list.get(0);
			UsertypeQuery query = new UsertypeQuery();
			BeanUtils.copyProperties(query, type);
			JSONObject jsonobj = JSONObject.fromObject(query);
			out.write(jsonobj.toString());
		}else{
			out.write("alert('无此用户类型信息')");
		}
		return null;
	}
	/** 查询所有用户类型信息 */
	public String getUsertypeJSON(){		
		List<Usertype> list = usertypeManager.findAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Usertype usertype = list.get(i);
			UsertypeQuery query = new UsertypeQuery();
			BeanUtils.copyProperties(query, usertype);
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
	/** 执行搜索 */
	public String search(){
		UsertypeQuery typeQuery=new UsertypeQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("userTypeId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					typeQuery.setUserTypeId(Integer.parseInt(searchInfo));
				}
			}
			if("userTypeName".equals(searchType)){
				typeQuery.setUserTypeName(searchInfo);
			}
		}
		Page page = usertypeManager.findPage(typeQuery);
		savePage(page,typeQuery);
		return LIST_JSP;
	}
	public void getUsertypeReport() throws IOException {
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
		  String title[] = {Usertype.ALIAS_USER_TYPE_ID,Usertype.ALIAS_USER_TYPE_NAME};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Usertype> typeList = usertypeManager.findAll();
		  Iterator it = typeList.iterator();
		  while (it.hasNext()) {
			  Usertype type =(Usertype) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(type.getUserTypeId()+"");
			  list.add(type.getUserTypeName()+"");
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)

		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Usertype/list.do");
	  }	
	}
}
