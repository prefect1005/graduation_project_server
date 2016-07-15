package com.touch.graduation.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 图书的数据库存储ID
	 */
	private int bookId;

	/**
	 * 存储用户上传的图片数量
	 */
	private int photoListSize;

	/**
	 * 存储用户上传的图书照片
	 */
	private List<String> photoList;

	/**
	 * 图书名称
	 */
	private String bookName;

	/**
	 * 作者姓名
	 */
	private String bookAuthor;

	/**
	 * 图书简介
	 */
	private String bookContent;

	/**
	 * 发布位置
	 */
	private String location;

	/**
	 * 联系类型
	 */
	private int contactTypeNow;

	/**
	 * 其他联系途径
	 */
	private String otherContactType;

	/**
	 * 联系方式
	 */
	private String contactNumber;

	/**
	 * 发布时间
	 */
	private String pushTime;

	/**
	 * 发布者账户名
	 */
	private String account;

	/**
	 * 用户id
	 */
	private int userId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPhotoListSize() {
		return photoListSize;
	}

	public void setPhotoListSize(int photoListSize) {
		this.photoListSize = photoListSize;
	}

	public List<String> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<String> photoList) {
		this.photoList = photoList;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getContactTypeNow() {
		return contactTypeNow;
	}

	public void setContactTypeNow(int contactTypeNow) {
		this.contactTypeNow = contactTypeNow;
	}

	public String getOtherContactType() {
		return otherContactType;
	}

	public void setOtherContactType(String otherContactType) {
		this.otherContactType = otherContactType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String toString() {
		return "sun fu jin, are you ok";
	}
}
