package org.nifoo.showcase.db.cache;

import java.io.IOException;

import org.mockito.Mockito;
import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.service.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
