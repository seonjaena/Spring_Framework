package com.sj.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import com.sj.spring.beans.UserBean;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {

	@Bean(name = "loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}

}
