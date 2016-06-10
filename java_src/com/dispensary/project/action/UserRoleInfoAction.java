/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.io.PrintWriter;
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

public class UserRoleInfoAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/UserRoleInfo/query.jsp";
	protected static final String LIST_JSP= "/pages/UserRoleInfo/list.jsp";
	protected static final String CREATE_JSP = "/pages/UserRoleInfo/create.jsp";
	protected static final String EDIT_JSP = "/pages/UserRoleInfo/edit.jsp";
	protected static final String SHOW_JSP = "/pages/UserRoleInfo/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/UserRoleInfo/list.do";
	
	private UserRoleInfoManager userRoleInfoManager;
	@Resource private UserinfoManager userinfoManager;
	@Resource private RoleManager roleManager;
	private UserRoleInfo userRoleInfo;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		userRoleInfo = new UserRoleInfo();
		/*if (isNullOrEmptyString(id)) {
			userRoleInfo = new UserRoleInfo();
		} else {
			userRoleInfo = (UserRoleInfo)userRoleInfoManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserRoleInfoManager(UserRoleInfoManager manager) {
		this.userRoleInfoManager = manager;
	}	
	
	public Object getModel() {
		return userRoleInfo;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UserRoleInfoQuery query = newQuery(UserRoleInfoQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = userRoleInfoManager.findPage(query);
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
		userRoleInfoManager.save(userRoleInfo);
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
		userRoleInfoManager.update(this.userRoleInfo);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			userRoleInfoManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Userinfo> userinfoList =  userinfoManager.findAll();
		requestMap.put("userinfoList",userinfoList);
		List<Role> roleList =  roleManager.findAll();
		requestMap.put("roleList",roleList);
	}
	/** 执行查找用户角色 */
	public String getUserRoles(){	
		UserRoleInfoQuery userRoleInfoQuery=new UserRoleInfoQuery();
		userRoleInfoQuery.setUserId(userRoleInfo.getUserId());
		List<UserRoleInfo> list = userRoleInfoManager.findAll(userRoleInfoQuery);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			UserRoleInfo userRoleInfo = list.get(i);
			UserRoleInfoQuery query = new UserRoleInfoQuery();
			BeanUtils.copyProperties(query, userRoleInfo);
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
