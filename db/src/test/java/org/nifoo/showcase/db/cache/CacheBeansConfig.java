package org.nifoo.showcase.db.cache;

import org.mockito.Mockito;
import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheBeansConfig{
	
	@Bean
	public UserService getUserService(){
		return new UserService();
	}
	
	@Bean
	public UserDao getUserDao(){
		return Mockito.mock(UserDao.class);
	}
}
