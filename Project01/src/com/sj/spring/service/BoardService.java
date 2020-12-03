package com.sj.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.spring.beans.ContentBean;
import com.sj.spring.beans.PageBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Value("${upload.path}")
	private String upload_path;
	
	@Value("${page.contentCnt}")
	private int page_contentCnt;
	
	@Value("${page.buttonCnt}")
	private int page_buttonCnt;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	public String getFileName(MultipartFile file) {
		HashService hashService = new HashService();
		String file_name = hashService.Encrypt(file.getOriginalFilename());
		
		try {
			
			file.transferTo(new File(upload_path + "/" + file_name));
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return file_name;
		
	}
	
	public void addContentInfo(ContentBean writeContentBean) {
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		boardDao.addContentInfo(writeContentBean);
		
		MultipartFile[] upload_file = writeContentBean.getUpload_file();

		String file_name = null;
		List<String> content_file = new ArrayList<String>();
		
		for(MultipartFile files : upload_file) {
			
			if(files.getSize() > 0) {
				
				file_name = getFileName(files);
				content_file.add(file_name);
				boardDao.addContentFile(file_name, writeContentBean.getContent_idx());
				
			}
			
		}
		
	}
	
	public List<ContentBean> getContentList(int board_info_idx ,int page) {
		
		int start = (page - 1) * page_contentCnt;
		RowBounds rowBounds = new RowBounds(start, page_contentCnt);
		
		return boardDao.getContentList(board_info_idx, rowBounds);
		
	}
	
	public ContentBean getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
	
	public List<String> getContentFile(int content_idx) {
		return boardDao.getContentFile(content_idx);
	}
	
	public void getModifyContentInfo(int content_idx, ContentBean modifyContentBean) {
		ContentBean tempModifyContentBean = boardDao.getContentInfo(content_idx);
		
		modifyContentBean.setContent_idx(content_idx);
		modifyContentBean.setContent_writer_name(tempModifyContentBean.getContent_writer_name());
		modifyContentBean.setContent_subject(tempModifyContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempModifyContentBean.getContent_text());
		modifyContentBean.setContent_date(tempModifyContentBean.getContent_date());
	}
	
	public List<String> getModifyContentFile(int content_idx) {
		return boardDao.getContentFile(content_idx);
	}
	
	public void modifyContentInfo(int content_idx, ContentBean modifyContentBean) {
		
		modifyContentBean.setContent_idx(content_idx);
		boardDao.modifyContentInfo(modifyContentBean);
		
		MultipartFile[] upload_file = modifyContentBean.getUpload_file();
		String content_file = null;
		if(!upload_file[0].getOriginalFilename().equals("")) {
			
			boardDao.modifyContentFile1(content_idx);
		
			for(MultipartFile files : upload_file) {
				
				content_file = getFileName(files);
				
				boardDao.modifyContentFile2(content_idx, content_file);
				
			}
			
		}
		
	}
	
	public void deleteContentInfo(int content_idx) {
		boardDao.deleteContentInfo(content_idx);
	}
	
	public PageBean getContentCnt(int board_info_idx, int page) {
		int contentCnt = boardDao.getContentCnt(board_info_idx);
		PageBean pageBean = new PageBean(contentCnt, page, page_contentCnt, page_buttonCnt);
		return pageBean;
	}
	
	public void addViews(@Param("views")int views, @Param("content_idx")int content_idx) {
		boardDao.addViews(views, content_idx);
	}
	
}