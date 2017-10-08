package com.aerolinea.config;

import javax.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DriverManagerDataSource dataSource;
    @Autowired
    Codificar codificar;

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
    }
    */
    
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/index.xhtml").authenticated()
                .antMatchers("/Pais/**").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().and()
                .httpBasic();
    }
    */
    
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/Pais/**").hasRole("OTRO")
                .antMatchers(HttpMethod.POST, "/Pais").hasRole("OTRO")
                .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/UsuarioForm.xhtml").requiresSecure().and()
                .formLogin().and()
                .httpBasic();
        
        //disable csrf protection
        http.csrf().disable();
    
        //maps the port 8080(http) to 8443(https)
        http
                .portMapper() 
                .http(8080).mapsTo(8443);
    }   
    */
    
    /*
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" 
    */
    
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }
    */
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/").hasAuthority("ROLE_ADMIN")
                .antMatchers("/index.xhtml").authenticated()
                .antMatchers("/Pais/**").hasAuthority("ROLE_OTRO")
                .anyRequest().permitAll().and()
                .formLogin()
                .loginPage("/login.xhtml")
                .defaultSuccessUrl("/index.xhtml").and()
                .httpBasic().and()
                .logout()
                .logoutUrl("/salir")
                .logoutSuccessUrl("/login.xhtml");

        //remember me
        http.rememberMe()
                .key("uniqueAndSecret")
                .tokenValiditySeconds(86400);

    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select idusuario, clave, true from usuario where idusuario=?")
                .authoritiesByUsernameQuery(
                        "select u.idusuario, concat('ROLE_',r.rol) from usuario u, rol r where u.idrol = r.idrol and u.idusuario =  ?")
                .passwordEncoder(codificar);

    }
    /**/    
    
    /*
    @Autowired
    AerolineaUserService AerolineaUserService;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(AerolineaUserService)
                .passwordEncoder(codificar);
    }
    */    
    
}
