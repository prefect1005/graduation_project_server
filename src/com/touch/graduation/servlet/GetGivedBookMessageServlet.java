package com.touch.graduation.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touch.graduation.common.Book;
import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class GetGivedBookMessageServlet extends HttpServlet {

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

		System.out.println("I'm in GetGivedBookMessageServlet doPost()");

		String account = req.getParameter(Util.ACCOUNT_INTERNET);
		System.out.println("account = " + account);

		ArrayList<Book> bookMessageList = OperateDatabase
				.getGivedBookMessageListByLocation(account);

		ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
		oos.writeObject(bookMessageList);

		oos.flush();
		oos.close();
	}

}
