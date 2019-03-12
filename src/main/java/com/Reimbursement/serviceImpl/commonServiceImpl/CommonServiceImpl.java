package com.Reimbursement.serviceImpl.commonServiceImpl;

import com.Reimbursement.dao.commonDaoService.CommonDaoService;
import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.commonModel.PaymentMode;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.service.commonServices.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonDaoService commonDaoService;

    public Employee authorize(String username, String password) {
        return commonDaoService.checkUser(username, password);
    }

    @Override
    public List<Location> getAllLocations() {
        return commonDaoService.getAllLocations();
    }

    @Override
    public Location getLocationById(int id) {
        System.out.println("Common service impl " + id);
        return commonDaoService.getLocationBylocId(id);
    }

    @Override
    public Location addLocation(Location location) {
        return commonDaoService.saveNewLocation(location);
    }

    @Override
    public void deleteLocation(Location location) {
        commonDaoService.deleteLocation(location);
    }


    @Override
    public List<Vendor> getAllVendors() {
        return commonDaoService.getAllVendorsList();
    }

    @Override
    public  Vendor addNewVendor(Vendor vendor){
        return commonDaoService.addNewVendorDetails(vendor);
    }

    @Override
    public  Vendor getVendorDetailsByVendorID(int vendor_Id){
        return  commonDaoService.getVendorDetailsByVendorId(vendor_Id);
    }

    @Override
    public void  deleteVendorByVendorId(Vendor ven){
        commonDaoService.makeDisableVendor(ven);
    }

    @Override
    public List<PaymentMode> getAllPaymentMode(){
        return  commonDaoService.fetchAllPaymentMode();
    }

    @Override
    public boolean isVendorExist(Vendor vendor){
        return commonDaoService.findVendorByName(vendor.getVendor_name());
    }

    @Override
    public boolean isLocationExist(Location location) {
        return commonDaoService.findLocationByName(location.getLocation_name());
    }


}
