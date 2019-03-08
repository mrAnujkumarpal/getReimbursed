package com.Reimbursement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

@Autowired
AppUserDetailsService appUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/home/**").hasAuthority("USER")
                .antMatchers("/myProfile").hasAuthority("USER")
                .antMatchers("/viewAllLocations").hasAuthority("USER")
                .antMatchers("/expenseHistory/**").hasAuthority("USER")
                .antMatchers("/allVendors").hasAuthority("USER")
                .antMatchers("/expenseStatus").hasAuthority("USER")
                .antMatchers("/empRegistration").hasAuthority("USER")
                .antMatchers("/empRegistration/*").hasAuthority("USER")
                //  .antMatchers("/allVendors").permitAll()
                //      .antMatchers("/teamleader/edit").hasAuthority("TEAM_LEADER")
                //      .antMatchers("/search").hasAuthority("ADMIN")
                //      .antMatchers("/rest/getall").hasAuthority("ADMIN")
                //      .antMatchers("/search/all").hasAuthority("ADMIN")
                //      .antMatchers("/admin/search/all").hasAuthority("ADMIN")
                //      .antMatchers("/teamleader/getFile/pdf").hasAuthority("TEAM_LEADER")
                //      .antMatchers("/teamleader/getFile").hasAuthority("TEAM_LEADER")
                //      .antMatchers("/admin/getFile").hasAuthority("ADMIN")
                //       .antMatchers("/admin/getFile/pdf").hasAuthority("ADMIN")
                //       .antMatchers("/registration").permitAll()
                //       .antMatchers("/teamleaderRegistration").permitAll()
                //       .antMatchers("/teamleader/**").hasAuthority("TEAM_LEADER")
                //       .antMatchers("/addteammember/**").hasAuthority("TEAM_LEADER")
                //       .antMatchers("/admin/edit").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/default")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder);
        System.out.println(" configureGlobal " + passwordEncoder);
       // auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
    }

}
