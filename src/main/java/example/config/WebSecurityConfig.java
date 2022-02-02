package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().
        authorizeRequests().antMatchers("/loginForm","/loginForm?error=true").permitAll()
        .anyRequest().authenticated().and()
        .formLogin().loginPage("/loginForm").usernameParameter("userName").passwordParameter("password").
        loginProcessingUrl("/login").defaultSuccessUrl("/",true).failureUrl("/loginForm?error=true").
        and()
        .logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){


    }
    
}
