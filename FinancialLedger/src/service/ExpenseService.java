package service;

import java.io.IOException;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;

import excel.ExcelManager;

public class ExpenseService {
	/**
	 * ���� ���������� �����Ͽ� ���� ������ �߰��Ѵ�.
	 * @param regdate ��¥
	 * @param price	    ����
	 * @param content ����
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static void append(Date regdate, int price, String content) throws EncryptedDocumentException, IOException
	{
		Object[] f_vals = {regdate,price,content};
		ExcelManager em = new ExcelManager();
		em.appendExcelFile(f_vals);
	}
}