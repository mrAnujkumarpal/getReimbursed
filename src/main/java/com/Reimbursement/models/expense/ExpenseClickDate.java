package com.Reimbursement.models.expense;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "expense_ClickDate")
public class ExpenseClickDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_createdDate;

    @Column(name = "submitted_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_submittedDate;

    @Column(name = "approved_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_approvedDate;

    @Column(name = "audited_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_auditedDate;

    @Column(name = "reimbursed_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_reimbursedDate;

    @Column(name = "Exp_Id")
    private int exp_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExp_createdDate() {
        return exp_createdDate;
    }

    public void setExp_createdDate(Date exp_createdDate) {
        this.exp_createdDate = exp_createdDate;
    }

    public Date getExp_submittedDate() {
        return exp_submittedDate;
    }

    public void setExp_submittedDate(Date exp_submittedDate) {
        this.exp_submittedDate = exp_submittedDate;
    }

    public Date getExp_approvedDate() {
        return exp_approvedDate;
    }

    public void setExp_approvedDate(Date exp_approvedDate) {
        this.exp_approvedDate = exp_approvedDate;
    }

    public Date getExp_auditedDate() {
        return exp_auditedDate;
    }

    public void setExp_auditedDate(Date exp_auditedDate) {
        this.exp_auditedDate = exp_auditedDate;
    }

    public Date getExp_reimbursedDate() {
        return exp_reimbursedDate;
    }

    public void setExp_reimbursedDate(Date exp_reimbursedDate) {
        this.exp_reimbursedDate = exp_reimbursedDate;
    }

    public int getExp_id() {
        return exp_id;
    }

    public void setExp_id(int exp_id) {
        this.exp_id = exp_id;
    }
}
