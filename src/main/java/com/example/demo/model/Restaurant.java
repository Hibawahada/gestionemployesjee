package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;




@Entity
@Table(name = "restaurants")
public class Restaurant { 
	
	private long restaurantId;
    private String address;
    private String city;
   
 
    public Restaurant() {
  
    }
 
    public Restaurant(String address, String city) {
         this.address = address;
         this.city = city;
        
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id", nullable = false)
        public long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
 
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
 
    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
 
 

    @Override
    public String toString() {
        return "Employee [id=" + restaurantId + ", address=" + address + ", city=" + city + "]";
    }

}