package com.springboot.learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.learning.entity.UserSecurityInfo;
import com.springboot.learning.service.UserSecurityMapper;

@Configuration
// 通过@EnableWebSecurity注解开启Spring Security的功能 不加也可以的，默认是开启的
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityMapper mapper;

	// 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。这里指定了/和/login不需要任何认证就可以访问，其他的路径都必须通过身份验证。
	// 通过formLogin()定义当需要用户登录时候，转到的登录页面。
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll();
		// spring security在集成spring boot的微服务框架后，实现了cas认证和权限控制。但是在使用 post方式
		// 进行调用的时候出现这个问题,所以要需要将该功能关闭掉,不然Swagger2中的post会被禁止
		// 更多内容参考：http://blog.csdn.net/u012373815/article/details/55047285
		http.csrf().disable();
	}

	// 在内存中创建了一个用户，该用户的名称为test，密码为123456，用户角色为USER。
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("test").password("123456").roles("USER");
	}

	// 通过数据库中的信息来验证用户权限，是否可以登录
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			@Override
			public UserSecurityInfo loadUserByUsername(String userName) throws UsernameNotFoundException {
				return mapper.getInfoByName(userName);
			}
		});
	}

}
