package com.finalledger.security;

import com.finalledger.security.oauth.CustomOAuth2User;
import com.finalledger.security.oauth.CustomOAuth2UserService;
import com.finalledger.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponseType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()); // How to encode and verify passwords
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/profile") // user's home page
                .permitAll()

                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value

                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/register", "/login", "/about")
                .permitAll()

                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers("/profile", "/ledger/personal", "/oauth2/**")
                .authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

                        usersLoader.processOAuthPostLogin(oAuth2User.getEmail());

                        response.sendRedirect("/profile");
                    }
                })

        ;
    }

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

}