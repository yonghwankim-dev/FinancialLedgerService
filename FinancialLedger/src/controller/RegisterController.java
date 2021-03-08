package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excel.ExcelManager;
import service.ExpenseService;

@WebServlet("/register")
public class RegisterController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		String regdate_ = request.getParameter("regdate");
		Date regdate = null;
		try {
			regdate = transFormat.parse(regdate_);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int price = Integer.parseInt(request.getParameter("price"));
		String content = request.getParameter("content");
		
		ExpenseService service = new ExpenseService();
		service.append(request, regdate, price, content);
		
	}
}
