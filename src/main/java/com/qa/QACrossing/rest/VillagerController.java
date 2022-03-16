package com.qa.QACrossing.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.QACrossing.entity.Villager;
import com.qa.QACrossing.service.VillagerService;

@RestController
@RequestMapping("/villager")
public class VillagerController {
	private VillagerService service;
	
	private VillagerController(VillagerService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Villager> createVillager(@RequestBody Villager villager){
		return new ResponseEntity<Villager>(this.service.create(villager), HttpStatus.CREATED);
	}
	@GetMapping("/readAll")
	public ResponseEntity<List<Villager>> readAll(){
		return new ResponseEntity<List<Villager>>(this.service.readAll(), HttpStatus.OK);
	}
	@GetMapping("/readById/{id}")
	public ResponseEntity<Villager> readById(@PathVariable long id){
		return new ResponseEntity<Villager>(this.service.readById(id), HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Villager> updateVillager(@PathVariable long id, @RequestBody Villager villager){
		return new ResponseEntity<Villager>(this.service.update(id, villager), HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteVillager(@PathVariable long id){
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
	
}
