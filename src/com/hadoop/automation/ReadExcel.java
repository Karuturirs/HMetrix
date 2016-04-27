package com.hadoop.automation;

import java.io.*;
import java.util.*;

import org.apache.commons.collections.ListUtils;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private String inputFile;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException  {
		File inputWorkbook = new File(inputFile);
		System.out.println(inputWorkbook.exists());
		Workbook w;
		String[] sheetnames={""};
		String[] AllUids = {""};

		String[] EndUids = {""};
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet;
			// Loop over first 10 column and lines
	         sheetnames=w.getSheetNames();
	         for(int k=0;k<sheetnames.length;k++){
	        	sheet = w.getSheet(k);
	        	//System.out.println(sheetnames.length+"::"+sheetnames[k]);
	        	if(k==0)
	        		AllUids= getinto(sheetnames[k],sheet);
	        	else
	        		EndUids = getinto(sheetnames[k],sheet);
	         }
	         
	         List diff1 = ListUtils.subtract(Arrays.asList(AllUids), Arrays.asList(EndUids));
	         List diff2 = ListUtils.subtract(Arrays.asList(EndUids), Arrays.asList(AllUids));
	         System.out.println("\n"+diff1+" "+diff1.size()+"\n"+diff2+" "+diff2.size());
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
	public String[] getinto(String sheetname,Sheet sheet){
	
		String countrycode="";
		StringBuffer content= new StringBuffer();
		System.out.println("sheet Name: "+sheetname+"---");
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell countrycell = sheet.getCell(0, i);
				if ((countrycell.getType() == CellType.NUMBER)|| (countrycell.getType() == CellType.LABEL)){
					
					countrycode=countrycell.getContents();
					if(content.equals(""))
						content.append(countrycode.toLowerCase());
					else
						content.append(","+countrycode.toLowerCase());
				}else{
					//if(content.equals(""))	
					//	content.append(countrycode);
					//else
						//content.append(","+countrycode);
				}
				
			}
			
		return content.toString().split(",");
	}
	
	

	public static void main(String[] args) throws IOException {
		ReadExcel readxls = new ReadExcel();
		readxls.setInputFile("D:/Eclipse_WorkSpace/GSPANN/Hadoop-Practice/Input/HistoryLoad2.xls");
		readxls.read();
	}

}

		

