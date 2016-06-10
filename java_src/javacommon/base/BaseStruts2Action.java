package javacommon.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javacommon.util.ConvertRegisterHelper;
import javacommon.util.PageRequestFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.util.Assert;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.ObjectUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseStruts2Action extends ActionSupport implements RequestAware,SessionAware {
	protected final static String CREATED_SUCCESS = "创建成功";
	protected final static String UPDATE_SUCCESS = "更新成功";
	protected final static String DELETE_SUCCESS = "删除成功";
	protected Map<String, Object> session;
	protected Map requestMap = null;
	protected Integer globalMenuId;
	static {
		//注册converters
		ConvertRegisterHelper.registerConverters();
	}
	
	public void copyProperties(Object target,Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public <T> T copyProperties(Class<T> destClass,Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setRequest(Map request) {
		this.requestMap = request;
	}

	public void savePage(Page page,PageRequest pageRequest){
		savePage("",page,pageRequest);
	}
	
	/**
	 * 用于一个页面有多个extremeTable是使用
	 * @param tableId 等于extremeTable的tableId属性
	 */
	public void savePage(String tableId,Page page,PageRequest pageRequest){
		Assert.notNull(tableId,"tableId must be not null");
		Assert.notNull(page,"page must be not null");
		
		getRequest().setAttribute(tableId+"page", page);
		getRequest().setAttribute(tableId+"totalRows", new Integer(page.getTotalCount()));
		getRequest().setAttribute(tableId+"pageRequest", pageRequest);
		getRequest().setAttribute(tableId+"query", pageRequest);
	}
	
	public <T extends PageRequest> T newQuery(Class<T> queryClazz,String defaultSortColumns){
		PageRequest query = PageRequestFactory.bindPageRequest(org.springframework.beans.BeanUtils.instantiateClass(queryClazz),ServletActionContext.getRequest(),defaultSortColumns);
		return (T)query;
    }
	
	public boolean isNullOrEmptyString(Object o) {
		return ObjectUtils.isNullOrEmptyString(o);
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	protected void setRelative(Object target,String property){
		String paramName = property.replace('.', '_');
		String value = this.getRequest().getParameter(paramName);
		BeanUtils.setProperty(target, property, value);
	}
	
	protected void showMessage(String message){
		getResponse().setCharacterEncoding("utf-8");
		getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = getResponse().getWriter();
			//writer.println("<script type=\"text/javascript\">var html=\"内容\";var div = document.createElement(\"<div></div>\";div.document.open();div.document.write(html);div.document.close();document.body.appendChild(div);</script>");
			writer.println("<script>alert('"+message+"');window.history.go(-1);</script>");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void showMessage2(String message,String url){
		getResponse().setCharacterEncoding("utf-8");
		getResponse().setContentType("text/html;charset=UTF-8");
		String appName=getRequest().getServletContext().getContextPath();
		PrintWriter writer = null;
		try {
			writer = getResponse().getWriter();
			writer.println("<script>alert('"+message+"');window.location.href='"+appName+url+"';</script>");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void sendRedirect(String location){
		String contextPath = getRequest().getContextPath();
		try {
			this.getResponse().sendRedirect(contextPath+location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected String uploadFile(File uploadFile,String uploadFileName,String uploadDir){
		if(uploadFile==null) return null;
		String realUploadDir = ServletActionContext.getServletContext().getRealPath(uploadDir);
		String fileName = UUID.randomUUID().toString();
		String extString = FilenameUtils.getExtension(uploadFileName);
		File tarFile = new File(realUploadDir,fileName+"."+extString);
		try {
			System.out.println("将上传文件保存于"+tarFile.getPath());
			FileUtils.copyFile(uploadFile, tarFile);
			return tarFile.getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	protected void deleteUploadFile(String uploadDir,String fileName){
		String realUploadDir = ServletActionContext.getServletContext().getRealPath(uploadDir);
		File tarFile = new File(realUploadDir,fileName);
		tarFile.delete();
	}
	private String getIpAddr(HttpServletRequest request) {   
		String ip = request.getHeader("X-Forwarded-For");
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_CLIENT_IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getRemoteAddr();
		    }
		    return ip;
 
    } 
	
	public void setGlobalMenuId(Integer globalMenuId) {
		this.globalMenuId = globalMenuId;
	}

	public Integer getGlobalMenuId() {
		return globalMenuId;
	}
	
}

