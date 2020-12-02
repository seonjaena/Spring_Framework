package com.sj.spring.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.spring.beans.MediaBean;
import com.sj.spring.beans.PageBean;
import com.sj.spring.beans.UserBean;
import com.sj.spring.dao.MediaDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class MediaService {

	@Value("${media.media}")
	private String media_media;
	
	@Value("${media.image}")
	private String media_image;
	
	@Value("${upload.path}")
	private String upload_path;
	
	@Value("${page.contentCnt}")
	private int page_contentCnt;
	
	@Value("${page.buttonCnt}")
	private int page_buttonCnt;
	
	@Autowired
	private MediaDao mediaDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	public List<MediaBean> getMediaList(int media_info_idx, int page) {
		
		int start = (page - 1) * page_contentCnt;
		RowBounds rowBounds = new RowBounds(start, page_contentCnt);
		
		return mediaDao.getMediaList(media_info_idx, rowBounds);
		
	}
	
	public String getMediaName(MultipartFile upload_file) {
		
		HashService hashService = new HashService();
		
		String file_name = hashService.Encrypt(upload_file.getOriginalFilename());
		
		try {
			
			upload_file.transferTo(new File(media_media + "/" + file_name));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
		
	}
	
	public String getImageName(MultipartFile upload_file) {
		
		HashService hashService = new HashService();
		
		String file_name = hashService.Encrypt(upload_file.getOriginalFilename());
		
		try {
			
			upload_file.transferTo(new File(media_image + "/" + file_name));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
		
	}
	
	public void addMediaInfo(MediaBean writeMediaBean) {
		
		MultipartFile media_fileMF = writeMediaBean.getMedia_fileMF();
		
		String file_name1 = getMediaName(media_fileMF);
		writeMediaBean.setMedia_file(file_name1);
		writeMediaBean.setFilesize(media_fileMF.getSize());
		
		if(writeMediaBean.getMedia_posterMF().getSize() > 0) {
			String file_name2 = getImageName(writeMediaBean.getMedia_posterMF());
			writeMediaBean.setMedia_poster(file_name2);
		}
		writeMediaBean.setMedia_writer_idx(loginUserBean.getUser_idx());
		mediaDao.addMediaInfo(writeMediaBean);
		
	}
	
	public MediaBean getMediaInfo(int media_idx) {
		return mediaDao.getMediaInfo(media_idx);	
	}
	
	public PageBean getMediaCnt(int page, int media_info_idx) {
		int contentCnt = mediaDao.getMediaCnt(media_info_idx);
		PageBean pageBean = new PageBean(contentCnt, page, page_contentCnt, page_buttonCnt);
		return pageBean;
	}

	public void deleteMediaInfo(int media_idx) {
		mediaDao.deleteMediaInfo(media_idx);
	}
	
	public void getModifyMediaInfo(MediaBean modifyMediaBean, int media_idx) {
		
		MediaBean tempModifyMediaBean = mediaDao.getMediaInfo(media_idx);
		
		modifyMediaBean.setMedia_idx(tempModifyMediaBean.getMedia_idx());
		modifyMediaBean.setMedia_subject(tempModifyMediaBean.getMedia_subject());
		modifyMediaBean.setMedia_date(tempModifyMediaBean.getMedia_date());
		modifyMediaBean.setMedia_poster(tempModifyMediaBean.getMedia_poster());
		modifyMediaBean.setMedia_file(tempModifyMediaBean.getMedia_file());
		modifyMediaBean.setFilesize(tempModifyMediaBean.getFilesize());
		modifyMediaBean.setMedia_writer_name(tempModifyMediaBean.getMedia_writer_name());
		modifyMediaBean.setMedia_writer_idx(tempModifyMediaBean.getMedia_writer_idx());
		
	}
	
	public void modifyMediaInfo(MediaBean modifyMediaBean) {
		
		MultipartFile media_fileMF = modifyMediaBean.getMedia_fileMF();
		
		if(media_fileMF.getSize() > 0) {
			
			String file_name1 = getMediaName(media_fileMF);
			modifyMediaBean.setMedia_file(file_name1);
			modifyMediaBean.setFilesize(media_fileMF.getSize());
			
		}
		
		if(modifyMediaBean.getMedia_posterMF().getSize() > 0) {
			
			String file_name2 = getImageName(modifyMediaBean.getMedia_posterMF());
			modifyMediaBean.setMedia_poster(file_name2);
			
		}
		
		mediaDao.modifyMediaInfo(modifyMediaBean);
		
	}
	
}