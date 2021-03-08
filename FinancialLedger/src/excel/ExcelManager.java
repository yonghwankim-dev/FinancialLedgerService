package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.Expense;

public class ExcelManager {
	
	public ExcelManager()
	{
		
	}
	
	public static void createXlsx(String filePath)
	{
		XSSFWorkbook workbook = new XSSFWorkbook(); // 货 竣伎 积己
		XSSFSheet sheet = workbook.createSheet("sheet0");	// 货 矫飘 积己
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("竣伎颇老 积己 己傍");
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("竣伎颇老积己角菩");
		}
	}
	
	public static void addExpense(Object[] expense, File expense_file)
	{
		try {
			FileInputStream fis = new FileInputStream(expense_file);
			Workbook workbook = WorkbookFactory.create(fis);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			int rowCount = sheet.getLastRowNum();
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			
			Cell cell = row.createCell(columnCount);
			cell.setCellValue(rowCount);
			for(Object field : expense)
			{
				cell = row.createCell(++columnCount);
				if(field instanceof Date)
				{
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					
					cell.setCellValue((Date)field);
					cell.setCellStyle(cellStyle);
				}
				else if(field instanceof String)
				{
					cell.setCellValue((String)field);
				}
				else if(field instanceof Integer) 
				{
					cell.setCellValue((Integer)field);
				}
			}
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(expense_file);
			workbook.write(fos);
			workbook.close();
			fos.close();
		}catch (IOException | EncryptedDocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
