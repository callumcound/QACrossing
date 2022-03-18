package com.qa.QACrossing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.QACrossing.entity.Villager;
import com.qa.QACrossing.repo.VillagerRepo;

@SpringBootTest
public class VillagerServiceUnitTest {
	
	@Autowired
	private VillagerService service;
	
	@MockBean
	private VillagerRepo repo;
	
	@Test
	public void createVillagerTest() {
		Villager input = new Villager("Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Villager output = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		
		Mockito.when(this.repo.save(input)).thenReturn(output);
		assertEquals(output, this.service.create(input));
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test 
	public void readByIdTest() {
		Optional<Villager> optionalOutput = Optional.of(new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play"));
		Villager output = new Villager("Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
		assertEquals(output, this.service.readById(Mockito.anyLong()));
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void readAllTest() {
		List<Villager> Villagers = new ArrayList<>();
		Villager input = new Villager("Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Villager output = new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Villagers.add(input);
		Mockito.when(this.repo.findAll()).thenReturn(Villagers);
		assertEquals(Villagers, this.service.readAll());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	
	@Test 
	public void updateTest() {
		Optional<Villager> optionalOuput = Optional.of(new Villager(1L, "Barold", "Lazy", "Cub", 302, "cubby", "Play"));
		Villager output = new Villager("Barold", "Lazy", "Cub", 302, "cubby", "Play");
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOuput);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		assertEquals(output, this.service.update(1L, output)); //?
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.delete(1));
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
}
