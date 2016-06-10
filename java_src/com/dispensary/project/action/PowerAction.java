/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class PowerAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Power/query.jsp";
	protected static final String LIST_JSP= "/pages/Power/list.jsp";
	protected static final String CREATE_JSP = "/pages/Power/create.jsp";
	protected static final String EDIT_JSP = "/pages/Power/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Power/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Power/list.do";
	
	private PowerManager powerManager;
	private Power power;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		power = new Power();
		/*if (isNullOrEmptyString(id)) {
			power = new Power();
		} else {
			power = (Power)powerManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setPowerManager(PowerManager manager) {
		this.powerManager = manager;
	}	
	
	public Object getModel() {
		return power;
	}
	
	public void setPowerId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		PowerQuery query = newQuery(PowerQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = powerManager.findPage(query);
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
		powerManager.save(power);
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
		powerManager.update(this.power);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("powerId"));
			powerManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	
	}

	/* 执行查找权限信息 */
	public String getPower() {	
		PowerQuery powerQuery = new PowerQuery();
		powerQuery.setPowerId(power.getPowerId());
		List<Power> list = powerManager.findAll(powerQuery);
		Power power = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			power = (Power)list.get(0);
			JSONObject jsonobj = JSONObject.fromObject(power);
			System.out.println(jsonobj.toString());
			out.write(jsonobj.toString());
		}else{
			out.write("alert('无此权限信息')");
		}
		return null;
	}
}
