package com.sj.spring.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class MediaBean {

	private int media_idx;
	
	@NotBlank
	private String media_subject;
	private String media_date;
	private int media_writer_idx;
	private int media_board_idx;
	private int views;
	private int isdelete;
	private String media_poster;
	private String media_file;
	private long filesize;
	private String media_writer_name;
	
	private MultipartFile media_posterMF;
	private MultipartFile media_fileMF;
	
	public int getMedia_idx() {
		return media_idx;
	}
	public void setMedia_idx(int media_idx) {
		this.media_idx = media_idx;
	}
	public String getMedia_subject() {
		return media_subject;
	}
	public void setMedia_subject(String media_subject) {
		this.media_subject = media_subject;
	}
	public String getMedia_date() {
		return media_date;
	}
	public void setMedia_date(String media_date) {
		this.media_date = media_date;
	}
	public String getMedia_poster() {
		return media_poster;
	}
	public void setMedia_poster(String media_poster) {
		this.media_poster = media_poster;
	}
	public String getMedia_file() {
		return media_file;
	}
	public void setMedia_file(String media_file) {
		this.media_file = media_file;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public MultipartFile getMedia_posterMF() {
		return media_posterMF;
	}
	public void setMedia_posterMF(MultipartFile media_posterMF) {
		this.media_posterMF = media_posterMF;
	}
	public MultipartFile getMedia_fileMF() {
		return media_fileMF;
	}
	public void setMedia_fileMF(MultipartFile media_fileMF) {
		this.media_fileMF = media_fileMF;
	}
	public int getMedia_writer_idx() {
		return media_writer_idx;
	}
	public void setMedia_writer_idx(int media_writer_idx) {
		this.media_writer_idx = media_writer_idx;
	}
	public int getMedia_board_idx() {
		return media_board_idx;
	}
	public void setMedia_board_idx(int media_board_idx) {
		this.media_board_idx = media_board_idx;
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
	public String getMedia_writer_name() {
		return media_writer_name;
	}
	public void setMedia_writer_name(String media_writer_name) {
		this.media_writer_name = media_writer_name;
	}
	
}