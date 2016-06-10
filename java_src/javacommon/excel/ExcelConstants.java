/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacommon.excel;

import org.apache.commons.io.FilenameUtils;

/**
 * Excel 常量
 *
 * @author xc
 * @version 1.0
 * @since 2013-12-03
 */
public class ExcelConstants {

    /**
     * 03版本及之前版本 Excel
     */
    public static final int EXCEL_XLS_VERSION = 0;
    /**
     * 07版本及后续版本 Excel
     */
    public static final int EXCEL_XLSX_VERSION = 1;

    /**
     * 默认日期格式
     */
    public static String[] DATE_PATTERNS = new String[]{
        "yyyy-MM-dd","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss", //        "yyyy/MM/dd","yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss",
    };

    /**
     * 获取Excel版本类型
     *
     * @param filename
     * @return Excel文件版本 。 0 : 1997版Excel(后缀xls);1 : 2007版Excel(后缀xlsx)
     */
    public static int getExcelType(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        if (extension.equalsIgnoreCase("xls")) {
            return EXCEL_XLS_VERSION;
        } else if (extension.equalsIgnoreCase("xlsx")) {
            return EXCEL_XLSX_VERSION;
        }
        return EXCEL_XLS_VERSION;
    }
}
