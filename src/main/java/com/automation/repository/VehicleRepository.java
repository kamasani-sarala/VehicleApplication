package com.automation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automation.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

     Vehicle getVehicleByvId(int vId);
     
 	@Query("from Vehicle v where v.vId=:vId")
 	public Vehicle getVehiclebyvId(@Param(value="vId")Integer vId );


 	public Object findByvehicleType(String vehicleType);


 	//public Object findByvehicleTypeAndcapacity(String vehicleType, int capacity);
}

