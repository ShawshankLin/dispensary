/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacommon.excel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel 读取类 支持03以及07版本Excel
 *
 * @author xc
 */
public class ExcelReader implements Reader{
    
    /**
     * Excel数据
     */
    private Workbook workbook;
    /**
     * 表格个数
     */
    private int sheets;

    public ExcelReader(Workbook workbook) {
        this.workbook = workbook;
        init();
    }

    /**
     * 获取表格个数
     *
     * @return
     */
    public int getSheets() {
        return sheets;
    }

    /**
     * 获取Excel所有Sheet的行数
     *
     * @return
     */
    public int getTotalRows() {
        int total = 0;
        for (int i = 0; i < sheets; i++) {
            total += getTotalRows(i);
        }
        return total;
    }

    /**
     * 获取工作薄单个表格的总行数
     *
     * @param sheetIndex 表格下标从0开始
     * @return
     */
    public int getTotalRows(int sheetIndex) {
        if (sheetIndex < sheets) {
            return workbook.getSheetAt(sheetIndex).getLastRowNum() + 1;
        }
        return 0;
    }
    /**
     * 行最大列数
     * @param sheetIndex 表格下标
     * @param row  行下标
     * @return 
     */
    public int getMaxColumn(int sheetIndex,int row){
        int cols = 0;
        if(sheetIndex < getSheets() && row < getTotalRows(sheetIndex)){
            return workbook.getSheetAt(sheetIndex).getRow(row).getLastCellNum()+1;
        }
        return cols;
    }

    /**
     * 获取表格最大列数
     *
     * @param sheetIndex
     * @return
     */
    public int getMaxColumn(int sheetIndex) {
        if (sheetIndex >= sheets) {
            return 0;
        }
        int maxCol = 0;
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int maxRows = sheet.getLastRowNum();
        int cols = 0;
        Row row = null;
        for (int i = 0; i < maxRows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                cols = row.getLastCellNum();
                if (cols > maxCol) {
                    maxCol = cols;
                }
            }
        }
        return maxCol + 1;
    }

    /**
     * 是否为空白行
     *
     * @param sheetIndex
     * @param rowIndex
     * @return
     */
    public boolean isBlankRow(int sheetIndex, int rowIndex) {
        Row row = getRow(sheetIndex, rowIndex);
        if (row == null) {
            return true;
        }
        int cols = row.getLastCellNum();    //行的最大列数
        boolean isBlank = true;//是否为空白行
        String str = null;
        for (int i = 0; i <= cols; i++) {
            str = getCellStringValue(row.getCell(i));
            if (str != null && str.trim().length() > 0) {
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }
    /**
     * 获取列宽度
     * @param sheetIndex
     * @return 
     */
    public List<Integer> getColumnWidths(int sheetIndex){
        int rowCount = getTotalRows(sheetIndex);
        if(rowCount > 0){
            Row row = workbook.getSheetAt(sheetIndex).getRow(0);
             int cols = row.getLastCellNum();    //行的最大列数
             List<Integer> result = new ArrayList<Integer>(cols);            
            for (int i = 0; i < cols; i++) {
                result.add(getColumnWidth(sheetIndex, i));
            }
            return result;
        }
        return null;
    }
    /**
     * 获取列宽度
     * @param sheetIndex
     * @param columnIndex
     * @return 
     */
    public int getColumnWidth(int sheetIndex,int columnIndex){
        return workbook.getSheetAt(sheetIndex).getColumnWidth(columnIndex) * 8/256;
    }
    /**
     * 获取Excel字符串类型行数据 当为空白行时返回Null
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @return 行数据
     */
    public List<String> getRowStingData(int sheetIndex, int rowIndex) {
        List<String> result = new Vector<String>();
        Row row = getRow(sheetIndex, rowIndex);
        if (row == null) {
            return null;
        }
        int cols = row.getLastCellNum();    //行的最大列数
        boolean isBlank = true;//是否为空白行
        String str = null;
        for (int i = 0; i < cols; i++) {
            str = getCellStringValue(row.getCell(i));
            if (str != null && str.trim().length() > 0) {
                isBlank = false;
            }
            result.add(str);
        }
        return isBlank ? null : result;
    }

    /**
     * 获取Excel字符串类型行中指定列的数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param colBegin 列下标从0开始
     * @param colEnd 列下标从0开始
     * @return 行数据
     */
    public List<String> getRowStingData(int sheetIndex, int rowIndex, int colBegin, int colEnd) {
        List<String> result = new Vector<String>();
        Row row = getRow(sheetIndex, rowIndex);
        if (row == null) {
            return null;
        }
        int cols = row.getLastCellNum();    //行的最大列数
        if(colEnd > cols)
            colEnd = cols;
        String str = null;
        int i = colBegin;
        int emptyCount = 0;
        for (i = 0; i <= colEnd; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                str = getCellStringValue(cell);
                if ("".equals(str)) {
                    emptyCount++;
                }
            } else {
                emptyCount++;
                str = "";
            }
            result.add(str);
        }
        if (emptyCount == colEnd + 1) {
            return null;
        }
        return result;
    }

    /**
     * 获取Excel行数据 当为空白行时返回Null
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @return 行数据
     */
    public List<Object> getRowData(int sheetIndex, int rowIndex) {
        Row row = getRow(sheetIndex, rowIndex);

        if (row == null) {
            return null;
        }
        List<Object> result = new Vector<Object>();
        int cols = row.getLastCellNum(); //行的最大列数
        boolean isBlank = true;          //是否为空白行
        Object obj = null;
        for (int i = 0; i <= cols; i++) {
            obj = getCellValue(row.getCell(i));
            result.add(obj);
            if (obj != null) {
                isBlank = false;
            }
        }
        return isBlank ? null : result;
    }
    
    /**
     * 获取单元格数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param columnIndex 列下标从0开始
     * @return
     */
    public Object getCellValue(int sheetIndex, int rowIndex, int columnIndex) {
        Row row = getRow(sheetIndex, rowIndex);
        if (row != null && columnIndex <= row.getLastCellNum()) {
            return getCellValue(row.getCell(columnIndex));
        }
        return null;
    }

    /**
     * 获取单元格字符串数据
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @param columnIndex 列下标从0开始
     * @return
     */
    public String getCellStringValue(int sheetIndex, int rowIndex, int columnIndex) {
        Row row = getRow(sheetIndex, rowIndex);
        if (row != null && columnIndex <= row.getLastCellNum()) {
            return getCellStringValue(row.getCell(columnIndex));
        }
        return null;
    }

    /**
     * 初始化Excel内容
     *
     * @param in
     */
    private void init() {        
        sheets = this.workbook.getNumberOfSheets();
    }

    /**
     * 获取单元格数据
     *
     * @param c 单元格
     * @return
     */
    private String getCellStringValue(Cell c) {
        if (c == null) {
            return "";
        }
        String value = null;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(12);       
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(DateUtil.isCellDateFormatted(c)){
                    return DateFormatUtils.ISO_DATE_FORMAT.format(c.getDateCellValue());
                }else  if ("@".equals(c.getCellStyle().getDataFormatString())) {                    
                    value = nf.format(c.getNumericCellValue());
                } else if ("General".equals(c.getCellStyle().getDataFormatString())) {
                    value = nf.format(c.getNumericCellValue());                    
                } else if (ArrayUtils.contains(ExcelConstants.DATE_PATTERNS,c.getCellStyle().getDataFormatString())) {
                    value = DateFormatUtils.format(HSSFDateUtil.getJavaDate(c.getNumericCellValue()),c.getCellStyle().getDataFormatString());
                } else {
                    value = nf.format(c.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = c.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = c.getCellFormula();
                break;
        }
        return value == null ? "" : value.trim();
    }

    /**
     * 获取Excel字符串类型行数据 当为空白行时返回Null
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @return 行数据
     */
    public List<String> getRowStingFormatData(int sheetIndex, int rowIndex) {
        List<String> result = new Vector<String>();
        Row row = getRow(sheetIndex, rowIndex);
        if (row == null) {
            return null;
        }
        int cols = row.getLastCellNum();    //行的最大列数
        boolean isBlank = true;//是否为空白行
        String str = null;
        for (int i = 0; i < cols; i++) {
            str = getCellStringFormatValue(row.getCell(i));
            if (str != null && str.trim().length() > 0) {
                isBlank = false;
            }
            result.add(str);
        }
        return isBlank ? null : result;
    }

    /**
     * 获取单元格数据
     *
     * @param c 单元格
     * @return
     */
    private String getCellStringFormatValue(Cell c) {
        if (c == null) {
            return "";
        }
        String value = null;        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(12);
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(DateUtil.isCellDateFormatted(c)){
                    return DateFormatUtils.ISO_DATE_FORMAT.format(c.getDateCellValue());
                }else  if ("@".equals(c.getCellStyle().getDataFormatString())) {
                    value = nf.format(c.getNumericCellValue());
                } else if ("General".equals(c.getCellStyle().getDataFormatString())) {
                    value = nf.format(c.getNumericCellValue());                    
                } else if (ArrayUtils.contains(ExcelConstants.DATE_PATTERNS,c.getCellStyle().getDataFormatString())) {
                    value = DateFormatUtils.format(HSSFDateUtil.getJavaDate(c.getNumericCellValue()),c.getCellStyle().getDataFormatString());
                } else {
                    value = nf.format(c.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = c.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:                
                return c.getCellFormula(); 
        }
        return value == null ? "" : value.trim();
    }

    /**
     * 获取单元格数据
     *
     * @param c 单元格
     * @return
     */
    private Object getCellValue(Cell c) {
        if (c == null) {
            return null;
        }        
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return c.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(c)) {
                    return c.getDateCellValue();
                }
                return c.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
                return c.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return c.getCellFormula();
        }
        return null;
    }

    /**
     * 获取Excel行
     *
     * @param sheetIndex 表格下标从0开始
     * @param rowIndex 行下标从0开始
     * @return
     */
    private Row getRow(int sheetIndex, int rowIndex) {
        if (sheetIndex < sheets) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (rowIndex <= sheet.getLastRowNum()) {
                return sheet.getRow(rowIndex);
            }
        }
        return null;
    }

}
