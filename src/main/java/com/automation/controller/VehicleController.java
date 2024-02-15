package com.automation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.automation.entity.Vehicle;
import com.automation.model.RequiredResponse;
import com.automation.repository.VehicleRepository;
import com.automation.services.VehicleService;


@RestController
@RequestMapping("/Vehicle")
public class VehicleController {
 
	@Autowired
    VehicleService vehicleService;
	
	 @Autowired
	    private RestTemplate restTemplate;
	 @Autowired
	 VehicleRepository vehicleRepo;
	
	private final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@GetMapping("/")
	public String defaultMessage() {
		return "Welcome to Vehicle Page";
	}

	
	//http://localhost:8090/Vehicle/addVehicle
	@PostMapping("/addVehicle")
	public Vehicle addVehicle(@RequestBody Vehicle vehicle)  {
		try {
			return vehicleService.addVehicle(vehicle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vehicle;
	}
	//http://localhost:8090/Vehicle/allVehicles
	@GetMapping("/allVehicles")
	public List<Vehicle> viewAllVehicle() throws Exception{
	return vehicleService.viewAllVehicles();
	    }

	
	//http://localhost:8090/Vehicle/updateVehicle
	@PutMapping("/updateVehicle")
	public Vehicle updateVehicle(@RequestBody Vehicle vehicle) throws Exception {
		try {
	        Vehicle existingVehicle = vehicleService.getVehicleByvId(vehicle.getvId());
	        if (existingVehicle == null) {
	            throw new Exception("Vehicle not found");
	        }
	       
	        // Update the properties of the existing route
	        existingVehicle.setvId(vehicle.getvId());
	        existingVehicle.setVehicleType(vehicle.getVehicleType());
	        existingVehicle.setCurrentLocation(vehicle.getCurrentLocation());
	        existingVehicle.setCapacity(vehicle.getCapacity());
	      

	        // Call the updateRoute() method from the routeService to save the changes
	        Vehicle updatedVehicle = vehicleService.updateVehicle(existingVehicle);
	        
	        return updatedVehicle;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Vehicle not found");
	    }
	}
	//http://localhost:8090/Vehicle/delete/3
	@DeleteMapping("/delete/{vId}") 
	public String deleteVehicleByvId(@PathVariable int vId)  {
	    try {
	        // Assuming routeService.deleteRouteByrId(vId) method exists to perform the delete operation
	        vehicleService.deleteVehicleByvId(vId);
	        logger.info("Deleted = " + vId + " Data");
	        return "Deleted Id = " + vId + " Data";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error deleting Id = " + vId + " Data: " + e.getMessage();
	    }
	}
	// localhost:8090/Vehicle/sort/capacity
	@GetMapping("/sort/capacity") 
	public List<Vehicle> getBySorting() {

	List<Vehicle> allVehicle = vehicleService.sortingBasedOnCapacity();

	return allVehicle;

}
	//http://localhost:8090/Vehicle/rId/1
    @GetMapping("/rId/{rId}")
    public ResponseEntity<RequiredResponse> getallDataBasedOnRouteId(@PathVariable int rId) throws Exception {
    	logger.info("Fetching data for ID: {}", rId);

        RequiredResponse requiredResponse = new RequiredResponse();

        Vehicle vehicle = vehicleService.getVehicleByvId(rId);
        requiredResponse.setVehicle(vehicle);
        List<com.automation.model.Route> listRoute = restTemplate.getForObject("http://ROUTE/Route/rId/"+rId, List.class);
        requiredResponse.setRoute(listRoute);

        logger.info("Fetched data for ID: {}", rId);

        return new ResponseEntity <>(requiredResponse, HttpStatus.OK);
    }

}
