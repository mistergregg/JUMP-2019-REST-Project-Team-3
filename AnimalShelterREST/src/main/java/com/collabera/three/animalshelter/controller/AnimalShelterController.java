package com.collabera.three.animalshelter.controller;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.three.animalshelter.model.Animal;
import com.collabera.three.animalshelter.model.Foster;
import com.collabera.three.animalshelter.model.Shelter;
import com.collabera.three.animalshelter.model.Staff;
import com.collabera.three.animalshelter.service.FosterService;
import com.collabera.three.animalshelter.repository.FosterRepository;
import com.collabera.three.animalshelter.repository.ShelterRepo;

import com.collabera.three.animalshelter.service.ShelterService;
import com.collabera.three.animalshelter.service.StaffService;
import com.collabera.three.animalshelter.service.AnimalService;
import com.collabera.three.animalshelter.repository.AnimalRepository;

import com.collabera.three.animalshelter.service.ShelterService;
import com.collabera.three.animalshelter.service.AnimalService;
import com.collabera.three.animalshelter.repository.AnimalRepository;

@RestController
@RequestMapping(path = "/animalshelter")
public class AnimalShelterController
{
	/* Below is Animal table controller mapping */
	 	@Autowired
		private AnimalService animalService;
	 	
	  	@PostMapping(path = "/addAnimal2")
		public @ResponseBody String addNewAnimal2 (@RequestBody @Valid Animal aAnimal)
		{
	  		System.out.println(aAnimal.getName());
	  		
			animalService.addAnimal(aAnimal.getImagePath(),
					aAnimal.getName(),
					aAnimal.getGender(),
					aAnimal.getSpecies(),
					aAnimal.getBreed(), 
					aAnimal.getSize(),
					aAnimal.getAge());
			return "Saved";
		}
		
		@GetMapping(path = "/allAnimals")
		public @ResponseBody Iterable<Animal> getAllAnimals()
		{
			return animalService.findAll();
		}
		
		@GetMapping(path = "/getAnimal/{animalId}")
		public Animal getAnimalById(@PathVariable String animalId) {
			return animalService.getAnimalById(animalId);
		}
		
		@PutMapping("/updateAnimal")
		public String updateAnimal(@RequestBody Animal animal)
		{
			animalService.updateAnimal(animal);
			return "Updated";
		}
		
		@DeleteMapping("/deleteAnimal/{animalId}")
		public void deleteAnimal(@PathVariable String animalId)
		{
			animalService.deleteAnimal(Integer.parseInt(animalId));
		}
	/* Above is Animal table controller mapping */

	/* Below is Location table controller mapping */
	@Autowired // will automatically create this object for you on runtime
	ShelterService service;
	private ShelterRepo shelterRepo;

  	@PostMapping(path = "/addShelter2")
	public @ResponseBody String addNewShelter2(@RequestBody @Valid Shelter aShelter)
	{
  		System.out.println(aShelter.getZIP());
  		
  		service.addShelter(aShelter.getShelter_name(), 
  				aShelter.getAddressNo(), 
  				aShelter.getStreet(), 
  				aShelter.getTownship(), 
  				aShelter.getState(), 
  				aShelter.getZIP(), 
  				aShelter.getImage_path());
  		return "Saved";
  	}

	@GetMapping("/getShelter/{shelterid}")
	public Shelter getShelterById(@PathVariable String shelterid)
	{
		return service.getShelterById(shelterid);
	}
  	@GetMapping(path = "/allShelters")
	public Iterable<Shelter> getAllShelters()
	{
		return service.findAll();
	}
  	@PutMapping(path = "/updateShelter")
  	public String updateShelter(@RequestBody Shelter shelter)
  	{
  		service.updateShelter(shelter);
  		return "Updated";
  	}
  	@DeleteMapping("/deleteShelter/{shelterid}")
	public void deleteShelter(@PathVariable String shelterid)
	{
		service.deleteShelter(Integer.parseInt(shelterid));
	}

	/* Above is Location table controller mapping */
	/* Below is Worker table controller mapping */
	@Autowired
	private StaffService staffService;

  	@PostMapping("/addStaff2")
	public String addNewStaff2 (@RequestBody @Valid Staff aStaff)
	{
  		System.out.println(aStaff.getName());
  		staffService.addStaff(aStaff.getImagePath(), aStaff.getName(), aStaff.getTitle());
		return "Added";
	}
	
	@GetMapping("/allStaff")
	public Iterable<Staff> getAllStaff()
	{
		return staffService.findAll();
	}
	
	@GetMapping(path = "/getStaff/{staffId}")
	public Staff getStaffById(@PathVariable String staffId) {
		return staffService.getStaffById(staffId);
	}
	
	@PutMapping("/updateStaff")
	public String updateStaff(@RequestBody Staff staff)
	{
		staffService.updateStaff(staff);
		return "Updated";
	}
	
	@DeleteMapping("/deleteStaff/{staffId}")
	public void staffAnimal(@PathVariable String staffId)
	{
		staffService.deleteStaff(Integer.parseInt(staffId));
	}
	/* Above is Worker table controller mapping */
	
	
	/* Below is Foster table controller mapping */
  
		@Autowired
		private FosterService fosterService;
  
	  	@PostMapping("/addFoster")
		public String addNewFoster (@RequestParam String name, 
				@RequestParam String animalType, 
				@RequestParam String fosterDescription,
				@RequestParam String estimatedDateOfBirth,
				@RequestParam String imagePath)
		{
	  		fosterService.addFoster(name, animalType, fosterDescription, estimatedDateOfBirth, imagePath);
			return "Saved";
		}
	  	
		@GetMapping("/allFoster")
		public Iterable<Foster> getAllFosters()
		{
			return fosterService.findAll();
		}
		
		@GetMapping("/getFoster/{fosterId}")
		public Foster getFosterById(@PathVariable String fosterId)
		{
			return fosterService.getFosterById(fosterId);
		}
		
		@PutMapping("/updateFoster")
		public String updateFoster(@RequestBody Foster foster)
		{
			fosterService.updateFoster(foster);
			return "Updated";
		}
		
		@DeleteMapping("/deleteFoster/{fosterId}")
		public void deleteFoster(@PathVariable String fosterId)
		{
			fosterService.deleteFoster(Integer.parseInt(fosterId));
		}
		
	  	@PostMapping("/addFoster2")
	  	public String addNewFoster2(@RequestBody @Valid Foster aFoster)
	  	{
	  		System.out.println(aFoster.getAnimalName());
	  		fosterService.addFoster(aFoster.getAnimalName(), aFoster.getAnimalType(), aFoster.getFosterDescription(), aFoster.getEstimatedDateOfBirth().toString(), aFoster.getImagePath());
	  		return "Added";
	  	}
}
