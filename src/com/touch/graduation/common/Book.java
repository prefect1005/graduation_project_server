package com.touch.graduation.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ͼ������ݿ�洢ID
	 */
	private int bookId;

	/**
	 * �洢�û��ϴ���ͼƬ����
	 */
	private int photoListSize;

	/**
	 * �洢�û��ϴ���ͼ����Ƭ
	 */
	private List<String> photoList;

	/**
	 * ͼ������
	 */
	private String bookName;

	/**
	 * ��������
	 */
	private String bookAuthor;

	/**
	 * ͼ����
	 */
	private String bookContent;

	/**
	 * ����λ��
	 */
	private String location;

	/**
	 * ��ϵ����
	 */
	private int contactTypeNow;

	/**
	 * ������ϵ;��
	 */
	private String otherContactType;

	/**
	 * ��ϵ��ʽ
	 */
	private String contactNumber;

	/**
	 * ����ʱ��
	 */
	private String pushTime;

	/**
	 * �������˻���
	 */
	private String account;

	/**
	 * �û�id
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
