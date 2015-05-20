package com.touch.graduation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.touch.graduation.common.Book;
import com.touch.graduation.util.Util;

public class OperateDatabase {

	@SuppressWarnings("resource")
	public static boolean insertData(Book book) {

		boolean result = false;

		String sql = "insert into book "
				+ "(book_name, book_author, book_content, book_location, "
				+ "contact_type, contact_number, push_time, pic_number, other_contact_type)"
				+ "values(?,?,?,?,?,?,?,?,?)";

		String sqlNoOtherType = "insert into book "
				+ "(book_name, book_author, book_content, book_location, "
				+ "contact_type, contact_number, push_time, pic_number) "
				+ "values(?,?,?,?,?,?,?,?)";

		String sqlInsertPhoto = "insert into book_picture (book_id, book_pic) value(?,?)";

		String sqlSelectBookId = "select book_id from book where book_name = '"
				+ book.getBookName() + "' AND contact_number = '"
				+ book.getContactNumber() + "' AND book_author = '"
				+ book.getBookAuthor() + "'";

		String sqlSelectUserId = "select user_id from user where account = '"
				+ book.getAccount() + "'";

		String sqlInsertIntoUserPushBook = "insert into user_push_book (user_id, book_id) value (?,?)";

		Connection conn = ConnectDatabase.getConnection();

		if (conn != null) {
			PreparedStatement ps = null;
			Statement statement = null;
			try {
				if (book.getOtherContactType() != null) {
					ps = conn.prepareStatement(sql);
					ps.setString(9, book.getOtherContactType());
				} else {
					ps = conn.prepareStatement(sqlNoOtherType);
				}
				ps.setString(1, book.getBookName());
				ps.setString(2, book.getBookAuthor());
				ps.setString(3, book.getBookContent());
				ps.setString(4, book.getLocation());
				ps.setInt(5, book.getContactTypeNow());
				ps.setString(6, book.getContactNumber());
				ps.setString(7, book.getPushTime());
				ps.setInt(8, book.getPhotoListSize());
				int count = ps.executeUpdate();
				if (count != 0) {
					// result = true;
					// 插入其他数据成功，准备查询插入的该列数据的id，然后插入图片
					statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sqlSelectBookId);
					int bookId = 0;
					if (rs.next()) {
						bookId = rs.getInt("book_id");
					}
					System.out.println("bookId = " + bookId);
					// 查询图书id成功，将照片插入照片的表
					List<String> picList = book.getPhotoList();
					for (String everyPic : picList) {
						ps = conn.prepareStatement(sqlInsertPhoto);
						ps.setInt(1, bookId);
						ps.setString(2, everyPic);
						count = ps.executeUpdate();
						if (count != 0) {
							continue;
						} else {
							System.out
									.println("I'm sorry! This is OperateDatabase.java,something has mistake");
						}
					}
					if (count != 0) {
						// result = true;
						// 其他数据插入成功，准备插入用户发布图书的表
						statement = conn.createStatement();
						ResultSet rsUserId = statement
								.executeQuery(sqlSelectUserId);
						int userId = 0;
						if (rsUserId.next()) {
							userId = rsUserId.getInt("user_id");
						}
						System.out.println("userId = " + userId);
						// 查询用户id成功，将图书id和用户ID插入user_push_book表中
						ps = conn.prepareStatement(sqlInsertIntoUserPushBook);
						ps.setInt(1, userId);
						ps.setInt(2, bookId);
						count = ps.executeUpdate();
						if (count != 0) {
							result = true;
						}
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;

	}

	public static String login(String account, String password) {
		String result = Util.LOGIN_FAIL_TAG;
		String sqlSelectUser = "select user_id from user "
				+ "where account = '" + account + "' and password = '"
				+ password + "'";
		Connection conn = ConnectDatabase.getConnection();
		Statement statement = null;
		if (conn != null) {
			try {
				statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sqlSelectUser);
				if (rs.next()) {
					result = Util.LOGIN_SUCCESS_TAG;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static String register(String account, String password,
			String telephone) {
		String result = Util.REGISTER_FAIL_TAG;
		String sqlInsertUser = "insert into user (account, password, telephone) values (?, ?, ?)";
		Connection conn = ConnectDatabase.getConnection();

		if (conn != null) {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sqlInsertUser);
				ps.setString(1, account);
				ps.setString(2, password);
				ps.setString(3, telephone);
				int count = ps.executeUpdate();
				if (count != 0) {
					result = Util.REGISTER_SUCCESS_TAG;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<Book> getBookMessageListByLocation(String location) {
		List<Book> bookMessageList = new ArrayList<Book>();

		String selectBookMessageByLocation = "select book_id, book_name, book_author, book_content, "
				+ "contact_type, other_contact_type, contact_number,"
				+ "push_time, pic_number "
				+ "from book "
				+ "where book_location = '" + location + "'" + "ORDER BY book_id DESC";

		Statement statement = null;
		Connection conn = ConnectDatabase.getConnection();
		if (conn != null) {

			try {
				statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery(selectBookMessageByLocation);
				while (rs.next()) {
					Book book = new Book();
					int bookId = rs.getInt("book_id");
					String bookName = rs.getString("book_name");
					String bookAuthor = rs.getString("book_author");
					String bookContent = rs.getString("book_content");
					int contactType = rs.getInt("contact_type");
					String otherContactType = rs
							.getString("other_contact_type");
					String contactNumber = rs.getString("contact_number");

					String pushTime = rs.getString("push_time");

					int picNumber = rs.getInt("pic_number");
					/*
					 * List<String> photoList = new ArrayList<String>();
					 * 
					 * //两个结果集要分开查询！！！现在是错误的没处理！！！！！ String
					 * selectBookPictureByBookId =
					 * "select book_pic from book_picture where book_id = '" +
					 * bookId + "'"; ResultSet rsBookPic =
					 * statement.executeQuery(selectBookPictureByBookId);
					 * while(rsBookPic.next()) { //现在查询出的图片路径为服务器本机的路径
					 * //现在未经处理，直接转到客户端再进行处理！！！
					 * photoList.add(rsBookPic.getString("book_pic")); }
					 */
					book.setBookId(bookId);
					book.setBookName(bookName);
					book.setBookAuthor(bookAuthor);
					book.setBookContent(bookContent);
					book.setContactTypeNow(contactType);
					book.setOtherContactType(otherContactType);
					book.setContactNumber(contactNumber);
					book.setPushTime(pushTime);
					book.setPhotoListSize(picNumber);
					// book.setPhotoList(photoList);
					bookMessageList.add(book);
				}

				for (Book book : bookMessageList) {
					String selectBookPictureByBookId = "select book_pic from book_picture where book_id = '"
							+ book.getBookId() + "'";
					ResultSet rsBookPic = statement
							.executeQuery(selectBookPictureByBookId);
					List<String> photoList = new ArrayList<String>();
					while (rsBookPic.next()) {
						photoList.add(rsBookPic.getString("book_pic"));
					}
					book.setPhotoList(photoList);

					String selectUserIdByBookId = "select account from user_push_book, user where user.user_id = user_push_book.user_id and book_id = '"
							+ book.getBookId() + "'";
					ResultSet rsUserId = statement
							.executeQuery(selectUserIdByBookId);
					String account = null;
					if (rsUserId.next()) {
						account = rsUserId.getString("account");
					}
					book.setAccount(account);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return bookMessageList;
	}

	public static String getAccountIntroduceByAccount(String account) {

		String result = null;

		String selectAccountIntroduceByAccount = "select introduce from user where account = '"
				+ account + "'";

		Statement statement = null;
		Connection conn = ConnectDatabase.getConnection();
		if (conn != null) {
			try {
				statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery(selectAccountIntroduceByAccount);
				if (rs.next()) {
					result = rs.getString("introduce");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

	public static String updateAccountIntroduceByAccount(String account,
			String introduce) {

		String result = null;

		String selectAccountIntroduceByAccount = "UPDATE user SET introduce = '"
				+ introduce + "' WHERE account = '" + account + "'";

		Connection conn = ConnectDatabase.getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(selectAccountIntroduceByAccount);
				int count = ps.executeUpdate();
				if (count != 0) {
					result = "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;

	}

	public static ArrayList<Book> getPublicBookMessageListByLocation(
			String account) {

		ArrayList<Book> result = new ArrayList<Book>();

		String selectPublishedBookMessageByAccount = "select book.book_id, book.book_name, book.book_author, book.push_time "
				+ "from user, user_push_book, book "
				+ "where user.user_id = user_push_book.user_id "
				+ "AND user_push_book.book_id = book.book_id "
				+ "AND user.account = '" + account + "'";

		Statement statement = null;
		Connection conn = ConnectDatabase.getConnection();

		if (conn != null) {
			try {
				statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery(selectPublishedBookMessageByAccount);
				while (rs.next()) {
					Book book = new Book();
					int bookId = rs.getInt("book_id");
					String name = rs.getString("book_name");
					String author = rs.getString("book_author");
					String date = rs.getString("push_time");
					book.setBookId(bookId);
					book.setBookName(name);
					book.setBookAuthor(author);
					book.setPushTime(date);
					result.add(book);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}
	
	public static boolean removeBookFromBookToChangedBook(int bookId) {
		
		boolean result = false;
		//首先，通过bookid将图书信息查询出来，添加至ChangedBook表，然后删除相关信息
		
		Book book = new Book();
		String selectBookMessageByBookIdSql = "SELECT book_name, book_author, push_time, user_id FROM book, user_push_book WHERE  book.book_id = '" + bookId + "'" + "AND user_push_book.book_id = book.book_id";
		String insertBookMessageToChangedBookSql = "insert into changed_book "
				+ "(changed_book_name, changed_book_author, changed_book_pushtime, changed_book_userid)"
				+ "values(?,?,?,?)";
		String deleteBookMessageFromBookPictureSql = "DELETE FROM book_picture WHERE book_id = '" + bookId + "'";
		String deleteBookMessageFromUserPushBookSql = "DELETE FROM user_push_book WHERE book_id = '" + bookId + "'";
		String deleteBookMessageFromBookSql = "DELETE FROM book WHERE book_id = '" + bookId + "'";
		
		Statement statement = null;
		Connection conn = ConnectDatabase.getConnection();

		if (conn != null) {
			try {
				statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery(selectBookMessageByBookIdSql);
				if (rs.next()) {
					String name = rs.getString("book_name");
					String author = rs.getString("book_author");
					String date = rs.getString("push_time");
					int userId = rs.getInt("user_id");
					book.setBookName(name);
					book.setBookAuthor(author);
					book.setPushTime(date);
					book.setUserId(userId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(insertBookMessageToChangedBookSql);
				
				ps.setString(1, book.getBookName());
				ps.setString(2, book.getBookAuthor());
				ps.setString(3, book.getPushTime());
				ps.setInt(4, book.getUserId());
				
				int count = ps.executeUpdate();
				if (count != 0) {
					//表示插入成功，开始删除BookPicture表中的数据
					ps = conn.prepareStatement(deleteBookMessageFromBookPictureSql);
					count = ps.executeUpdate();
					if(count != 0) {
						//表示删除成功，继续删除user_push_book表中的数据
						ps = conn.prepareStatement(deleteBookMessageFromUserPushBookSql);
						count = ps.executeUpdate();
						if(count != 0) {
							//表示删除成功，继续删除book表中的数据
							ps = conn.prepareStatement(deleteBookMessageFromBookSql);
							count = ps.executeUpdate();
							if(count != 0) {
								//表示删除成功！
								result = true;
							}
						}
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return result;
		
	}
	
	public static ArrayList<Book> getGivedBookMessageListByLocation(
			String account) {

		ArrayList<Book> result = new ArrayList<Book>();

		String selectGivededBookMessageByAccount = "select changed_book_name, changed_book_author, changed_book_pushtime "
				+ "from changed_book, user "
				+ "where user.account = '" + account + "'" + "AND changed_book.changed_book_userid = user.user_id";

		Statement statement = null;
		Connection conn = ConnectDatabase.getConnection();

		if (conn != null) {
			try {
				statement = conn.createStatement();
				ResultSet rs = statement
						.executeQuery(selectGivededBookMessageByAccount);
				while (rs.next()) {
					Book book = new Book();
					String name = rs.getString("changed_book_name");
					String author = rs.getString("changed_book_author");
					String date = rs.getString("changed_book_pushtime");
					book.setBookName(name);
					book.setBookAuthor(author);
					book.setPushTime(date);
					result.add(book);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;

	}

}
