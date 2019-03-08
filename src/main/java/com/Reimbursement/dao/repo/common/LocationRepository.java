package com.Reimbursement.dao.repo.common;



import com.Reimbursement.models.commonModel.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("from Location where location_name=?1")
    Location findLocationByName(String LocationName);
}
