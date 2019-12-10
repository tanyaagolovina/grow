package com.tetianaholovina.weatherforecast.config;

import com.tetianaholovina.weatherforecast.model.domain.User;
import com.tetianaholovina.weatherforecast.service.schedule.UserService;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .and()
                .authorizeRequests()
                .mvcMatchers("/weather/forecasts", "/weather/forecasts/current", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(){
        return map -> {
            String id = (String) map.get("sub");
            User user = userService.findById(id).orElseGet(() -> {
                        User newUser = new User();
                        newUser.setId(id);
                        newUser.setName((String)map.get("name"));
                        newUser.setEmail((String)map.get("email"));
                        return  newUser;
                    });
            user.setLastVisit(LocalDateTime.now());
            return userService.save(user);
        };
    }
}
