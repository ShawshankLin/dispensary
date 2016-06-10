/*
 */

package com.dispensary.project.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.dispensary.project.vo.query.*;

import javax.annotation.Resource;

public class MenuRoleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/MenuRole/query.jsp";
	protected static final String LIST_JSP= "/pages/MenuRole/list.jsp";
	protected static final String CREATE_JSP = "/pages/MenuRole/create.jsp";
	protected static final String EDIT_JSP = "/pages/MenuRole/edit.jsp";
	protected static final String SHOW_JSP = "/pages/MenuRole/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/MenuRole/list.do";
	
	private MenuRoleManager menuRoleManager;
	@Resource private MenuManager menuManager;
	@Resource private RoleManager roleManager;
	private MenuRole menuRole;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			menuRole = new MenuRole();
		} else {
			menuRole = (MenuRole)menuRoleManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setMenuRoleManager(MenuRoleManager manager) {
		this.menuRoleManager = manager;
	}	
	
	public Object getModel() {
		return menuRole;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		MenuRoleQuery query = newQuery(MenuRoleQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = menuRoleManager.findPage(query);
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
		/*MenuRole menuRole=new MenuRole();
		menuRole.setMenuId(6);
		menuRole.setRoleId(3);
		menuRoleManager.save(menuRole);
		//Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;*/
		String menuIds[] = null;
		try {
			
			String menuId = this.getRequest().getParameter("menuIds"); //获取所有没权限的菜单id
			String roleId = this.getRequest().getParameter("roleId");  //获取角色id
			System.out.println(menuId);
			System.out.println(roleId);
			
			if(menuId != null && !menuId.equals("")){
				menuIds = menuId.substring(0,menuId.length()-1).split(",");
				MenuRoleQuery query=new MenuRoleQuery();
				query.setRoleId(Integer.parseInt(roleId));
				//查出角色已有的菜单权限
				List<MenuRole> menuRoles=menuRoleManager.findAll(query);
				for(int i=0;i<menuRoles.size();i++){
					MenuRole menuRole=menuRoles.get(i);
					boolean flat=false;
					for(int j=0;j<menuIds.length;j++){
						if(menuRole.getMenuId().equals(menuIds[j])){
							flat=true;
							break;
						}
					}//删除不存在的
					if(!flat){
						menuRoleManager.removeById(menuRole.getId());
					}
				}
				
				//添加新增
				for(int i=0;i<menuIds.length;i++){
					MenuRoleQuery menuRoleQuery=new MenuRoleQuery();
					menuRoleQuery.setRoleId(Integer.parseInt(roleId));
					menuRoleQuery.setMenuId(Integer.parseInt(menuIds[i]));
					System.out.println(menuIds[i]);
					List<MenuRole> list=menuRoleManager.findAll(menuRoleQuery);
					if(list==null||list.size()==0){
						MenuRole menuRole=new MenuRole();
						menuRole.setRoleId(Integer.parseInt(roleId));
						menuRole.setMenuId(Integer.parseInt(menuIds[i]));
						menuRoleManager.save(menuRole);
					}
				}
				
			}
			
			this.getResponse().getWriter().write("ok");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		menuRoleManager.update(this.menuRole);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			menuRoleManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
		List<Menu> menuList =  menuManager.findAll();
		requestMap.put("menuList",menuList);
		List<Role> roleList =  roleManager.findAll();
		requestMap.put("roleList",roleList);
	}

}
