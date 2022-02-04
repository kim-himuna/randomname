package example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userService;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/javascript/**","/stylesheet/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().
        authorizeRequests().antMatchers("/loginForm","/loginForm?error=true","/","/packs/shuffle/**","/packs/{packId}").permitAll()
        .anyRequest().authenticated();

        http.formLogin().loginPage("/loginForm").loginProcessingUrl("/login")
        .defaultSuccessUrl("/",true).failureUrl("/loginForm?error=true")
        .usernameParameter("username").passwordParameter("password")
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/loginForm").deleteCookies("JSESSIONID")
        .invalidateHttpSession(true).permitAll()
        .and()
        .sessionManagement().invalidSessionUrl("/loginForm");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}