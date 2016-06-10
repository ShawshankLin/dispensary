/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javacommon.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel工具类
 * 支持2003版本以及2007以后版本
 * @author xc
 * @version 1.0
 * @since 2013-12-03
 */
public class ExcelUtils {
    /**
     * 根据文件名获取Excel版本
     * @param fileName
     * @return 
     */
    public static int getVersionByFileName(String fileName){
        String extension = FilenameUtils.getExtension(fileName);
        if("xls".equalsIgnoreCase(extension))
            return ExcelConstants.EXCEL_XLS_VERSION;
        else if("xlsx".equalsIgnoreCase(extension))
            return ExcelConstants.EXCEL_XLSX_VERSION;
        else
            throw new IllegalArgumentException("文件格式不正确");
    }
    
     /**
     * 读取Excel
     * @param type excel版本0:xls,1:xlsx
     * @param in 
     */
    public static void read(String filename,InputStream in,Handler handler) throws IOException{
        read(getVersionByFileName(filename),in,handler);
    }
    /**
     * 读取Excel
     * @param filename
     * @param file 文件 
     * @param handler
     * @throws IOException 
     */
    public static void read(File file,Handler handler) throws IOException{
        Reader reader = ReaderFactory.getReader(file);
        handler.handle(reader);
    }
    /**
     * 读取Excel
     * @param type excel版本0:xls,1:xlsx
     * @param in 
     */
    public static void read(int type,InputStream in,Handler handler) throws IOException{
        Reader reader = ReaderFactory.getReader(type, in);
        handler.handle(reader);
    }
    /**
     * 读取Excel
     * @param type excel版本0:xls,1:xlsx
     * @param contents
     * @param handler
     * @throws IOException 
     */
    public static void read(String filename,byte[] contents,Handler handler) throws IOException{
        read(getVersionByFileName(filename),contents,handler);
    }
    /**
     * 读取Excel
     * @param type excel版本0:xls,1:xlsx
     * @param contents
     * @param handler
     * @throws IOException 
     */
    public static void read(int type,byte[] contents,Handler handler) throws IOException{
        read(type, new ByteArrayInputStream(contents), handler);
    }
    /**
     * 读取Excel
     * @param type excel版本0:xls,1:xlsx
     * @param url
     * @param handler
     * @throws IOException 
     */
    public static void read(int type,URL url,Handler handler) throws IOException{
        read(type, url.openStream(), handler);
    }
    /**
     * 输出数据
     * @param file 输出文件
     * @param data 输出数据
     */
    public static <T> void write(File file,List<T[]> data)throws IOException{        
         Writer w = new ExcelWriter(getWorkBook(file.getName()));
         String sheetName = FilenameUtils.getBaseName(file.getName());
         Sheet sheet = w.createSheet(sheetName);
         for(int i = 0 ;i < data.size();i++){
            w.writeRow(sheet, data.get(i),i);
         }
         OutputStream out = null;
         try{
            out = FileUtils.openOutputStream(file);
            w.flush(out);            
         }finally{
             IOUtils.closeQuietly(out);
         }
    }
    
    /**
     * 获取workbook
     * @param type 
     * @param in
     * @return
     * @throws IOException 
     */
    private static Workbook getWorkBook(String fielname) throws IOException {
        int type = getVersionByFileName(fielname);
        Workbook book = null;
        if (type == ExcelConstants.EXCEL_XLS_VERSION) {
            book = new HSSFWorkbook();
        } else if (type == ExcelConstants.EXCEL_XLSX_VERSION) {
            book = new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("格式不正确!");
        }
        return book;
    }
}
