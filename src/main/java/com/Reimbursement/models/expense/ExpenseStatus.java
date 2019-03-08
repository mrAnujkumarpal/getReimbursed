package com.Reimbursement.models.expense;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expense_status")
public class ExpenseStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_status_Id")
    private int expStatus_Id;

    @Column(name = "expense_status_Name")
    private String expStatus_Name;

    @Column(name = "expense_status_Enabled")
    private Boolean enabled = true;

    @Column(name = "expense_status_created_Date")
    public Date created_Date;


    public int getExpStatus_Id() {
        return expStatus_Id;
    }

    public void setExpStatus_Id(int expStatus_Id) {
        this.expStatus_Id = expStatus_Id;
    }

    public String getExpStatus_Name() {
        return expStatus_Name;
    }

    public void setExpStatus_Name(String expStatus_Name) {
        this.expStatus_Name = expStatus_Name;
    }


    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
