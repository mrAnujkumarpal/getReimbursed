package com.Reimbursement.models.empModel;

import com.Reimbursement.models.commonModel.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Employees")
public class Employee implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name = "firstname")
    public String fName;
    @Column(name = "lastname")
    public String lName;
    @Column(name = "email")
    public String email;
    @Column(name = "mobileNo")
    public String mobile;
    @Column(name = "password")
    public String password;
    @Column(name = "Emp_DOJ")
    public Date createdDate;
    @Column(name = "enable")
    public boolean enabled = true;
    @Column(name = "Org_Name")
    public String org_code ;

    public String getOrg_code() {
        return org_code;
    }

    public void setorg_code(String org_code) {
        org_code = org_code;
    }

//  private  int  submittedTo, approvedTo, employeeApproverMappingId;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JsonManagedReference
    public Employee subimitter_To;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JsonManagedReference
    public Employee approver_To;

    @OneToOne(cascade = CascadeType.DETACH)
    public EmployeeRole empRole;

    @OneToOne(cascade = CascadeType.DETACH)
    public Location location;

//    @OneToOne(cascade = CascadeType.DETACH)
//    public Employee employee;

/*

    @JoinColumn(name = "EmployeeApproverMappingId")
    private EmployeeApproverMapping employeeApproverMapping;

    @OneToOne(fetch = FetchType.EAGER)
    public EmployeeApproverMapping getEmployeeApproverMapping() {
        return employeeApproverMapping;
    }

    public void setEmployeeApproverMapping(EmployeeApproverMapping employeeApproverMapping) {
        this.employeeApproverMapping = employeeApproverMapping;
        this.employeeApproverMappingId = employeeApproverMapping.getId();
    }

    @Transient
    public int getSubmittedTo() {
        return submittedTo;
    }

    public void setSubmittedTo(int submittedTo) {
        this.submittedTo = submittedTo;

    }

    @Transient
    public int getApprovedTo() {
        return approvedTo;
    }

    public void setApprovedTo(int approvedTo) {
        this.approvedTo = approvedTo;

    }

*/

    public Employee getSubimitter_To() {
        return subimitter_To;
    }

    public void setSubimitter_To(Employee subimitter_To) {
        this.subimitter_To = subimitter_To;
    }

    public Employee getApprover_To() {
        return approver_To;
    }

    public void setApprover_To(Employee approver_To) {
        this.approver_To = approver_To;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EmployeeRole getEmpRole() {
        return empRole;
    }

    public void setEmpRole(EmployeeRole empRole) {
        this.empRole = empRole;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

   /* public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
