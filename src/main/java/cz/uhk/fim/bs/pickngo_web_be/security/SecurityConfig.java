package cz.uhk.fim.bs.pickngo_web_be.security;

import cz.uhk.fim.bs.pickngo_web_be.CustomUser.CustomUserDetailsService;
import cz.uhk.fim.bs.pickngo_web_be.Employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableJpaRepositories(basePackageClasses = EmployeeRepository.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.userDetailsService=customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/employee/all").authenticated()
                .antMatchers("/baguetteOrder/update/**").authenticated()
                //to pod tim odkomentovat po vytvoreni roli a admina
                .antMatchers("/employee/admin/create/**").hasRole("Admin")
                .antMatchers("/employee/create/**","/employee/remove/**", "/employee/update/**" ).hasRole("Admin")
                .antMatchers("/role/all", "/role/detail/**", "/role/create", "/role/update/**", "/role/remove/**").hasAuthority("ROLE_Admin")
                .antMatchers("/ingredients/create", "/ingredients/update/**", "/ingredients/remove/**").hasAuthority("ROLE_Admin")
                .antMatchers("/ingredientType/create", "/ingredientType/update/**", "/ingredientType/remove/**").hasAuthority("ROLE_Admin")
                .antMatchers("/item/all").hasAuthority("ROLE_Employee")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
