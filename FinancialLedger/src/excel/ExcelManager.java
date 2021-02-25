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
import org.apache.poi.ss.usermodel.DateUtil;
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

	/**
	 * ExcelFile�� ��� expense ��ü�� �����ϴ� �����͸� �߰��Ѵ�.
	 * @param expense
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static void appendExcelFile(Object[] f_vals) throws EncryptedDocumentException, IOException
	{
		String filePath = "./res/expense.xlsx"; // open�� ���� ���
		
		FileInputStream fis = new FileInputStream(new File(filePath));
		
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();
				
		Row row = sheet.createRow(++rowCount);
		int columnCount = 0;
		
		Cell cell = row.createCell(columnCount);
		cell.setCellValue(rowCount);
		
		for(Object field : f_vals)
		{
			cell = row.createCell(++columnCount);
			if(field instanceof Date) {
				cell.setCellValue((Date)field);
			}
			else if(field instanceof Integer) {
				cell.setCellValue((Integer)field);
			}
			else if(field instanceof String) {
				cell.setCellValue((String)field);
			}
		}
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	
	public static void writeExcelFile(List<Expense> list) throws IOException
	{
		String filePath = "./res/expense.xlsx";	//������ ���� ���
		
		FileOutputStream fos = new FileOutputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("studentList");	// sheet ����
		
		XSSFRow curRow;
		
		int row = list.size();
		
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
		
		for(int i=0;i<row;i++)
		{
			curRow = sheet.createRow(i); // row ����
			XSSFCell RegdateCell = curRow.createCell(0);
			RegdateCell.setCellValue((Date)list.get(i).getRegdate());
			RegdateCell.setCellStyle(cellStyle);
			
			curRow.createCell(1).setCellValue(list.get(i).getPrice());
			curRow.createCell(2).setCellValue(list.get(i).getContent());
		}
		workbook.write(fos);
		fos.close();
	}
	
	public static List<Expense> getStudentList() throws EncryptedDocumentException, IOException
	{
		List<Expense> expenseList = new ArrayList<Expense>();
		
		String filePath="./res/student.xlsx";
		InputStream inputStream = new FileInputStream(filePath);
		
		// ���� �ε�
		Workbook workbook = WorkbookFactory.create(inputStream);
		
		// ��Ʈ �ε� 0, ù��° ��Ʈ �ε�
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItr = sheet.iterator();
		
		// �ุŭ �ݺ�
		while(rowItr.hasNext())
		{
			Expense expense = new Expense();
			Row row = rowItr.next();
			//ù���� ���� ����� ��� ��ŵ, 2��° ����� data �ε�
			if(row.getRowNum()==0)
			{
				continue;
			}
			Iterator<Cell> cellItr = row.cellIterator();
			// �� ���� �ѿ��� �б� (�� �б�)
			while(cellItr.hasNext())
			{
				Cell cell = cellItr.next();
				int index = cell.getColumnIndex();
				switch(index) {
				case 0: //�������
					expense.setRegdate((Date)getValueFromCell(cell));
					break;
				case 1:	//����
					// ���� �������� ��� Double������ ��ȯ �� int������ ��ȯ
					expense.setPrice(((Double) getValueFromCell(cell)).intValue());
					break;
				case 2: //����
					expense.setContent((String)getValueFromCell(cell));
					break;
				
				}
			}
			expenseList.add(expense);
		}
		return expenseList;
	}

	private static Object getValueFromCell(Cell cell) {
		// TODO Auto-generated method stub
		switch(cell.getCellType())
		{
		case STRING:
			return cell.getStringCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell))
			{
				return cell.getDateCellValue();
			}
			return cell.getNumericCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		default:
			return "";
		}
	}
}