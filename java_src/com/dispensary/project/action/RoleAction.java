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
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javacommon.base.BaseStruts2Action;
import javacommon.excel.Excel;
import javacommon.excel.Files;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.dispensary.project.model.Menu;
import com.dispensary.project.model.MenuRole;
import com.dispensary.project.model.Role;
import com.dispensary.project.service.MenuManager;
import com.dispensary.project.service.MenuRoleManager;
import com.dispensary.project.service.RoleManager;
import com.dispensary.project.utils.FunctionLib;
import com.dispensary.project.utils.TreeNode;
import com.dispensary.project.vo.query.MenuQuery;
import com.dispensary.project.vo.query.MenuRoleQuery;
import com.dispensary.project.vo.query.RoleQuery;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class RoleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Role/query.jsp";
	protected static final String LIST_JSP= "/pages/Role/list.jsp";
	protected static final String CREATE_JSP = "/pages/Role/create.jsp";
	protected static final String EDIT_JSP = "/pages/Role/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Role/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Role/list.do";
	
	private RoleManager roleManager;
	@Resource private MenuRoleManager menuRoleManager;
	@Resource private MenuManager menuManager;
	private Role role;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		role = new Role();
		/*if (isNullOrEmptyString(id)) {
			role = new Role();
		} else {
			role = (Role)roleManager.getById(id);
		}*/
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRoleManager(RoleManager manager) {
		this.roleManager = manager;
	}	
	
	public Object getModel() {
		return role;
	}
	
	public void setRoleId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		RoleQuery query = newQuery(RoleQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = roleManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	/** 执行搜索 */
	public String search(){
		RoleQuery roleQuery=new RoleQuery();
		String searchType=getRequest().getParameter("searchType");
		String searchInfo=getRequest().getParameter("searchInfo").trim();
		try {
			searchInfo=java.net.URLDecoder.decode(searchInfo,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null!=searchInfo&&!searchInfo.equals("")){
			if("roleId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					roleQuery.setRoleId(Integer.parseInt(searchInfo));
				}
			}
			if("roleName".equals(searchType)){
				roleQuery.setRoleName(searchInfo);
			}
		}
		Page page = roleManager.findPage(roleQuery);
		savePage(page,roleQuery);
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
		roleManager.save(role);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String roleId=getRequest().getParameter("roleId");
		if(roleId!=null){
			Role role=roleManager.getById(Integer.parseInt(roleId));
			getRequest().setAttribute("role", role);
		}	
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		roleManager.update(this.role);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String roleId=getRequest().getParameter("roleId");
		if(roleId!=null){
			roleManager.removeById(Integer.parseInt(roleId));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("roleId"));
			roleManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	public void getRelative(){
	}

	/* 执行查找角色信息 */
	public String getRole() {	
		RoleQuery roleQuery = new RoleQuery();
		roleQuery.setRoleId(role.getRoleId());
		List<Role> list = roleManager.findAll(roleQuery);
		Role role = null;
		PrintWriter out=null;
		try {
			out = getResponse().getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 1) {
			role = (Role)list.get(0);
			JSONObject jsonobj = JSONObject.fromObject(role);
			//System.out.println(jsonobj.toString());
			out.write(jsonobj.toString());
		}else{
			out.write("alert('无此角色信息')");
		}
		return null;
	}
	/** 查询所有角色信息 */
	public String getRoles(){		
		List<Role> list = roleManager.findAll();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Role role = list.get(i);
			RoleQuery query = new RoleQuery();
			BeanUtils.copyProperties(query, role);
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
	public void getRoleReport() throws IOException {
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
		  String title[] = {Role.ALIAS_ROLE_ID,Role.ALIAS_ROLE_NAME,Role.ALIAS_ROLE_DESCRIBE,};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Role> roleList = roleManager.findAll();
		  Iterator it = roleList.iterator();
		  while (it.hasNext()) {
			  Role role =(Role) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(role.getRoleId()+"");
			  list.add(role.getRoleName()+"");
			  list.add(role.getRoleDescribe()+"");
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
	
	
	
	
	public String loadMenu(){
		
		try {
			this.getResponse().setCharacterEncoding("UTF-8");  
			PrintWriter out=this.getResponse().getWriter();
			
			//获取角色Id
			String roleId=this.getRequest().getParameter("roleId");
			
			//获取所有菜单
			List<Menu> listmenu = this.menuManager.findAll();
			
			List<TreeNode> list=new ArrayList<TreeNode>();  
			
			list.add(new TreeNode(0,0,"菜单目录",false,true,"0"));
			if(roleId!=null){
				//根据角色id，获取到已经被关联的信息
				MenuRoleQuery menuRoleQuery=new MenuRoleQuery();
				menuRoleQuery.setRoleId(Integer.parseInt(roleId));
				List<MenuRole> listmenu2 = this.menuRoleManager.findAll(menuRoleQuery);
				for(int i=0;i<listmenu.size();i++){
					Menu menu=listmenu.get(i);
					boolean flat=false;
					for(int j=0;j<listmenu2.size();j++){  //判断该节点是否已选
    					if(menu.getMenuId().equals(listmenu2.get(j).getMenuIdModel().getMenuId())){
    						
    						if(menu.getParent() == null){   //如果是根目录 
    			        		list.add(new TreeNode(menu.getMenuNo(),0,menu.getMenuName(),true,true,menu.getMenuId().toString()));
    			        	}else{
    			        		MenuQuery menuQuery=new MenuQuery();
    			        		menuQuery.setParent(menu.getMenuId());
    			        		List childMenuList = this.menuManager.findAll(menuQuery);
    			        		if(childMenuList.size() == 0){  //如果是叶子节点
    			        			list.add(new TreeNode(menu.getMenuNo(),menu.getParentModel().getMenuNo(),menu.getMenuName(),true,false,menu.getMenuId().toString()));    			        			
    			        		}else{  //如果不是叶子节点
    			        			list.add(new TreeNode(menu.getMenuNo(),menu.getParentModel().getMenuNo(),menu.getMenuName(),true,true,menu.getMenuId().toString()));
    			        		}
    			        	}
    						flat=true;
    						break;
    					}
    				}
					if(!flat){
						if(menu.getParent() == null){   //如果是根目录 
			        		list.add(new TreeNode(menu.getMenuNo(),0,menu.getMenuName(),false,true,"0"));
			        	}else{
			        		MenuQuery menuQuery=new MenuQuery();
			        		menuQuery.setParent(menu.getMenuId());
			        		List childMenuList = this.menuManager.findAll(menuQuery);
			        		if(childMenuList.size() == 0){  //如果是叶子节点
			        			list.add(new TreeNode(menu.getMenuNo(),menu.getParentModel().getMenuNo(),menu.getMenuName(),false,false,menu.getMenuId().toString()));    			        			
			        		}else{  //如果不是叶子节点
			        			list.add(new TreeNode(menu.getMenuNo(),menu.getParentModel().getMenuNo(),menu.getMenuName(),false,true,"0"));
			        		}
			        	}
					}
		        	
				}
			}
			
			
			
			
			String jsonString =new Gson().toJson(list);  
	        System.out.println("json:"+jsonString);
			out.println(jsonString);  
	        out.flush();  
	        out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
