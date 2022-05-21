package com.Reimbursement.config;

import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.models.empModel.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppUserDetailsService implements UserDetailsService {

  @Autowired EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String loginedEmail) throws UsernameNotFoundException {
    Employee emp = employeeRepository.findByEmail(loginedEmail);
    System.out.println("MMM----> verified email  " + emp.getEmail());
    if (emp == null) {
      throw new UsernameNotFoundException("User not authorized");
    }
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");

    UserDetails userDetails =
        new User(emp.getEmail(), emp.getPassword(), Arrays.asList(grantedAuthority));




    return userDetails;
  }
}
