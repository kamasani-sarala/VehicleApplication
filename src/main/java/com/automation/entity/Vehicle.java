package com.automation.entity;



	import java.util.Objects;

import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;

	@Entity
	public class Vehicle {
	
		@Id
		@GeneratedValue
		private int vId;
		private String vehicleType;
		private String currentLocation;
		private int capacity;
		
		private int rId;
		public Vehicle() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Vehicle(int vId, String vehicleType, String currentLocation, int capacity, int rId) {
			super();
			this.vId = vId;
			this.vehicleType = vehicleType;
			this.currentLocation = currentLocation;
			this.capacity = capacity;
			this.rId = rId;
		}
		public int getvId() {
			return vId;
		}
		public void setvId(int vId) {
			this.vId = vId;
		}
		public String getVehicleType() {
			return vehicleType;
		}
		public void setVehicleType(String vehicleType) {
			this.vehicleType = vehicleType;
		}
		public String getCurrentLocation() {
			return currentLocation;
		}
		public void setCurrentLocation(String currentLocation) {
			this.currentLocation = currentLocation;
		}
		public int getCapacity() {
			return capacity;
		}
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		public int getrId() {
			return rId;
		}
		public void setrId(int rId) {
			this.rId = rId;
		}
		@Override
		public String toString() {
			return "Vehicle [vId=" + vId + ", vehicleType=" + vehicleType + ", currentLocation=" + currentLocation
					+ ", capacity=" + capacity + ", rId=" + rId + "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(rId, vId,vehicleType,currentLocation,capacity);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vehicle other = (Vehicle) obj;
			return rId == other.rId && currentLocation == other.currentLocation && vId == other.vId && Objects.equals(vehicleType, other.vehicleType)
					&& Objects.equals(capacity, other.capacity) && Objects.equals(vehicleType, other.vehicleType)
					&& Objects.equals(currentLocation, other.currentLocation);
		}

		
	}


