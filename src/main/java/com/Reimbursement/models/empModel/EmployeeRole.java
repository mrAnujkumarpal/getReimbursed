package com.Reimbursement.models.empModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "EmployeeRoles")
public class EmployeeRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name = "EmployeeRole")
    public String empRole;
    @Column(name = "EmployeeRoleCode")
    public String empRoleCode;
    @Column(name = "created_Date")
    public Date created_Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public String getEmpRoleCode() {
        return empRoleCode;
    }

    public void setEmpRoleCode(String empRoleCode) {
        this.empRoleCode = empRoleCode;
    }

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

}
