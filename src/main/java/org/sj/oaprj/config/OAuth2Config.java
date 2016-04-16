/**
 * 
 */
package org.sj.oaprj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author zhen.pan
 *
 */

@Configuration
public class OAuth2Config {
	/*@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			// Since we want the protected resources to be accessible in the UI as well we need 
			// session creation to be allowed (it's disabled by default in 2.0.6)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
            .csrf()
            .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
            .disable()
            .headers()
            .frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/logs/**").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers( "/api/web/v1/users/register/email" ).permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/uploads/**").authenticated()
            .antMatchers("/metrics/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/health/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/trace/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/dump/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/shutdown/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/beans/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/configprops/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/info/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/autoconfig/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/env/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/trace/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/api-docs/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/protected/**").authenticated();
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;
		@Value("${security.oauth2.client.client-id}")
		private String clientId;
		@Value("${security.oauth2.client.client-secret}")
		private String clientSecret;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients
            .inMemory()
            .withClient(clientId)
            .scopes("read", "write")
            .authorities("ROLE_ADMIN", "ROLE_USER")
            .authorizedGrantTypes("password", "refresh_token")
            .secret(clientSecret)
            .accessTokenValiditySeconds(1800);
		}

		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore)
					.authenticationManager(authenticationManager);
		}
	}*/
}
