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
import com.dispensary.project.utils.MySession;
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class MemoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Memo/query.jsp";
	protected static final String LIST_JSP= "/pages/Memo/list.jsp";
	protected static final String CREATE_JSP = "/pages/Memo/create.jsp";
	protected static final String EDIT_JSP = "/pages/Memo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Memo/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Memo/list.do";
	
	private MemoManager memoManager;
	@Resource private UserinfoManager userinfoManager;
	private Memo memo;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			memo = new Memo();
		} else {
			memo = (Memo)memoManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setMemoManager(MemoManager manager) {
		this.memoManager = manager;
	}	
	
	public Object getModel() {
		return memo;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		MemoQuery query = newQuery(MemoQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = memoManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		/*MemoQuery memoQuery=new MemoQuery();
		String id=getRequest().getParameter("id");
		if(id!=null){
			memoQuery.setId(Integer.parseInt(id));
		}
		List<Memo> memo=memoManager.findAll(memoQuery);
		if(memo.size()==1){
			getRequest().setAttribute("memo", memo);
		}*/
		getRequest().setAttribute("memo", memo);
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		getRelative();
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
		if(mySession!=null&&mySession.getUserinfo()!=null){
			memo.setUserId(mySession.getUserinfo().getUserId());
		}
		memo.setDate(new Date());
		memo.setStatus(1);
		memoManager.save(memo);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String id=getRequest().getParameter("id");
		if(id!=null){
			Memo memo=memoManager.getById(Integer.parseInt(id));
			getRequest().setAttribute("memo", memo);
		}	
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		memoManager.update(this.memo);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			memoManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			memoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		MemoQuery memoQuery=new MemoQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("id".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					memoQuery.setId(Integer.parseInt(searchInfo));
				}
			}
			if("title".equals(searchType)){
				memoQuery.setTitle(searchInfo);
			}
			if("date".equals(searchType)){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				Date date=null;
				try {
					date = format.parse(searchInfo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				memoQuery.setDateString(date);
			}
		}
		Page page = memoManager.findPage(memoQuery);
		savePage(page,memoQuery);
		return LIST_JSP;
	}
	public void getRelative(){
		List<Userinfo> userinfoList =  userinfoManager.findAll();
		requestMap.put("userinfoList",userinfoList);
	}
	/* 执行查找用户备忘录 */
	public String getMemo() {	
		MemoQuery memoQuery = new MemoQuery();
		Userinfo user=(Userinfo) getRequest().getSession().getAttribute("userLogin");
		memoQuery.setUserId(user.getUserId());
		List<Memo> list = memoManager.findAll(memoQuery);
		Memo memo = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			memo = (Memo)list.get(0);
			UserinfoQuery query = new UserinfoQuery();
			BeanUtils.copyProperties(query, memo);
			JSONObject jsonobj = JSONObject.fromObject(query);
			out.write(jsonobj.toString());
		}
		return null;
	}
	/** 查询用户备忘录 */
	public String getMemos(){
		MemoQuery memoQuery = new MemoQuery();
		Userinfo user=(Userinfo) getRequest().getSession().getAttribute("userLogin");
		if(user!=null){
			memoQuery.setUserId(user.getUserId());
			memoQuery.setStatus(1);
			memoQuery.setSortColumns("date");
		}
		List<Memo> list = memoManager.findAll(memoQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Memo memo = list.get(i);
			MemoQuery query = new MemoQuery();
			BeanUtils.copyProperties(query, memo);
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
	public void getMemoReport() throws IOException {
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
		  String title[] = {Memo.ALIAS_USER_ID,Memo.ALIAS_TITLE,Memo.ALIAS_CONTENT,
					Memo.ALIAS_ATTACHMENT,Memo.ALIAS_DATE,Memo.ALIAS_STATUS};
		  List<List> lists = new ArrayList<List>();
		  //查询自己的便签
		  MemoQuery memoQuery=new MemoQuery();
		  Userinfo userinfo=(Userinfo)getRequest().getSession().getAttribute("loginUser");
		  memoQuery.setUserId(userinfo.getUserId());
		  //组成list
		  List<Memo> memoList = memoManager.findAll(memoQuery);
		  Iterator it = memoList.iterator();
		  while (it.hasNext()) {
			  Memo memo =(Memo) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(memo.getUserIdModel().getModelTagValue()+"");
			  list.add(memo.getTitle()+"");
			  list.add(memo.getContent()+"");
			  list.add(memo.getAttachment()+"");
			  list.add(memo.getDateString()+"");
			  list.add(memo.getStatus()+"");
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
