package com.qa.QACrossing.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class VillagerTest {
	
		@Test
		public void testEquals() {
		 	EqualsVerifier.forClass(Villager.class).usingGetClass().verify();
		}
		
		@Test
		public void getAndSetTest() {
			
			Villager villager = new Villager();
			
			villager.setId(1L);
			villager.setName("Cookie");
			villager.setPersonality("Peppy");
			villager.setSpecies("Dog");
			villager.setBirthday(618);
			villager.setCatchphrase("arfer");
			villager.setHobbies("Fashion");
			
			//use assertNotNull to check that the values are set and are not null
			assertNotNull(villager.getId());
			assertNotNull(villager.getName());
			assertNotNull(villager.getPersonality());
			assertNotNull(villager.getSpecies());
			assertNotNull(villager.getBirthday());
			assertNotNull(villager.getCatchphrase());
			assertNotNull(villager.getHobbies());
			
			//make sure that when we use the getters they get the correct value.
			assertEquals(villager.getId(), 1L);
			assertEquals(villager.getName(), "Cookie");
			assertEquals(villager.getPersonality(), "Peppy");
			assertEquals(villager.getSpecies(), "Dog");
			assertEquals(villager.getBirthday(), 618);
			assertEquals(villager.getCatchphrase(), "arfer");
			assertEquals(villager.getHobbies(), "Fashion");
			
		}
		
		@Test
		public void allArgsConstructor() {
			Villager villager = new Villager(1L, "Cookie", "Peppy", "Dog", 618, "arfer", "Fashion");
			
			assertNotNull(villager.getName());
			assertNotNull(villager.getPersonality());
			assertNotNull(villager.getSpecies());
			assertNotNull(villager.getBirthday());
			assertNotNull(villager.getCatchphrase());
			assertNotNull(villager.getHobbies());
			
			assertEquals(villager.getId(), 1L);
			assertEquals(villager.getName(), "Cookie");
			assertEquals(villager.getPersonality(), "Peppy");
			assertEquals(villager.getSpecies(), "Dog");
			assertEquals(villager.getBirthday(), 618);
			assertEquals(villager.getCatchphrase(), "arfer");
			assertEquals(villager.getHobbies(), "Fashion");
			
		}
	}


