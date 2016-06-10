/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javacommon.excel;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Excel输出类
 * @author xc
 */
public interface Writer {
    /**
     * 创建表格
     * @return 
     */
    Sheet createSheet();
    /**
     * 创建表格
     * @param name
     * @return 
     */
    Sheet createSheet(String name);

    /**
     * 输出
     * @param out 输出位置
     * @throws IOException
     */
    void flush(OutputStream out) throws IOException;

    /**
     * 写单元格
     * @param row
     * @param value
     * @param index
     */
    void writeCell(Row row, Object value, int index);
    /**
     * 写行数据
     * @param sheet
     * @param data 
     */
    void writeRow(Sheet sheet, Object[] data,int index);
}
