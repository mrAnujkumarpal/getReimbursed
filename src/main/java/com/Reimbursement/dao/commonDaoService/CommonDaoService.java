package com.Reimbursement.dao.commonDaoService;


import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.commonModel.PaymentMode;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;

import java.util.List;


public interface CommonDaoService  {

    public Employee checkUser(String username, String password);

    public List<Location> getAllLocations();

    public  Location getLocationBylocId(int id);

    public  Location saveNewLocation(Location location);

    public  void deleteLocation(Location location);

    public  List<Vendor> getAllVendorsList();

    public  Vendor addNewVendorDetails(Vendor vendor);

    public  Vendor getVendorDetailsByVendorId(int vendor_Id);

    public void  makeDisableVendor(Vendor vendor);

    public List<PaymentMode> fetchAllPaymentMode();

    public boolean findVendorByName(String vendorName);

    public boolean findLocationByName(String locationName);

}
