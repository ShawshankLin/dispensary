/*
 */

package com.dispensary.project.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

public class NoticeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/Notice/query.jsp";
	protected static final String LIST_JSP= "/pages/Notice/list.jsp";
	protected static final String CREATE_JSP = "/pages/Notice/create.jsp";
	protected static final String EDIT_JSP = "/pages/Notice/edit.jsp";
	protected static final String SHOW_JSP = "/pages/Notice/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/Notice/list.do";
	
	private NoticeManager noticeManager;
	@Resource private UserinfoManager userinfoManager;
	private Notice notice;
	java.lang.Integer id = null;
	private String[] items;
	//获得上传文件
	private File upload;
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	//获得上传文件名
	private String uploadFileName;

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			notice = new Notice();
		} else {
			notice = (Notice)noticeManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setNoticeManager(NoticeManager manager) {
		this.noticeManager = manager;
	}	
	
	public Object getModel() {
		return notice;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		NoticeQuery query = newQuery(NoticeQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = noticeManager.findPage(query);
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
	public String save(){
		MySession mySession=(MySession)getRequest().getSession().getAttribute("MySession");
		if(mySession!=null&&mySession.getUserinfo()!=null){
			notice.setUserId(mySession.getUserinfo().getUserId());
		}
		notice.setDate(new Date());
		notice.setStatus(1);
		//添加附件
		String savePath="temp\\notice\\";
		String tarFileName=this.uploadFile(this.upload, this.uploadFileName, savePath);
		notice.setAttachment(tarFileName);
		notice.setFileName(this.uploadFileName);
		noticeManager.save(notice);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	/**下载文件*/
	public String downloadFile(){
		try {
			String fileName = new String(getRequest().getParameter("fileName").getBytes("ISO8859-1"),"utf-8");
			String filePath = getRequest().getParameter("filePath");
			getResponse().setContentType("text/plain");
			getResponse().setHeader("Location", fileName);
			getResponse().setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			OutputStream outputStream = getResponse().getOutputStream();
			filePath=getRequest().getServletContext().getRealPath("\\temp\\"+filePath)+"\\";
			InputStream inputStream = new FileInputStream(filePath
					+ fileName);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e1) {
			showMessage("没有找到您要的文件");
		} catch (Exception e) {
			showMessage("系统错误，请及时与管理员联系");
		}
		return null;
	}
	
	/**进入更新页面*/
	public String edit() {
		getRelative();
		String id=getRequest().getParameter("id");
		if(id!=null){
			Notice notice=noticeManager.getById(Integer.parseInt(id));
			getRequest().setAttribute("notice", notice);
		}	
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		noticeManager.update(this.notice);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delete(){
		String id=getRequest().getParameter("id");
		if(id!=null){
			Notice notice=noticeManager.getById(Integer.parseInt(id));
			if(notice.getAttachment()!=null){
				String uploadDir="\\temp\\notice";
				deleteUploadFile(uploadDir, notice.getAttachment());
			}
			noticeManager.removeById(Integer.parseInt(id));
		}
		//showMessage("删除成功！");
		return LIST_ACTION;
	}
	/**删除对象*/
	public String delBatch() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			noticeManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	/** 执行搜索 */
	public String search(){
		NoticeQuery noticeQuery=new NoticeQuery();
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
					noticeQuery.setId(Integer.parseInt(searchInfo));
				}
			}
			if("userId".equals(searchType)){
				if(FunctionLib.isNum(searchInfo)){
					noticeQuery.setUserId(Integer.parseInt(searchInfo));
				}
			}
			if("userName".equals(searchType)){
				noticeQuery.setUserIdModelTag(searchInfo);
			}
			if("title".equals(searchType)){
				noticeQuery.setTitle(searchInfo);
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
				noticeQuery.setDateString(date);
			}
		}
		Page page = noticeManager.findPage(noticeQuery);
		savePage(page,noticeQuery);
		return LIST_JSP;
	}
	public void getRelative(){
		List<Userinfo> userinfoList =  userinfoManager.findAll();
		requestMap.put("userinfoList",userinfoList);
	}
	public void getNoticeReport() throws IOException {
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
		  String title[] = {Notice.ALIAS_USER_ID,Notice.ALIAS_TITLE,Notice.ALIAS_CONTENT,
					Notice.ALIAS_ATTACHMENT,Notice.ALIAS_DATE,Notice.ALIAS_STATUS};
		  List<List> lists = new ArrayList<List>();
		  //组成list
		  List<Notice> noticeList = noticeManager.findAll();
		  Iterator it = noticeList.iterator();
		  while (it.hasNext()) {
			  Notice Notice =(Notice) it.next();
			  List<String> list = new ArrayList<String>();
			  list.add(Notice.getUserIdModel().getModelTagValue()+"");
			  list.add(Notice.getTitle()+"");
			  list.add(Notice.getContent()+"");
			  list.add(Notice.getAttachment()+"");
			  list.add(Notice.getDateString()+"");
			  list.add(Notice.getStatus()+"");
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
