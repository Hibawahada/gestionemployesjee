package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Restaurant;

import com.example.demo.repository.RestaurantRepository;

@RestController
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RequestMapping("/restaurants")
public class RestaurantController {

	 @Autowired
	    private RestaurantRepository restaurantRepository;

	    @GetMapping("/get")
	    public List<Restaurant> getAllRestaurants() {
	        return restaurantRepository.findAll();
	    }

	    @GetMapping("/restaurant/{id}")
	    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value = "id") Long restaurantId)
	        throws ResourceNotFoundException {
	        Restaurant restaurant = restaurantRepository.findById(restaurantId)
	          .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));
	        return ResponseEntity.ok().body(restaurant);
	    }
	    
	    
	    @PostMapping("/restaurants")
	    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
	        return restaurantRepository.save(restaurant);
	    }

	    @PutMapping("/restaurants/{id}")
	    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "id") Long restaurantId,
	         @Valid @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException {
	    	Restaurant restaurant = restaurantRepository.findById(restaurantId)
	        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

	    	restaurant.setAddress(restaurantDetails.getAddress());
	    	restaurant.setCity(restaurantDetails.getCity());
	    
	        final Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
	        return ResponseEntity.ok(updatedRestaurant);
	    }

	    @DeleteMapping("/employees/{id}")
	    public Map<String, Boolean> deleteRestaurant(@PathVariable(value = "id") Long restaurantId)
	         throws ResourceNotFoundException {
	    	Restaurant restaurant = restaurantRepository.findById(restaurantId)
	       .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

	    	restaurantRepository.delete(restaurant);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
