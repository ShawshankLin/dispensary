package javacommon.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * @author Jeanzhou
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("T:\\20150203202357059.xls");
        
        List list = Main.getExcelData(file);
        
        System.out.println(list);
		
	}
	
	public static List getExcelData(File file) throws IOException {
		List list = new ArrayList();
        Reader reader = ReaderFactory.getReader(file);
        
        int maxRows = reader.getTotalRows(0);
        
        for(int i=0; i<maxRows; i++) {
            List rowsList = reader.getRowStingData(0, i);
            list.add(rowsList);
        }
        
        return list;
	}

}
