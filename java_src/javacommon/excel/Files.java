package javacommon.excel;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class Files {
 
 /**
  * 向客户端下载文件,弹出下载框.
  * 
  * @param response(HttpServletResponse)
  * @param file(需要下载的文件)
  * @param isDel(下载完成后是否删除该文件)
  * @throws IOException 
  */
 public static void exportFile(HttpServletResponse response, File file, boolean isDel) throws IOException {
	  OutputStream out = null;
	  InputStream in = null;
	 
	  // 获得文件名
	  String filename = URLEncoder.encode(file.getName(), "UTF-8"); 
	  // 定义输出类型(下载)
	  // response.setContentType("application/force-download"); 
	  response.setHeader("Location", filename);
	     // 定义输出文件头
	  response.setHeader("Content-Disposition", "attachment;filename=" + filename); 
	  response.setContentType("application/x-download");
	  out = response.getOutputStream();
	  in = new FileInputStream(file.getPath());
	  byte[] buffer = new byte[1024];
	  int i = -1;
	  while ((i = in.read(buffer)) != -1) {
	    out.write(buffer, 0, i);
	  }
	  
	  in.close();
	  out.close();  
	  
	  if (isDel) {
	   //删除文件,删除前关闭所有的Stream.
	   file.delete();
	  }
  
 }
/* public static void downloadFile(String path, String fileName) throws IOException {
     // 获得JSF上下文环境
     FacesContext context = FacesContext.getCurrentInstance();
     // 获得ServletContext对象
     ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
     // 取得文件的绝对路径
     HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
     downloadFile(httpServletResponse, path, fileName);
     FacesContext.getCurrentInstance().responseComplete();
 }

 public static void downloadFile(HttpServletResponse response,
         String realName, String fileName) throws IOException{
     response.setHeader("Content-disposition", "attachment; filename="
             + fileName);
     response.setContentType("application/x-download");
     //File exportFile = new File(realName);
     //response.setContentLength((int) exportFile.length());
     ServletOutputStream servletOutputStream = response.getOutputStream();
     byte[] b = new byte[1024];
     int i = 0;
     FileInputStream fis = new java.io.FileInputStream(realName);
     while ((i = fis.read(b)) > 0) {
         servletOutputStream.write(b, 0, i);
     }
 }*/
}