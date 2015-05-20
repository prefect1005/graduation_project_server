package com.touch.graduation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class SetAccountIntroduceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("I'm in SetAccountIntroduceServlet doPost()");
		
		String introduce = req.getParameter(Util.ACCOUNT_INTRODUCE_INTERNET);
		System.out.println("introduce = " + introduce);
		
		String account = req.getParameter(Util.ACCOUNT_INTERNET);
		System.out.println("account = " + account);
		
		String result = OperateDatabase.updateAccountIntroduceByAccount(account,introduce);
		
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.print(result);
	}
	
	

}
