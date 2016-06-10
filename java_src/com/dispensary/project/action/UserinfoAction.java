/*
 */

package com.dispensary.project.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.dispatcher.RequestMap;
import org.springframework.context.ApplicationContext;

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
import com.dispensary.project.utils.Application;
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.utils.MD5Util;
import com.dispensary.project.utils.MySession;
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class UserinfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Userinfo/query.jsp";
	protected static final String LIST_JSP= "/pages/Userinfo/list.jsp";
	protected static final String CREATE_JSP = "/pages/Userinfo/create.jsp";
	protected static final String EDIT_JSP = "/pages/Userinfo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Userinfo/show.jsp";
	//redirect paths,startWith: !
	protected static final String INDEX_JSP = "!/index.jsp";
	protected static final String LOGIN_JSP="!/login.jsp";
	protected static final String LIST_ACTION = "!/pages/Userinfo/list.do";
	private UserinfoManager userinfoManager;
	@Resource private UsertypeManager usertypeManager;
	@Resource private UserRoleInfoManager userRoleInfoManager;
	@Resource private DispensarystaffManager dispensarystaffManager;
	@Resource private MemoManager memoManager;
	@Resource private NoticeManager noticeManager;
	@Resource private LogManager logManager;
	@Resource private PatiCaseHistoryManager patiCaseHistoryManager;
	@Resource private DrugStockInfoManager drugStockInfoManager;
	@Resource private RoleManager roleManager;
	private Userinfo userinfo;
	java.lang.Integer id = null;
	private String[] items;
	//保存用户角色
	String roles;
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	private Set<UserRoleInfo> userRoleInfos=new HashSet<UserRoleInfo>();
	public Set<UserRoleInfo> getUserRoleInfos() {
		return userRoleInfos;
	}

	public void setUserRoleInfos(Set<UserRoleInfo> userRoleInfos) {
		this.userRoleInfos = userRoleInfos;
	}

	public void prepare() throws Exception {
		userinfo = new Userinfo();
		/*if (isNullOrEmptyString(id)) {
			userinfo = new Userinfo();
		} else {
			userinfo = (Userinfo)userinfoManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserinfoManager(UserinfoManager manager) {
		this.userinfoManager = manager;
	}	
	
	public Object getModel() {
		return userinfo;
	}
	
	public void setUserId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UserinfoQuery query = newQuery(UserinfoQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userinfoManager.findPage(query);
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
		//保存用户信息
		int maxId=userinfoManager.getNewUserId();
		userinfo.setUserId(maxId);
		userinfo.setPassword(MD5Util.createPassword(userinfo.getPassword()));
		userinfoManager.save(userinfo);
		//保存用户角色
		if(roles!=null){
			String[] roleArr=roles.replace(" ","").split(",");
			
			for(int i=0;i<roleArr.length;i++){
				UserRoleInfo ur=new UserRoleInfo();
				ur.setRoleId(Integer.parseInt(roleArr[i]));
				ur.setUserId(userinfo.getUserId());
				userRoleInfoManager.saveOrUpdate(ur);
			}
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	public String setAccessUrl(){
		String curUrl = this.getRequest().getParameter("curUrl");
		HttpSession session2 = this.getRequest().getSession();
		session2.setAttribute("curUrl", curUrl);
		return null;
	}
	public String getAccessUrl(){
		String curUrl = (String)this.getRequest().getSession().getAttribute("curUrl");
		PrintWriter writer = null;
		try {
			writer = this.getResponse().getWriter();
			writer.print(curUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String userId=getRequest().getParameter("userId");
		if(userId!=null){
			Userinfo user=userinfoManager.getById(Integer.parseInt(userId));
			UserRoleInfoQuery query=new UserRoleInfoQuery();
			query.setUserId(user.getUserId());
			List<UserRoleInfo> roles=userRoleInfoManager.findAll(query);
			getRequest().setAttribute("roles", roles);
			getRequest().setAttribute("user", user);
		}
		return EDIT_JSP;
	}
	/**保存更新对象*/
	public String update() {
		userinfoManager.update(this.userinfo);
		//添加用户角色
		if(roles!=null){
			UserRoleInfoQuery urQuery=new UserRoleInfoQuery();
			urQuery.setUserId(userinfo.getUserId());
			List<UserRoleInfo> urList=userRoleInfoManager.findAll(urQuery);
			for(int i=0;i<urList.size();i++){
				UserRoleInfo ur=urList.get(i);
				userRoleInfoManager.removeById(ur.getId());
			}
			String[] roleArr=roles.replace(" ","").split(",");
			for(int i=0;i<roleArr.length;i++){
				UserRoleInfo ur=new UserRoleInfo();
				ur.setUserId(userinfo.getUserId());
				System.out.println(roleArr[i]);
				ur.setRoleId(Integer.valueOf(roleArr[i]));
				userRoleInfoManager.save(ur);
			}
		}
		
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String userId=getRequest().getParameter("userId");
		if(userId!=null){
			userinfoManager.removeById(Integer.parseInt(userId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("userId"));
			userinfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Usertype> usertypeList =  usertypeManager.findAll();
		requestMap.put("usertypeList",usertypeList);
		List<Dispensarystaff> dispensarystaffList =  dispensarystaffManager.findAll();
		requestMap.put("dispensarystaffList",dispensarystaffList);
		List<Role> roleList = roleManager.findAll();
		requestMap.put("roleList",roleList);
	}
	/** 执行搜索 */
	public String search(){
		UserinfoQuery userQuery=new UserinfoQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("userId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					userQuery.setUserId(Integer.parseInt(searchInfo));
				}
			}
			if("userName".equals(searchType)){
				userQuery.setUserName(searchInfo);
			}
			if("meStId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					userQuery.setMeStId(Integer.parseInt(searchInfo));
				}
			}
		}
		Page page = userinfoManager.findPage(userQuery);
		savePage(page,userQuery);
		return LIST_JSP;
	}
	/* 执行查找用户信息 */
	public String getUserinfo() {	
		UserinfoQuery userinfoQuery = new UserinfoQuery();
		userinfoQuery.setUserId(userinfo.getUserId());
		List<Userinfo> list = userinfoManager.findAll(userinfoQuery);
		Userinfo userinfo = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			userinfo = (Userinfo)list.get(0);
			UserinfoQuery query = new UserinfoQuery();
			BeanUtils.copyProperties(query, userinfo);
			JSONObject jsonobj = JSONObject.fromObject(query);
			out.write(jsonobj.toString());
		}else{
			showMessage("无此用户信息");
		}
		return null;
	}
	/** 查询所有用户信息 */
	public String getUserinfos(){		
		List<Userinfo> list = userinfoManager.findAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Userinfo userinfo = list.get(i);
			UserinfoQuery query = new UserinfoQuery();
			BeanUtils.copyProperties(query, userinfo);
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
	public String changePWD(){
		String newPWD=MD5Util.createPassword(userinfo.getPassword());
		MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
		userinfo=mySession.getUserinfo();
		//将修改的密码使用md5加密
		userinfo.setPassword(newPWD);
		userinfoManager.update(userinfo);
		showMessage("密码修改成功！");
		return null;
	}
	//检擦密码正确性
	public String checkPWD(){
		MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
		Userinfo user=mySession.getUserinfo();
		PrintWriter out;
		try {
			out = getResponse().getWriter();
			if(MD5Util.authenticatePassword(user.getPassword(),userinfo.getPassword())){
				out.write("{\"result\":\"true\"}");
			}else {
				out.write("{\"result\":\"false\"}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	//检查用户合法性
	public String login(){
		UserinfoQuery userinfoQuery = new UserinfoQuery();
		if(userinfo.getUserName()!=null){
			userinfoQuery.setUserName(userinfo.getUserName());
		}
		//userinfoQuery.setPassword(userinfo.getPassword());
		List<Userinfo> list = userinfoManager.findAll(userinfoQuery);
		//使用md5算法效验密码
		if(list.size()==1){
			if(list.get(0).getIfValidity()!=0){
				if (MD5Util.authenticatePassword(list.get(0).getPassword(),userinfo.getPassword())) {
					ServletContext servletContext=getRequest().getServletContext();
					Application application=null;
					if(servletContext!=null&&servletContext.getAttribute("Application")!=null){
						application=(Application) servletContext.getAttribute("Application");
					}else {
						application=new Application();
					}
					//添加在线用户信息
					Map<Integer, Userinfo> onlineMap=null;
					if(application.getOnlineUser()!=null&&!application.getOnlineUser().isEmpty()){
						onlineMap=application.getOnlineUser();
					}else {
						onlineMap=new HashMap<Integer, Userinfo>();
					}
					onlineMap.put(list.get(0).getUserId(), list.get(0));
					application.setOnlineUser(onlineMap);
					servletContext.setAttribute("Application", application);
					//创建session
					HttpSession session=getRequest().getSession();
					MySession mySession=null;
					if(session!=null&&session.getAttribute("MySession")!=null){
						mySession=(MySession) session.getAttribute("MySession");
					}else{
						mySession=new MySession();
					}
					//保存在线用户信息
					mySession.setUserinfo(list.get(0));
					//查询最新的便签
					Memo memo=memoManager.getNewMemo(String.valueOf(list.get(0).getUserId()));
					mySession.setMemo(memo);
					//查询最新的公告
					Notice notice=noticeManager.getNewNotice();
					mySession.setNotice(notice);
					//查询历史就诊数
					mySession.setHistVisitsNum(patiCaseHistoryManager.getHistVisitsNum());
					//查询昨日就诊数
					mySession.setYdayVisitsNum(patiCaseHistoryManager.getYdayVisitsNum());
					//查询库存药品总数
					mySession.setStockDrugNum(drugStockInfoManager.getStockDrugNum());
					//查询前5的就诊医生
					mySession.setTopStaffs(patiCaseHistoryManager.getTopVisits());
					//查询用户角色
					List<UserRoleInfo> roleList=getUserRoles(list.get(0).getUserId());
					mySession.setRoleList(roleList);
					//保存session
					session.setAttribute("MySession", mySession);
					//插入日记
					Log log=new Log();
					log.setUserId(list.get(0).getUserId());
					log.setTag("login");
					log.setDate(new Date());
					log.setSummary(list.get(0).getUserName()+"登陆系统！");
					log.setIp(getRequest().getLocalAddr());
					logManager.save(log);
					return INDEX_JSP;
				}else{
					showMessage("账号或密码错误！");
				}
			}else{
				showMessage("此账号已锁定，请联系管理人员协助！");
			}
		}else{
			showMessage("无此用户存在！");
		}
		
		return LOGIN_JSP;

	}
	
	public String logout(){
		
		HttpSession session=getRequest().getSession(false);
		ServletContext servletContext=getRequest().getServletContext();
		if(session!=null){
			//退出在线人员名单
			Application application=(Application) servletContext.getAttribute("Application");
			if(application!=null){
				MySession mySession=(MySession) session.getAttribute("MySession");
				application.getOnlineUser().remove(mySession.getUserinfo().getUserId());
				servletContext.setAttribute("Application", application);
			}
			//注销session
			session.removeAttribute("MySession");
		}
		return LOGIN_JSP;
	}
	public void getUserReport() throws IOException {
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
		  String title[] = {Userinfo.ALIAS_USER_ID,Userinfo.ALIAS_USER_NAME,"员工姓名",
					Userinfo.ALIAS_USER_TYPE_ID,Userinfo.ALIAS_IF_VALIDITY};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Userinfo> userList = userinfoManager.findAll();
		  Iterator it = userList.iterator();
		  while (it.hasNext()) {
			  Userinfo user =(Userinfo) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(user.getUserId()+"");
			  list.add(user.getUserName()+"");
			  list.add(user.getMeStIdModel().getModelTagValue()+"");
			  list.add(user.getUserTypeIdModel().getModelTagValue()+"");
			  if(user.getIfValidity()==1){
				  list.add("是");
			  }else{
				  list.add("否");
			  }
			  lists.add(list);
		}
	  
	  // 生成excel文件(保存在服务器机上)
	  
		  Excel.writeExcel(new FileOutputStream(f), title, lists);
		  Files.exportFile(getResponse(), f, true);
	  	} catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   showMessage2("导出excel失败！", "/pages/Userinfo/list.do");
	  }	
	}
	/**
	 * 查询用户角色
	 * @param userId
	 * @return
	 */
	public List<UserRoleInfo> getUserRoles(int userId){
		UserRoleInfoQuery query=new UserRoleInfoQuery();
		query.setUserId(userId);
		return userRoleInfoManager.findAll(query);
	}
}
