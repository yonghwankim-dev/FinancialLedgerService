package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import excel.ExcelManager;



public class ExpenseService {
	/**
	 * 지출 엑셀파일을 오픈하여 지출 내용을 추가한다.
	 * @param regdate 날짜
	 * @param price	    가격
	 * @param content 내용
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public void append(HttpServletRequest request, Date regdate, int price, String content)
	{	
		String fileName = "expense.xlsx";
		String realPath = request.getServletContext().getRealPath("/expense");
		File path = new File(realPath);
		if(!path.exists())
		{
			path.mkdirs();
		}
		
		String filePath = realPath + File.separator + fileName;
		File expense_file = new File(filePath);
		
		if(!expense_file.exists())
		{
			ExcelManager.createXlsx(filePath);
		}
		
		Object[] expense = {regdate, price, content};
		ExcelManager.addExpense(expense, expense_file);
		
		
	}
}
