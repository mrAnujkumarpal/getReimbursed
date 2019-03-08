package com.Reimbursement.models.expense;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expenseType")
public class ExpenseType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_type_Id")
    private int expType_Id;

    @Column(name = "expense_type_Name")
    private String expType_Name;

    @Column(name = "expense_type_Enabled")
    private Boolean enabled = true;

    @Column(name = "created_Date")
    public Date created_Date;

    public int getExpType_Id() {
        return expType_Id;
    }

    public void setExpType_Id(int expType_Id) {
        this.expType_Id = expType_Id;
    }

    public String getExpType_Name() {
        return expType_Name;
    }

    public void setExpType_Name(String expType_Name) {
        this.expType_Name = expType_Name;
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
