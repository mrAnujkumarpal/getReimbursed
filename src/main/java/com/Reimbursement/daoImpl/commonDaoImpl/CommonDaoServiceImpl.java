package com.Reimbursement.daoImpl.commonDaoImpl;


import com.Reimbursement.dao.commonDaoService.CommonDaoService;
import com.Reimbursement.dao.repo.common.LocationRepository;
import com.Reimbursement.dao.repo.common.PaymentModeRepository;
import com.Reimbursement.dao.repo.common.VendorsRepository;
import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.commonModel.PaymentMode;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDaoServiceImpl implements CommonDaoService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    VendorsRepository vendorsRepository;

    @Autowired
    PaymentModeRepository paymentModeRepository;

    public Employee checkUser(String email, String password) {

        System.out.println("username : " + email);
        System.out.println("Password : " + password);

        return employeeRepository.findByEmailAndPasswordAndEnabled(email, password, true);

    }


    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }


    public Location saveNewLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocationBylocId(int id) {
        return locationRepository.getOne(id);
    }

    public void deleteLocation(Location loc) {
        locationRepository.delete(loc);
    }


    public List<Vendor> getAllVendorsList() {
        return vendorsRepository.findAllByEnabled(true);
    }

    public Vendor addNewVendorDetails(Vendor vendor) {
       return vendorsRepository.save(vendor);
    }

    public Vendor getVendorDetailsByVendorId(int vendor_Id) {
        return vendorsRepository.findOne(vendor_Id);
    }

    public void makeDisableVendor(Vendor vendor) {
       vendor.setEnabled(false);
       vendorsRepository.save(vendor);

    }

    public List<PaymentMode> fetchAllPaymentMode(){
        return paymentModeRepository.findAll();
    }

    @Override
    public boolean findVendorByName(String vendorName){
        return vendorsRepository.findVendorByName(vendorName)!=null;
    }

    @Override
    public boolean findLocationByName(String locationName){
        return locationRepository.findLocationByName(locationName)!=null;
    }

}
