package org.sj.oaprj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//	@Bean
//	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//		return new SecurityEvaluationContextExtension();
//	}
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals", "/login", "/css/**", "/js/**", "/img/**", "/api/**", "/error");
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/login", "/css/**", "/js/**", "/img/**", "/api/**", "/error")
			.permitAll()
			.anyRequest()	//.authenticated()
			.fullyAuthenticated();
		
		
		/**
		 * <login />
		 */
		http.formLogin().loginPage("/login");
		
		/**
         * <logout invalidate-session="true" delete-cookies="JSESSIONID" />
         */
		http.logout().logoutUrl("/logout");
//			.logoutSuccessUrl("/")
//			.invalidateHttpSession(true)
//			.deleteCookies("JSESSIONID")
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		/**
         * <session-management session-fixation-protection="newSession"/>
         */
//		http.sessionManagement()
//            .maximumSessions(1)
//            .expiredUrl("/")
//            .maxSessionsPreventsLogin(true)
//            .sessionRegistry(sessionRegistry());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("user").password("user").roles("USER").and()
//		.withUser("admin").password("admin").roles("USER", "ADMIN");
		auth.userDetailsService(userDetailsService);
//		auth.passwordEncoder(new BCryptPasswordEncoder());
	}

    // Work around https://jira.spring.io/browse/SEC-2855
//    @Bean
//    public SessionRegistry sessionRegistry() {
//        SessionRegistry sessionRegistry = new SessionRegistryImpl();
//        return sessionRegistry;
//    }
}