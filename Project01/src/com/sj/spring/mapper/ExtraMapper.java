package com.sj.spring.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

import com.sj.spring.beans.ContentBean;

public interface ExtraMapper {

	@SelectKey(statement = "SELECT CONTENT_SEQ.nextval FROM DUAL", keyProperty = "content_idx", before = true, resultType = int.class)
	@Insert("INSERT INTO CONTENT_TABLE " + 
			"(CONTENT_IDX, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_WRITER_IDX, CONTENT_BOARD_IDX, CONTENT_DATE) " + 
			"VALUES (#{content_idx}, #{content_subject}, #{content_text}, #{content_writer_idx}, " + 
			"#{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	
	@Insert("INSERT INTO CONTENT_FILE " + 
			"(CONTENT_FILE, CONTENT_IDX) " + 
			"VALUES(#{content_file, jdbcType = VARCHAR}, #{content_idx})")
	void addContentFile(@Param("content_file") String content_file, @Param("content_idx") int content_idx);
	
}
