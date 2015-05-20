package com.touch.graduation.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touch.graduation.common.Book;
import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class GetMessageServlet extends HttpServlet {

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
		
		System.out.println("I'm in GetMessageServlet doPost()");
		
		String location = req.getParameter(Util.SELECT_BO0K_LOCATION_KEY);
		System.out.println("location = " + location);
		
		List<Book> bookMessageList = OperateDatabase.getBookMessageListByLocation(location);
		
		ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
		oos.writeObject(bookMessageList);
		
		oos.flush();
		oos.close();
		
		
	}
	
	

}
