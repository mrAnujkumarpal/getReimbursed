package com.Reimbursement.models.commonModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_mode")
public class PaymentMode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_Id")
    private int pay_Id;

    @Column(name = "pay_type")
    private String pay_type;

    @Column(name = "pay_type_Enabled")
    private Boolean enabled = true;

    public int getPay_Id() {
        return pay_Id;
    }

    public void setPay_Id(int pay_Id) {
        this.pay_Id = pay_Id;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
