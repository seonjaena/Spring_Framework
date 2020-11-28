package com.sj.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.sj.spring.beans.ContentBean;

public interface BoardMapper {

	@SelectKey(statement = "SELECT CONTENT_SEQ.nextval FROM DUAL", keyProperty = "content_idx", before = true, resultType = int.class)
	@Insert("INSERT INTO CONTENT_TABLE " + 
			"(CONTENT_IDX, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_WRITER_IDX, CONTENT_BOARD_IDX, CONTENT_DATE, " + 
			"VIEWS, ISDELETE) " + 
			"VALUES (#{content_idx}, #{content_subject}, #{content_text}, #{content_writer_idx}, " + 
			"#{content_board_idx}, sysdate, 0, 0)")
	void addContentInfo(ContentBean writeContentBean);
	
	@Insert("INSERT INTO CONTENT_FILE " + 
			"(CONTENT_FILE, CONTENT_IDX, FILE_IDX) " + 
			"VALUES(#{content_file, jdbcType = VARCHAR}, #{content_idx}, FILE_SEQ.nextval)")
	void addContentFile(@Param("content_file") String content_file, @Param("content_idx") int content_idx);
	
	@Select("SELECT a1.CONTENT_IDX, a1.CONTENT_SUBJECT, a1.VIEWS, " + 
			"a2.USER_NAME AS CONTENT_WRITER_NAME, " + 
			"TO_CHAR(a1.CONTENT_DATE, 'YYYY-MM-DD') AS CONTENT_DATE " + 
			"FROM CONTENT_TABLE a1, USER_TABLE a2 " + 
			"WHERE a1.CONTENT_WRITER_IDX = a2.USER_IDX " + 
			"AND a1.CONTENT_BOARD_IDX = #{content_board_idx} " + 
			"AND a1.ISDELETE = 0" + 
			"ORDER BY CONTENT_IDX DESC")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);
	
	@Select("SELECT a2.USER_NAME AS CONTENT_WRITER_NAME, " + 
			"TO_CHAR(a1.CONTENT_DATE, 'YYYY-MM-DD') AS CONTENT_DATE, " + 
			"a1.CONTENT_SUBJECT, a1.CONTENT_TEXT, a1.VIEWS, a1.CONTENT_WRITER_IDX, a1.CONTENT_IDX " + 
			"FROM CONTENT_TABLE a1, USER_TABLE a2 " + 
			"WHERE a1.CONTENT_WRITER_IDX = a2.USER_IDX " + 
			"AND a1.CONTENT_IDX = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	@Select("SELECT CONTENT_FILE " + 
			"FROM CONTENT_FILE " + 
			"WHERE CONTENT_IDX = #{content_idx} " + 
			"ORDER BY FILE_IDX ASC")
	List<String> getContentFile(int content_idx);
	
	@Update("UPDATE CONTENT_TABLE " + 
			"SET CONTENT_SUBJECT = #{content_subject}, CONTENT_TEXT = #{content_text} " + 
			"WHERE CONTENT_IDX = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("DELETE FROM CONTENT_FILE " + 
			"WHERE CONTENT_IDX = #{content_idx}")
	void modifyContentFile1(int content_idx);
	
	@Insert("INSERT INTO CONTENT_FILE " + 
			"(CONTENT_IDX, CONTENT_FILE, FILE_IDX) " + 
			"VALUES (#{content_idx}, #{content_file, jdbcType = VARCHAR}, FILE_SEQ.nextval)")
	void modifyContentFile2(@Param("content_idx") int content_idx, @Param("content_file") String content_file);
	
	@Update("UPDATE CONTENT_TABLE " + 
			"SET ISDELETE = 1 " + 
			"WHERE CONTENT_IDX = #{content_idx}")
	void deleteContentInfo(int content_idx);
	
	@Select("SELECT COUNT(*) FROM CONTENT_TABLE WHERE CONTENT_BOARD_IDX = #{board_info_idx}")
	int getContentCnt(int board_info_idx);
	
}