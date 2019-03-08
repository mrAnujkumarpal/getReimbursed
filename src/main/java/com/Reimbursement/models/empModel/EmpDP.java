package com.Reimbursement.models.empModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EmployeeDPTable")
public class EmpDP implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EmpDP_Id")
    private int empDPId;

    @Column(name = "EmpDP_Name")
    private String empDPName;

    @Column(name = "EmpDP_Type")
    private String empDPType;

    @Lob
    @Column(name = "EmpDP_Data")
    private byte[] empDPData;


    @Column(name="employee_id")
    private  int employee_id;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Transient
    private String base64;

    public EmpDP() {

    }

    public EmpDP(String empDPName, String empDPType, byte[] empDPData) {
        this.empDPName = empDPName;
        this.empDPType = empDPType;
        this.empDPData = empDPData;
    }

    public int getEmpDPId() {
        return empDPId;
    }

    public void setEmpDPId(int empDPId) {
        this.empDPId = empDPId;
    }

    public String getEmpDPName() {
        return empDPName;
    }

    public void setEmpDPName(String empDPName) {
        this.empDPName = empDPName;
    }

    public String getEmpDPType() {
        return empDPType;
    }

    public void setEmpDPType(String empDPType) {
        this.empDPType = empDPType;
    }

    public byte[] getEmpDPData() {
        return empDPData;
    }

    public void setEmpDPData(byte[] empDPData) {
        this.empDPData = empDPData;
    }
}

