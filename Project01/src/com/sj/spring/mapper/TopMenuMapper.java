package com.sj.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sj.spring.beans.BoardInfoBean;
import com.sj.spring.beans.MediaInfoBean;

public interface TopMenuMapper {

	@Select("SELECT * FROM BOARD_INFO_TABLE ORDER BY BOARD_INFO_IDX ASC")
	List<BoardInfoBean> getTopMenuList();
	
	@Select("SELECT * FROM MEDIA_INFO_TABLE ORDER BY MEDIA_INFO_IDX ASC")
	List<MediaInfoBean> getMediaMenuList();
	
}
