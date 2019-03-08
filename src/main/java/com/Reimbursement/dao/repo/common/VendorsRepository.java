package com.Reimbursement.dao.repo.common;

import com.Reimbursement.models.commonModel.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorsRepository extends JpaRepository<Vendor, Integer> {

        List<Vendor> findAllByEnabled(boolean enabled);

        @Query("from Vendor where vendor_name=?1")
        Vendor findVendorByName(String vendorName);

        }
