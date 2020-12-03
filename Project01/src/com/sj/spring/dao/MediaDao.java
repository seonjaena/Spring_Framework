package com.sj.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sj.spring.beans.MediaBean;
import com.sj.spring.mapper.MediaMapper;

@Repository
public class MediaDao {

	@Autowired
	private MediaMapper mediaMapper;
	
	public List<MediaBean> getMediaList(int media_info_idx, RowBounds rowBounds) {
		return mediaMapper.getMediaList(media_info_idx, rowBounds);
	}
	
	public void addMediaInfo(MediaBean writeMediaBean) {
		mediaMapper.addMediaInfo(writeMediaBean);
	}
	
	public MediaBean getMediaInfo(int media_idx) {
		return mediaMapper.getMediaInfo(media_idx);
	}
	
	public int getMediaCnt(int media_info_idx) {
		return mediaMapper.getMediaCnt(media_info_idx);
	}
	
	public void deleteMediaInfo(int media_idx) {
		mediaMapper.deleteMediaInfo(media_idx);
	}
	
	public void modifyMediaInfo(MediaBean modifyMediaBean) {
		mediaMapper.modifyMediaInfo(modifyMediaBean);
	}
	
	public void addViews(@Param("views")int views, @Param("media_idx")int media_idx) {
		mediaMapper.addViews(views, media_idx);
	}
	
}