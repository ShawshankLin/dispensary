/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacommon.excel;

import java.util.List;

/**
 * Excel读接口
 * @author xc
 * @version 1.0
 * @since 2013-12-03
 */
public interface Reader {
    /**
     * 获取表格数
     * @return 
     */
    public int getSheets();
    /**
     * 获取总行数
     * @return 
     */
    public int getTotalRows();
    /**
     * 获取单个表格行数
     * @param sheet
     * @return 
     */
    public int getTotalRows(int sheet);
    /**
     * 获取最大列
     * @param sheetIndex
     * @return 
     */
    public int getMaxColumn(int sheetIndex);
    
    /**
     * 行最大列数
     * @param sheetIndex 表格下标
     * @param row  行下标
     * @return 
     */
    public int getMaxColumn(int sheetIndex,int row);
    
    /**
     * 获取行字符串数据
     * @param sheetIndex
     * @param rowIndex
     * @return 
     */
    public List<String> getRowStingData(int sheetIndex, int rowIndex);
    
    /**
     * 获取Excel字符串类型行中指定列的数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param colBegin 列下标从0开始
     * @param colEnd 列下标从0开始
     * @return 行数据
     */
    public List<String> getRowStingData(int sheetIndex, int rowIndex, int colBegin, int colEnd);
    
    /**
     * 获取行数据
     * @param sheetIndex
     * @param rowIndex
     * @return 
     */
    public List<Object> getRowData(int sheetIndex, int rowIndex);
    /**
     * 是否空行
     * @param sheetIndex
     * @param rowIndex
     * @return 
     */
    public boolean isBlankRow(int sheetIndex, int rowIndex);
    
     /**
     * 获取单元格数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param columnIndex 列下标从0开始
     * @return
     */
    public Object getCellValue(int sheetIndex, int rowIndex, int columnIndex);
    
     /**
     * 获取单元格字符串数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param columnIndex 列下标从0开始
     * @return
     */
    public String getCellStringValue(int sheetIndex, int rowIndex, int columnIndex);

    /**
     * 获取列宽度
     * @param sheetIndex
     * @param columnIndex
     * @return
     */
    int getColumnWidth(int sheetIndex, int columnIndex);

    /**
     * 获取列宽度
     * @param sheetIndex
     * @return
     */
    List<Integer> getColumnWidths(int sheetIndex);

}
