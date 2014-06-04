package org.nifoo.showcase.db.cache;

import java.io.IOException;

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
@ComponentScan(basePackages = "org.nifoo.showcase.db.service")
@EnableCaching(proxyTargetClass = true)
public class CacheConfig implements CachingConfigurer {

	@Bean
	@Override
	public CacheManager cacheManager() {

		try {
			net.sf.ehcache.CacheManager ehcacheCacheManager = net.sf.ehcache.CacheManager.create(new ClassPathResource(
					"ehcache.xml").getInputStream());

			EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
			return cacheCacheManager;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		// 如果在Cache注解上没有指定key的话@CachePut(value = "user")，会使用KeyGenerator进行生成一个
		return new SimpleKeyGenerator();
	}
}
