package com.automation.model;

import java.util.List;
import java.util.Objects;

import com.automation.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {

		private Vehicle vehicle;
		private List<Route> route;
		
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        RequiredResponse that = (RequiredResponse) o;
	        return Objects.equals(vehicle, that.vehicle) &&
	                Objects.equals(route, that.route);
	    }

	 

	    @Override
	    public int hashCode() {
	        return Objects.hash(vehicle, route);
	    
		}		
}
