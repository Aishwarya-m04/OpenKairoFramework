package com.openkairo.genericUtilty;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
FileInputStream fis1;
	
	public String readDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws Exception
	{
		fis1 = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Cell cell = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		
		DataFormatter df = new DataFormatter();
		return df.formatCellValue(cell);
		
	}
	
	public void modifyExistingCellValue(String sheetName, int rowNum, int cellNum, String value) throws Exception
	{
		fis1 = new FileInputStream("src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		
		wb.close();
	}
	
	public void writeInNewCellValue(String sheetName, int rowNum, int cellNum, String value) throws Exception 
	{
		fis1 = new FileInputStream("src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		
		wb.close();
	}
	
	public void createNewSheet(String sheetName, int rowNum, int cellNum, String value) throws Exception
	{
		fis1 = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		
		wb.close();
	}

}
