/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacommon.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import jxl.read.biff.BiffRecordReader;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel读取接口工厂类
 * @author xc 
 * @version  1.0
 * @since  2013-12-3
 */
public class ReaderFactory {

    /**
     * 获取Excel读取接口
     * @see ExcelConstants
     * @param type Excel版本，2003以及之前的xlx,2007以及以后xlsx
     * @param in excel数据流
     * @return Excel读取接口
     * @throws IOException
     */
    public static Reader getReader(int type, InputStream in) throws IOException {
        return new ExcelReader(getWorkBook(type, in));
    }    
    
    /**
     * 获取Excel读取接口
     * @see ExcelConstants
     * @param type Excel版本，2003以及之前的xlx,2007以及以后xlsx
     * @param file excel文件
     * @return Excel读取接口
     * @throws IOException
     */
    public static Reader getReader(File file) throws IOException {
        return new ExcelReader(getWorkBook(ExcelUtils.getVersionByFileName(file.getName()),FileUtils.openInputStream(file)));
    }
    /**
     * 获取workbook
     * @param type 
     * @param in
     * @return
     * @throws IOException 
     */
    private static Workbook getWorkBook(int type, InputStream in) throws IOException {
        Workbook book = null;
        if (type == ExcelConstants.EXCEL_XLS_VERSION) {
            book = new HSSFWorkbook(in);
        } else if (type == ExcelConstants.EXCEL_XLSX_VERSION) {
            book = new XSSFWorkbook(in);
        } else {
            throw new IllegalArgumentException("格式不正确!");
        }
        return book;
    }
}
