package com.qa.QACrossing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		Villager output = new Villager(1, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		
		Mockito.when(this.repo.save(input)).thenReturn(output);
		assertEquals(output, this.service.create(input));
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
}
