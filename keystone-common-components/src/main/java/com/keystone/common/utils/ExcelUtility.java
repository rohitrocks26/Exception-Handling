/**
 * 
 */
package com.keystone.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author mapendyala
 *
 */
public class ExcelUtility {

	 /**
     * Method for writing a String Value to cell
     * Example of Cell Address is A1 ,(A - column , 1 - Row)
     * @throws ParseException 
     * */
    public void writeStringValueToCell(String sheetName,String cellAddress,String value, String fileName, String path) throws ParseException{
    		writeValueToCell(sheetName,cellAddress,value,"string", fileName, path);
    }
    
    public void writeValueToCell(String sheetName,String cellAddress,String value,String valueType, String fileName, String path) throws ParseException{
    	
    	FileOutputStream outfile = null;
    	
    	try {
			FileInputStream file = new FileInputStream(path+fileName);
				try{
					// Open workbook
					XSSFWorkbook workbook = new XSSFWorkbook(file);
			
					//Open Sheet that we need
					XSSFSheet sheet = workbook.getSheet(sheetName);
			
					//Create reference to a cell, that we need
					CellReference cr = new CellReference(cellAddress);
					Row row = sheet.getRow(cr.getRow());
			
					//If row doesn't exist we should create it
					if(isRowEmpty(row)){
						row = sheet.createRow(cr.getRow());
					};
					
					//If cell doesn't exist we should create it
					Cell cell = row.getCell(cr.getCol());
					if(isCellEmpty(cell)){
						cell = row.createCell(cr.getCol());
					}
			
					CellStyle cellStyle = workbook.createCellStyle();
					// set foreground color of a cell
					cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
					if(valueType.equals("string")){
						cell.setCellStyle(cellStyle);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(value);
					}
			
		
					if(valueType.equals("date")){				
						DataFormat format = workbook.createDataFormat();
						cellStyle.setDataFormat(format.getFormat("MM/dd/yyyy;@"));
						Date valueAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(value);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(valueAsDate);						
					}

					//Recalculate all formulas in a workbook;
					workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
					
					file.close();
			
					//Writing to file
					outfile = new FileOutputStream(path+fileName);
					workbook.write(outfile);
					outfile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}			
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
    		}
    
    public boolean isRowEmpty(Row row){
        if (row == null){
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean isCellEmpty(Cell cell){
        if (cell == null){
            return true;
        }
        else {
            return false;
        }
    }
    
    
    /**
     * Method for deleting row by Row Number
     * @throws IOException 
     * */
    public void deleteRowByRowNum (String path, String fileName, String sheetName, int rowNum) throws IOException {
		FileInputStream inp = new FileInputStream(path+fileName);
		XSSFWorkbook wb = new XSSFWorkbook(inp);
		//Open Sheet that we need
		XSSFSheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		sheet.removeRow(row);
		
		FileOutputStream fileOut = new FileOutputStream(path+fileName);
		wb.write(fileOut);
		fileOut.close();
		inp.close();
	}
    
    /**
     * Method for deleting row by Column Number
     * @throws IOException 
     * */
    public void deleteColumnByColumnNum (String path, String fileName, String sheetName, int columnNum) throws IOException {
		FileInputStream inp = new FileInputStream(path+fileName);
		XSSFWorkbook wb = new XSSFWorkbook(inp);
		//Open Sheet that we need
		XSSFSheet sheet = wb.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()){
			XSSFRow row=(XSSFRow)rowIterator.next();
            XSSFCell cell=row.getCell(columnNum);
            row.removeCell(cell);
		}
		
		FileOutputStream fileOut = new FileOutputStream(path+fileName);
		wb.write(fileOut);
		fileOut.close();
		inp.close();
	}
    
    protected static List<Sheet> getSheetsList(Workbook wb, String sheetName) {
		List<Sheet> sheets = new ArrayList<Sheet>();
		if (sheetName.equals("ALL")) {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					sheets.add(wb.getSheetAt(i));
			}
		} else 
			for(int i=0; i < wb.getNumberOfSheets(); i++){
				if(wb.getSheetName(i).contentEquals(sheetName)){
					sheets.add(wb.getSheetAt(i));
				}
			}
		return sheets;
	}
    
    
    /**
     * Method for finding a particular value in the sheet and replacing the old value with a new value
     * @throws IOException
     * */
    public void findAndReplaceValueInCell(String path, String fileName, String sheetName, String oldValue, String newValue) throws IOException {
		FileInputStream inp = new FileInputStream(path+fileName);

		Workbook wb = new XSSFWorkbook(inp);
		List<Sheet> sheets = getSheetsList(wb, sheetName);
		for (Sheet sheet : sheets) {
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == 1 && cell.getStringCellValue().equals(oldValue)) {
						cell.setCellValue(newValue);
					}
				}
			}
		}
		FileOutputStream fileOut = new FileOutputStream(path+fileName);
		wb.write(fileOut);
		fileOut.close();
		inp.close();
	}
    
    /**
     * Method for retrieving value in a particular cell with row Number and Column number as references
     * 
     * */
    public String getCellValueUsingIndex(String sheetName, String path, int rowNum, int cellNum, String fileName) {

		String retVal = null;
		try {

			FileInputStream fis = new FileInputStream(path+fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet s = wb.getSheet(sheetName);
			XSSFRow r = s.getRow(rowNum);
			XSSFCell c = r.getCell(cellNum);
			
			retVal = c.getStringCellValue();
			System.out.println(retVal);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retVal;
	}
    
    /**
     * Method for retrieving value in a particular cell with row number and column name as references
     * 
     * */
    public String getCellValueUsingColumnName(String sheetName, String path, int rowNum, String columnName, String fileName) {

    	int cellNum = CellReference.convertColStringToIndex(columnName);
    	return getCellValueUsingIndex(sheetName, path, rowNum, cellNum, fileName);
	}
    
}
