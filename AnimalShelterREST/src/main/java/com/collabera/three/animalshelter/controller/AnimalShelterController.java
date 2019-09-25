package com.collabera.three.animalshelter.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.collabera.three.animalshelter.model.AnimalShelterModel;
import com.collabera.three.animalshelter.service.AnimalShelterService;

@RestController
public class AnimalShelterController {
	/* Below is Animal table controller mapping */
	
	/* Above is Animal table controller mapping */
	/* Below is Location table controller mapping */
	@Autowired // will automatically create this object for you on runtime
	AnimalShelterService service;

	@GetMapping("/api/shelterlocation")
	public List<AnimalShelterModel> getShelters() { // Spring Boot handles converting this list to JSON
		return service.getAllShelterLocations();
	}

	@GetMapping("/api/shelterlocation/{shelterlocationid}")
	public AnimalShelterModel getShelter(@PathVariable String shelterid) { // annotation declares use of path variable {shelterid}
		return service.getShelterLocation(Integer.parseInt(shelterid)); // converts from string to an integer before using shelter
	}

	@PostMapping("/api/addshelter")
	public ResponseEntity<String> addShelter(@RequestBody AnimalShelterModel shelter) {  // It is improper to send data through URL for security reasons.
		AnimalShelterModel newShelter = service.addShelter(shelter.getName(), shelter.getAddressNo(), shelter.getStreetName(),
				shelter.getTownship(), shelter.getState(), shelter.getZIP()); // Creates new shelter object and returns it
		System.out.println(newShelter);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") // Sets up path to grab each new added shelter
				.buildAndExpand(newShelter.getId()).toUri();
		return ResponseEntity.created(location)
				.header("shelter", newShelter.getId() + "")
				.body(newShelter.getName()); // Body will be sent back with confirmation header
	}

	@PutMapping("/api/updateshelter") // Pushes one update to the entire object
	public void updateShelter(@RequestBody AnimalShelterModel shelter) {
		service.updateShelter(shelter);
	}

	@DeleteMapping("/api/deleteshelter/{shelterid}")
	public void removeShelter(@PathVariable int shelterid) {
		service.deleteShelter(shelterid);
	}

	@DeleteMapping("/api/deleteshelter/{shelterid}")
	public void removeShelterss(@PathVariable int shelterid) {
		service.deleteAllShelters();
	}

	/* Above is Location table controller mapping */
	/* Below is Worker table controller mapping */
	
	/* Above is Worker table controller mapping */
	/* Below is Foster table controller mapping */
	
}
