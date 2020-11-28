package com.sj.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.sj.spring.beans.MediaBean;

public interface MediaMapper {
	
	@Select("SELECT a1.MEDIA_IDX, a1.MEDIA_WRITER_IDX, a1.MEDIA_SUBJECT, a1.MEDIA_DATE, a1.FILESIZE, " + 
			"TO_CHAR(a1.MEDIA_DATE, 'YYYY-MM-DD') AS MEDIA_DATE, a1.MEDIA_POSTER, a1.MEDIA_FILE, a1.VIEWS " + 
			"FROM MEDIA_TABLE a1, USER_TABLE a2 " + 
			"WHERE a1.MEDIA_WRITER_IDX = a2.USER_IDX " + 
			"AND a1.ISDELETE = 0 " + 
			"AND a1.MEDIA_BOARD_IDX = #{media_info_idx} " + 
			"ORDER BY MEDIA_IDX DESC")
	List<MediaBean> getMediaList(int media_info_idx, RowBounds rowBounds);
	
	@SelectKey(statement = "SELECT MEDIA_SEQ.nextval FROM DUAL", keyProperty = "media_idx", before = true, resultType = int.class)
	@Insert("INSERT INTO MEDIA_TABLE(MEDIA_IDX, MEDIA_WRITER_IDX, MEDIA_BOARD_IDX, MEDIA_SUBJECT, MEDIA_DATE, MEDIA_POSTER, MEDIA_FILE, " + 
			"FILESIZE, VIEWS, ISDELETE) " + 
			"VALUES(#{media_idx}, #{media_writer_idx}, #{media_board_idx}, #{media_subject}, SYSDATE, " + 
			"#{media_poster, jdbcType = VARCHAR}, #{media_file}, #{filesize}, 0, 0)")
	void addMediaInfo(MediaBean writeMediaBean);
	
	@Select("SELECT a1.MEDIA_IDX, a1.MEDIA_SUBJECT, a1.MEDIA_POSTER, a1.MEDIA_FILE, a1.FILESIZE, " + 
			"TO_CHAR(a1.MEDIA_DATE, 'YYYY-MM-DD') AS MEDIA_DATE, a1.MEDIA_WRITER_IDX, " + 
			"a2.USER_NAME AS MEDIA_WRITER_NAME " + 
			"FROM MEDIA_TABLE a1, USER_TABLE a2 " + 
			"WHERE a1.MEDIA_WRITER_IDX = a2.USER_IDX " + 
			"AND a1.MEDIA_IDX = #{media_idx}")
	MediaBean getMediaInfo(int media_idx);
	
	@Update("UPDATE MEDIA_TABLE " + 
			"SET ISDELETE = 1 " + 
			"WHERE MEDIA_IDX = #{media_idx}")
	void deleteMediaInfo(int media_idx);
	
	@Select("SELECT COUNT(*) FROM MEDIA_TABLE WHERE MEDIA_BOARD_IDX = #{media_info_idx}")
	int getMediaCnt(int media_info_idx);
	
}