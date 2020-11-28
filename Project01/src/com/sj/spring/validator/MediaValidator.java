package com.sj.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sj.spring.beans.MediaBean;
import com.sj.spring.beans.UserBean;

public class MediaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MediaBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		MediaBean mediaBean = (MediaBean) target;
		
		String beanName = errors.getObjectName();
		
		if(beanName.equals("writeMediaBean")) {
			
			if(mediaBean.getMedia_fileMF().getSize() <= 0) {
				errors.rejectValue("media_fileMF", "CheckMediaExist");
			}
			
		}
		
	}

}