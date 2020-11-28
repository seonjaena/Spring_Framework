package com.sj.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sj.spring.beans.BoardInfoBean;
import com.sj.spring.beans.MediaInfoBean;
import com.sj.spring.mapper.TopMenuMapper;

@Repository
public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfoBean> getTopMenuList() {
		return topMenuMapper.getTopMenuList();
	}
	
	public List<MediaInfoBean> getMediaMenuList() {
		return topMenuMapper.getMediaMenuList();
	}
	
}
