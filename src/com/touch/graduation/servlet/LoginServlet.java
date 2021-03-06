package com.touch.graduation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class LoginServlet extends HttpServlet {

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
		
		System.out.println("I'm in LoginServlet doPost()");
		
		String account = req.getParameter(Util.ACCOUNT_INTERNET);
		System.out.println("account = " + account);
		
		String password = req.getParameter(Util.PASSWORD_INTERNET);
		System.out.println("password = " + password);
		
		String result = OperateDatabase.login(account, password);
		
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.print(result);

	}

}
