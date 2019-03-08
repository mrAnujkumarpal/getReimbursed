package com.Reimbursement.models.commonModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vendors")
public class Vendor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vendor_Id;

    @Column(name = "vendor_name")
    private String vendor_name;

    @Column(name = "vendor_address")
    private String vendor_address;

    @Column(name = "vendor_phno")
    private String vendor_phno;

    @OneToOne(cascade = CascadeType.DETACH)
    public Location location;

    @Column(name = "enable")
    public boolean enabled = true;

    @Column(name = "created_Date")
    private Date created_Date;

    @Column(name = "created_By")
    private String created_By;

    //Getter & setter below::::::::


    public int getVendor_Id() {
        return vendor_Id;
    }

    public void setVendor_Id(int vendor_Id) {
        this.vendor_Id = vendor_Id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_address() {
        return vendor_address;
    }

    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    public String getVendor_phno() {
        return vendor_phno;
    }

    public void setVendor_phno(String vendor_phno) {
        this.vendor_phno = vendor_phno;
    }

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }
}
