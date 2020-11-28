package com.sj.spring.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class ContentBean {
	private int content_idx;
	
	@NotBlank
	private String content_subject;
	
	@NotBlank
	private String content_text;
	private String content_file;
	private int content_writer_idx;
	private int content_board_idx;
	private String content_date;
	private MultipartFile[] upload_file;
	private String content_writer_name;
	private int views;
	private int file_idx;
	private int isdelete;
	
	public int getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}
	public String getContent_subject() {
		return content_subject;
	}
	public void setContent_subject(String content_subject) {
		this.content_subject = content_subject;
	}
	public String getContent_text() {
		return content_text;
	}
	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}
	public String getContent_file() {
		return content_file;
	}
	public void setContent_file(String file_name) {
		this.content_file = file_name;
	}
	public int getContent_writer_idx() {
		return content_writer_idx;
	}
	public void setContent_writer_idx(int content_writer_idx) {
		this.content_writer_idx = content_writer_idx;
	}
	public int getContent_board_idx() {
		return content_board_idx;
	}
	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}
	public String getContent_date() {
		return content_date;
	}
	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}
	public MultipartFile[] getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(MultipartFile[] upload_file) {
		this.upload_file = upload_file;
	}
	public String getContent_writer_name() {
		return content_writer_name;
	}
	public void setContent_writer_name(String content_writer_name) {
		this.content_writer_name = content_writer_name;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public int getFile_idx() {
		return file_idx;
	}
	public void setFile_idx(int file_idx) {
		this.file_idx = file_idx;
	}
	
}