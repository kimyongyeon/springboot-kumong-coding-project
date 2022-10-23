package kyy.springbootkumongcodingproject.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;


@Configuration
@Log4j2
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 : inmemory 방식
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 사용자 계정은 user1
//        auth.inMemoryAuthentication().withUser("user1")
//                // 1111 패스워드 인코딩 결과
//                .password("$2a$10$ionYDEFQ9CiMjfvxc26CH.Mse2SzyuC658QUCCP2TCe.wympR0jWS")
//                .roles("USER");
//    }

    // 인가
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // https://github.com/HomoEfficio/dev-tips/blob/master/Spring%20Security%EC%99%80%20h2-console%20%ED%95%A8%EA%BB%98%20%EC%93%B0%EA%B8%B0.md
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/sample/admin").hasRole("ADMIN")
//                .antMatchers("/h2-console/**").permitAll()  // h2-console + security
                .anyRequest().authenticated()
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
//                .and()
//                .csrf().ignoringAntMatchers("/h2-console/**")
        ;

        http.formLogin(); // 인증/인가 문제시 로그인 화면 이동
        http.csrf().disable(); // get 방식의 logout도 허용
        http.logout(); // 로그아웃 설정
    }
}