/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacommon.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel 读取类 支持03以及07版本Excel
 *
 * @author xc
 */
public class ExcelWriter implements Writer{
    
    /**
     * Excel数据
     */
    private Workbook workbook;

    public ExcelWriter(Workbook workbook) {
        this.workbook = workbook;        
    }
    /**
     * 输出
     * @param out 输出位置
     * @throws IOException 
     */
    public void flush(OutputStream out) throws IOException {
        workbook.write(out);        
    }    
    
    public Sheet createSheet(){
        return workbook.createSheet();
    }
    public Sheet createSheet(String name){
        return workbook.createSheet(name);
    }
    
    public void writeRow(Sheet sheet,Object[] data,int index){
        Row row = sheet.createRow(index);
        for(int i = 0 ;i < data.length;i++){
            writeCell(row, data[i],i);
        }
    }
        
    /**
     * 写单元格
     * @param row
     * @param value
     * @param index 
     */
    public void writeCell(Row row,Object value,int index){
        if(value != null){
            Cell cell = row.createCell(index);
            if(value instanceof Date){
                CellStyle style = workbook.createCellStyle();
                DataFormat format= workbook.createDataFormat();                
                style.setDataFormat(format.getFormat("yyyy-MM-dd"));
                cell.setCellStyle(style);
                cell.setCellValue((Date)value);
            }else if(value instanceof Calendar){
                cell.setCellValue((Calendar)value);
                CellStyle style = workbook.createCellStyle();
                DataFormat format= workbook.createDataFormat();                
                style.setDataFormat(format.getFormat("yyyy-MM-dd"));
                cell.setCellStyle(style);
            }else if(value  instanceof Double){
                cell.setCellValue((Double)value);
            }else if(value instanceof Short){
                cell.setCellValue((Short)value);
            }else if(value instanceof Integer){
                cell.setCellValue((Integer)value);
            }else if(value instanceof Float){
                cell.setCellValue((Float)value);
            }else if(value instanceof String){
                cell.setCellValue((String)value);
            }else if(value instanceof StringBuilder){
                cell.setCellValue(value.toString());
            }else if(value instanceof BigDecimal){
                cell.setCellValue(((BigDecimal)value).doubleValue());
            }
        }
    }
}
