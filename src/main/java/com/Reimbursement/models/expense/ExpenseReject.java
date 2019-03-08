package com.Reimbursement.models.expense;


import com.Reimbursement.models.empModel.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expense_reject")
public class ExpenseReject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_reject_Id")
    private int exp_reject_Id;

    @Column(name = "exp_reject_reason")
    private String exp_reject_reason;

    @Column(name = "exp_reject_Date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date exp_reject_Date;

    @Column(name = "exp_reject_By")
    private String exp_reject_By;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exp_id")
    private Expense expense;


    public int getExp_reject_Id() {
        return exp_reject_Id;
    }

    public void setExp_reject_Id(int exp_reject_Id) {
        this.exp_reject_Id = exp_reject_Id;
    }

    public String getExp_reject_reason() {
        return exp_reject_reason;
    }

    public void setExp_reject_reason(String exp_reject_reason) {
        this.exp_reject_reason = exp_reject_reason;
    }

    public Date getExp_reject_Date() {
        return exp_reject_Date;
    }

    public void setExp_reject_Date(Date exp_reject_Date) {
        this.exp_reject_Date = exp_reject_Date;
    }

    public String getExp_reject_By() {
        return exp_reject_By;
    }

    public void setExp_reject_By(String exp_reject_By) {
        this.exp_reject_By = exp_reject_By;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
