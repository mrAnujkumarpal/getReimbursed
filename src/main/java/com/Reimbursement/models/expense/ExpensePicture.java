package com.Reimbursement.models.expense;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expense_bills")
public class ExpensePicture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int picture_id;

    @Column(name = "mimeType")
    private String mimeType;

    @Column(name = "pictureName")
    private String pictureName;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "updateDate")
    private Date updateDate;

    @Lob
    @Column(name = "ExpBill_Data")
    private byte[] expBillData;

    @Transient
    private String base64;

    @Column(name = "bill_path")
    private String billPath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exp_id")
    private Expense expense;

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getBillPath() {
        return billPath;
    }

    public void setBillPath(String billPath) {
        this.billPath = billPath;
    }

    public byte[] getExpBillData() {
        return expBillData;
    }

    public void setExpBillData(byte[] expBillData) {
        this.expBillData = expBillData;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
