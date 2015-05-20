package com.touch.graduation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class RemoveBookFromBookToChangedBookServlet extends HttpServlet {

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

		System.out.println("I'm in RemoveBookFromBookToChangedBookServlet doPost()");

		int bookId = Integer.parseInt(req.getParameter(Util.DELETE_BOOK_ID_INTERNET));
		System.out.println("bookId = " + bookId);

		boolean result = OperateDatabase.removeBookFromBookToChangedBook(bookId);

		System.out.println("result!!!!!!==" + result);
		
		PrintWriter printWriter = resp.getWriter();

		printWriter.print(result);
	}

}
