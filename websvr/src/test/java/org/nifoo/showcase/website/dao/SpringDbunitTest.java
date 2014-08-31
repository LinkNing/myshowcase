package org.nifoo.showcase.website.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

/**
 * 创建自定义的组合注解简化 spring-test-dbunit 的使用.
 * <p>
 * 
 * 测试类上注解简化如下：
 * <pre><code>
 * {@code @RunWith(SpringJUnit4ClassRunner.class) }
 * {@code @SpringDbUnitTest }
 * public class XXXTest {
 *     ...
 *     }
 * }
 * </code>
 * </pre>
 * 
 * @author Nifoo Ning
 *
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(value = { "classpath:spring-context.xml", "classpath:spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, //
		DirtiesContextTestExecutionListener.class, //
		TransactionDbUnitTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ActiveProfiles("test")
public @interface SpringDbunitTest {
	// nothing need to do.
}