package com.hadoop.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsFiles {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File myFile = new File("D:/Eclipse WorkSpace/GSPANN/Hadoop-Practice/Input/ivr_DATA.xls"); 
		FileInputStream fis = new FileInputStream(myFile); 
		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); 
		// Return first sheet from the XLSX workbook 
		String[] AllUids = getinfo(myWorkBook,0);
		String[] EndUids = getinfo(myWorkBook,1);
		
		 List diff1 = ListUtils.subtract(Arrays.asList(AllUids), Arrays.asList(EndUids));
         List diff2 = ListUtils.subtract(Arrays.asList(EndUids), Arrays.asList(AllUids));
         System.out.println("\n"+diff1+" \n"+diff2);
         FileWriter fw = new FileWriter("D:/Eclipse WorkSpace/GSPANN/Hadoop-Practice/Output/IsBestCustomer.txt");
         //fw.write(diff1);
         //fw.write(diff2);
         fw.close();
	}
	public static String[] getinfo(XSSFWorkbook myWorkBook,int x){
		XSSFSheet mySheet = myWorkBook.getSheetAt(x); 
		// Get iterator to all the rows in current sheet 
		Iterator<Row> rowIterator = mySheet.iterator(); 
		// Traversing over each row of XLSX file 
		StringBuffer content= new StringBuffer();
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next(); 
			// For each row, iterate through each Column 
			System.out.println(row.getCell(0));
			
			if(content.equals(""))
				content.append(row.getCell(0));
			else
				content.append(","+row.getCell(0));
			/*Iterator<Cell> cellIterator = row.cellIterator(); 
			while (cellIterator.hasNext()) { 
				Cell cell = cellIterator.next(); 
				switch (cell.getCellType()) { 
					case Cell.CELL_TYPE_STRING: System.out.print(cell.getStringCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_NUMERIC: System.out.print(cell.getNumericCellValue() + "\t");
						break; 
					case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); 
						break; 
					default : 
				} 
			} */
			
		}
		return content.toString().split(",");
	}
}
