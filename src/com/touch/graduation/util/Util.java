package com.touch.graduation.util;

import java.awt.Image;

public class Util {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	public static final String URL = "jdbc:mysql://127.0.0.1:3306/change_book_db";

	/**
	 * 数据库连接用户名
	 */
	public static final String DATA_USER = "root";

	/**
	 * 数据库连接密码
	 */
	public static final String DATA_PASSWORD = "";
	
	/**
	 * 将存储照片的链表存储至BasicNameValuePair用于接收
	 */
	public static final String PHOTO_LIST_STRING_TO_INTERNET= "photo_list_string_to_internet";
	
	/**
	 * 将存储照片的链表的大小存储，便于存取需要
	 */
	public static final String PHOTO_LIST_STRING_TO_INTERNET_SIZE = "photo_list_string_to_internet_size";
	
	/**
	 * 存储图书书名用于网络传输
	 */
	public static final String BOOK_NAME_INTERNET = "book_name_internet";
	
	/**
	 * 存储图书作者名用于网络传输
	 */
	public static final String BOOK_AUTHOR_INTERNET = "book_author_internet";
	
	/**
	 * 存储图书简介用于网络传输
	 */
	public static final String BOOK_CONTENT_INTERNET = "book_content_internet";
	
	/**
	 * 图书拥有者的所在位置
	 */
	public static final String BOOK_LOCATION_INTERNET = "book_location_internet";
	
	/**
	 * 联系途径
	 */
	public static final String OWERN_CONTACT_TYPE_INTERNET = "owern_contact_type_internet";
	
	/**
	 * 其他联系途径
	 */
	public static final String OWERN_OTHER_CONTACT_TYPE_INTERNET = "owern_other_contact_type_internet";
	
	/**
	 * 联系方式
	 */
	public static final String OWERN_CONTACT_NUMBER_INTERNET = "owern_contact_number_internet";
	
	/**
	 * 用户名称
	 */
	public static final String ACCOUNT_INTERNET = "account_internet";
	
	/**
	 * 用户密码
	 */
	public static final String PASSWORD_INTERNET = "password_internet";
	
	/**
	 * 用户电话
	 */
	public static final String TELEPHONE_INTERNET = "telephone_internet";
	
	/**
	 * 用户所在地，用于查询返回图书
	 */
	public static final String SELECT_BO0K_LOCATION_KEY = "select_book_location_key";
	
	/**
	 * 返回登录成功标记
	 */
	public final static String LOGIN_SUCCESS_TAG = "success";
	
	/**
	 * 返回登录失败标记
	 */
	public final static String LOGIN_FAIL_TAG = "fail";
	
	/**
	 * 返回注册成功标记
	 */
	public final static String REGISTER_SUCCESS_TAG = "success";
	
	/**
	 * 返回注册失败标记
	 */
	public final static String REGISTER_FAIL_TAG = "fail";
	
	/**
	 * 用于向服务端上传用户简介
	 */
	public static final String ACCOUNT_INTRODUCE_INTERNET = "account_introduce_internet";
	
	/**
	 * 图书ID，用于向服务器传递要删除的图书id
	 */
	public static final String DELETE_BOOK_ID_INTERNET = "delete_book_id_internet";
	

	
}
