package com.Reimbursement.service.commonServices;


import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.commonModel.PaymentMode;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;

import java.util.List;

public interface CommonService {

    public Employee authorize(String username, String password);

    public List<Location> getAllLocations();

    public Location getLocationById(int id);

    public Location addLocation(Location location);

    public void deleteLocation(Location location);

    public List<Vendor> getAllendors();

    public  Vendor addNewVendor(Vendor vendor);

    public  Vendor getVendorDetailsByVendorID(int vendor_Id);

    public void deleteVendorByVendorId(Vendor ven);

    public List<PaymentMode> getAllPaymentMode();

    public boolean isVendorExist(Vendor vendor);

    public boolean isLocationExist(Location location);





}
