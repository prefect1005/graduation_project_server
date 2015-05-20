package com.touch.graduation.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.touch.graduation.common.Book;
import com.touch.graduation.database.OperateDatabase;
import com.touch.graduation.util.Util;

public class PushMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 存储上传图书信息
	 */
	private Book pushBook = new Book();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		System.out.println("I'm in PushMessageServlet doPost()");

		int photoListSize = Integer.parseInt(URLDecoder.decode(
				request.getParameter(Util.PHOTO_LIST_STRING_TO_INTERNET_SIZE),
				"UTF-8"));
		pushBook.setPhotoListSize(photoListSize);
		System.out.println("photoListSize:" + photoListSize);
		
		List<String> photoListString = new ArrayList<String>();
		for (int i = 1; i <= photoListSize; i++) {
			
			String tempString = request.getParameter(Util.PHOTO_LIST_STRING_TO_INTERNET + i);
			
			FileOutputStream fos = null;
			String savePath = request.getSession().getServletContext().getRealPath("/")+ "images";
			if (!new File(savePath).isDirectory()) {
				new File(savePath).mkdirs(); // 选定上传的目录此处为当前目录，没有则创建
			}
			//文件保存路径
			String savePngPath = savePath + File.separator + new Date().getTime()+ i + ".png";
			System.out.println("PushMessageServlet:savePngPath:" + savePngPath);
			
			fos = new FileOutputStream(savePngPath.toString());
			byte[] bytes = tempString.getBytes("UTF-8");
			Base64 a = new Base64();
			bytes = a.decode(bytes);
			fos.write(bytes);
			fos.close();
			
			photoListString.add(savePngPath);
			//photoListString.add(request.getParameter(Util.PHOTO_LIST_STRING_TO_INTERNET + i));
			// .getParameter(Util.PHOTO_LIST_STRING_TO_INTERNET));
		}
		pushBook.setPhotoList(photoListString);
		
		
		String bookName = URLDecoder.decode(
				request.getParameter(Util.BOOK_NAME_INTERNET), "UTF-8");
		pushBook.setBookName(bookName);
		
		String bookAuthor = URLDecoder.decode(
				request.getParameter(Util.BOOK_AUTHOR_INTERNET), "UTF-8");
		pushBook.setBookAuthor(bookAuthor);
		
		String bookContent = URLDecoder.decode(
				request.getParameter(Util.BOOK_CONTENT_INTERNET), "UTF-8");
		pushBook.setBookContent(bookContent);
		
		String showLocation = URLDecoder.decode(
				request.getParameter(Util.BOOK_LOCATION_INTERNET), "UTF-8");
		pushBook.setLocation(showLocation);
		
		int contactType = Integer
				.parseInt(URLDecoder.decode(
						request.getParameter(Util.OWERN_CONTACT_TYPE_INTERNET),
						"UTF-8"));
		pushBook.setContactTypeNow(contactType);
		
		if (2 == contactType) {
			String otherContactType = URLDecoder.decode(request
					.getParameter(Util.OWERN_OTHER_CONTACT_TYPE_INTERNET),
					"UTF-8");
			pushBook.setOtherContactType(otherContactType);
		}
		
		String contactNumber = URLDecoder.decode(
				request.getParameter(Util.OWERN_CONTACT_NUMBER_INTERNET),
				"UTF-8");
		pushBook.setContactNumber(contactNumber);
		
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String pushTime = sdf.format(new Date());
		pushBook.setPushTime(pushTime);
		
		String account = URLDecoder.decode(request.getParameter(Util.ACCOUNT_INTERNET),"UTF-8");
		pushBook.setAccount(account);

		boolean b = OperateDatabase.insertData(pushBook);

		PrintWriter out = response.getWriter();
		if (b) {
			out.print("success");
		} else {
			out.print("fail");
		}

		out.flush();
		out.close();
	}

}
