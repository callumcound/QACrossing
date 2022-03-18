package com.qa.QACrossing.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.QACrossing.entity.Villager;
import com.qa.QACrossing.service.VillagerService;

@SpringBootTest
	public class VillagerControllerUnitTest {
	
	@Autowired
	private VillagerController controller;
	
	@MockBean
	private VillagerService service;
	
	@Test
	public void createVillagerTest() {
		Villager villager = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		
		Mockito.when(service.create(villager)).thenReturn(villager);
		
		
		ResponseEntity<Villager> response = new ResponseEntity<Villager>(villager, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(controller.createVillager(villager));
		Mockito.verify(this.service, times(1)).create(villager);
	}
	
	@Test 
	public void readByIdTest() {
		Villager villager = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Mockito.when(service.readById(1L)).thenReturn(villager);
		
		ResponseEntity<Villager> response = new ResponseEntity<Villager>(villager, HttpStatus.OK);
		assertThat(response).isEqualTo(controller.readById(1L));
		Mockito.verify(this.service, times(1)).readById(1L);
	}
	
	@Test
	public void readAllTest() {
		Villager villager = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		List<Villager> Villagers = new ArrayList<>();
		Villagers.add(villager);
		Mockito.when(service.readAll()).thenReturn(Villagers);
		ResponseEntity<List<Villager>> response = new ResponseEntity<List<Villager>>(Villagers, HttpStatus.OK);
		assertThat(response).isEqualTo(controller.readAll());
		Mockito.verify(this.service, times(1)).readAll();
	}
	
	@Test
	public void updateTest() {
		Villager villager = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Mockito.when(service.update(1L, villager)).thenReturn(villager);
		ResponseEntity<Villager> response = new ResponseEntity<Villager>(villager, HttpStatus.ACCEPTED);
		assertThat(response).isEqualTo(controller.updateVillager(1L, villager));
		Mockito.verify(this.service, times(1)).update(1L, villager);
		}
	
	@Test
	public void deleteVillagerTest() {
		Villager villager = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Mockito.when(service.delete(1L)).thenReturn(true);
		ResponseEntity<Villager> response = new ResponseEntity<Villager>(HttpStatus.NO_CONTENT);
		assertThat(response).isEqualTo(controller.deleteVillager(1L));
		Mockito.verify(this.service, times(1)).delete(1L);
	}
	
	

}
