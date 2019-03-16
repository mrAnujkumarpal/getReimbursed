package com.Reimbursement.models.expense;


import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.commonModel.PaymentMode;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "expense")
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int exp_id;

    @Column(name = "Expense_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_Date;

    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_createdDate;

    @Column(name = "CreatedBy")
    private String exp_createdBy;

    @Column(name = "UpdatedBy")
    private String exp_updateddBy;

    @Column(name = "UpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_updatedDate;

    @Column(name = "name")
    private String exp_name;

    @Column(name = "Description")
    private String exp_description;

//    @Column(name = "PaymentMode_id")
//    private int exp_paymentMode;

    @Column(name = "Reimbursed")
    private Boolean reimbursed;

    @Column(name = "Billable")
    private Boolean billable;

    @Column(name = "Deleted")
    private Boolean discard;

    @Column(name = "Amount")
    private long exp_amount;


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

    @Column(name = "rejected_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp_rejectedDate;


    @Column(name = "reimbursByEmpId")
    private int exp_rembrsByEmpId;


    @Column(name = "rejectByEmpId")
    private int exp_rejectByEmpId;


    public Date getExp_rejectedDate() {
        return exp_rejectedDate;
    }

    public void setExp_rejectedDate(Date exp_rejectedDate) {
        this.exp_rejectedDate = exp_rejectedDate;
    }

    public int getExp_rembrsByEmpId() {
        return exp_rembrsByEmpId;
    }

    public void setExp_rembrsByEmpId(int exp_rembrsByEmpId) {
        this.exp_rembrsByEmpId = exp_rembrsByEmpId;
    }

    public int getExp_rejectByEmpId() {
        return exp_rejectByEmpId;
    }

    public void setExp_rejectByEmpId(int exp_rejectByEmpId) {
        this.exp_rejectByEmpId = exp_rejectByEmpId;
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

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Employee employee;

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Location location;

    @OneToOne(cascade = CascadeType.DETACH)
    private Vendor vendor;

    @OneToOne(cascade = CascadeType.DETACH)
    private ExpenseType expenseType;

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private ExpenseStatus expenseStatus;

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private ExpensePicture expensePicture;

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private PaymentMode paymentMode;

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }
    @Transient
    private String expenseDate;

    public int getExp_id() {
        return exp_id;
    }

    public void setExp_id(int exp_id) {
        this.exp_id = exp_id;
    }

    public Date getExp_Date() {
        return exp_Date;
    }

    public void setExp_Date(Date exp_Date) {
        this.exp_Date = exp_Date;
    }

    public Date getExp_createdDate() {
        return exp_createdDate;
    }

    public void setExp_createdDate(Date exp_createdDate) {
        this.exp_createdDate = exp_createdDate;
    }

    public Date getExp_updatedDate() {
        return exp_updatedDate;
    }

    public void setExp_updatedDate(Date exp_updatedDate) {
        this.exp_updatedDate = exp_updatedDate;
    }

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public String getExp_description() {
        return exp_description;
    }

    public void setExp_description(String exp_description) {
        this.exp_description = exp_description;
    }

/*    public int getExp_paymentMode() {
        return exp_paymentMode;
    }

    public void setExp_paymentMode(int exp_paymentMode) {
        this.exp_paymentMode = exp_paymentMode;
    }*/

    public Boolean getReimbursed() {
        return reimbursed;
    }

    public void setReimbursed(Boolean reimbursed) {
        this.reimbursed = reimbursed;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public Boolean getDiscard() {
        return discard;
    }

    public void setDiscard(Boolean discard) {
        this.discard = discard;
    }

    public long getExp_amount() {
        return exp_amount;
    }

    public void setExp_amount(long exp_amount) {
        this.exp_amount = exp_amount;
    }

    public String getExp_createdBy() {
        return exp_createdBy;
    }

    public void setExp_createdBy(String exp_createdBy) {
        this.exp_createdBy = exp_createdBy;
    }

    public String getExp_updateddBy() {
        return exp_updateddBy;
    }

    public void setExp_updateddBy(String exp_updateddBy) {
        this.exp_updateddBy = exp_updateddBy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public ExpensePicture getExpensePicture() {
        return expensePicture;
    }

    public void setExpensePicture(ExpensePicture expensePicture) {
        this.expensePicture = expensePicture;
    }
}