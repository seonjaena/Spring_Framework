package com.sj.spring.service;

import java.util.List;

import com.sj.spring.beans.BoardInfoBean;
import com.sj.spring.beans.MediaInfoBean;
import com.sj.spring.dao.TopMenuDao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TopMenuService {

	@Autowired
	private TopMenuDao topMenuDao;
	
	public List<BoardInfoBean> getTopMenuList() {
		return topMenuDao.getTopMenuList();
	}
	
	public List<MediaInfoBean> getMediaMenuList() {
		return topMenuDao.getMediaMenuList();
	}
	
}
