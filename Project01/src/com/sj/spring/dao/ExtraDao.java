package com.sj.spring.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sj.spring.beans.ContentBean;
import com.sj.spring.mapper.ExtraMapper;

@Repository
public class ExtraDao {

	@Autowired
	private ExtraMapper extraMapper;
	
	public void addContentInfo(ContentBean writeContentBean) {
		extraMapper.addContentInfo(writeContentBean);
	}
	
	public void addContentFile(@Param("content_file") String content_file, @Param("content_idx") int content_idx) {
		extraMapper.addContentFile(content_file, content_idx);
	}
	
}
