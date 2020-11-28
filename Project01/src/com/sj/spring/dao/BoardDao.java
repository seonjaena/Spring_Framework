package com.sj.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sj.spring.beans.ContentBean;
import com.sj.spring.mapper.BoardMapper;

@Repository
public class BoardDao {

	@Autowired
	private BoardMapper boardMapper;
	
	public void addContentInfo(ContentBean writeContentBean) {
		boardMapper.addContentInfo(writeContentBean);
	}
	
	public void addContentFile(@Param("file_name")String file_name, @Param("content_idx")int content_idx) {
		boardMapper.addContentFile(file_name, content_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds) {
		return boardMapper.getContentList(board_info_idx, rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		return boardMapper.getContentInfo(content_idx);
	}
	
	public List<String> getContentFile(int content_idx) {
		return boardMapper.getContentFile(content_idx);
	}
	
	public void modifyContentInfo(ContentBean modifyContentBean) {
		boardMapper.modifyContentInfo(modifyContentBean);
	}
	
	public void modifyContentFile1(int content_idx) {
		boardMapper.modifyContentFile1(content_idx);
	}
	
	public void modifyContentFile2(@Param("content_idx") int content_idx, @Param("content_file") String content_file) {
		boardMapper.modifyContentFile2(content_idx, content_file);
	}
	
	public void deleteContentInfo(int content_idx) {
		boardMapper.deleteContentInfo(content_idx);
	}
	
	public int getContentCnt(int board_info_idx) {
		return boardMapper.getContentCnt(board_info_idx);
	}
	
}
