
	package com.qa.QACrossing.rest;

	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

	import java.util.ArrayList;
	import java.util.List;

	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
	import org.springframework.http.MediaType;
	import org.springframework.test.context.ActiveProfiles;
	import org.springframework.test.context.jdbc.Sql;
	import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.test.web.servlet.RequestBuilder;
	import org.springframework.test.web.servlet.ResultMatcher;

	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.qa.QACrossing.entity.Villager;

	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@AutoConfigureMockMvc
	@ActiveProfiles("test")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:villager-schema.sql",
			"classpath:villager-data.sql"})

	public class VillagerControllerIntegrationTest {
		
		@Autowired
		private MockMvc mvc;
		@Autowired
		private ObjectMapper mapper;
		
		@Test
		public void testCreate() throws Exception {
			Villager testVillager = new Villager("Barold", "Lazy", "Cub", 302, "cubby", "Play");
			String testVillagerAsJSON = this.mapper.writeValueAsString(testVillager);
	//		RequestBuilder req = post("/villager/create").content(testVillagerAsJSON).contentType(MediaType.APPLICATION_JSON);

			Villager testSavedVillager = new Villager(2, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
			String testSavedVillagerAsJSON = this.mapper.writeValueAsString(testSavedVillager);
		
			ResultMatcher checkStatus = status().isCreated();
			ResultMatcher checkBody = content().json(testSavedVillagerAsJSON);

			this.mvc.perform(post("/villager/create")
					.content(testVillagerAsJSON)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(checkStatus)
					.andExpect(checkBody);
		}


		@Test
		public void testReadById() throws Exception {

			Villager savedVillager = new Villager(1, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
		String savedVillagerAsJSON = this.mapper.writeValueAsString(savedVillager);			
			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkBody = content().json(savedVillagerAsJSON);
			

		 	this.mvc.perform(get("/villager/readById/1"))
		 			.andExpect(checkStatus)
		 			.andExpect(checkBody);
		}
		
		@Test
		public void testReadAll() throws Exception{
			//makeObject
			Villager entry  = new Villager(1, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
			//makeArray
			List<Villager> villagers = new ArrayList<>();
			//addObjectToArray
			villagers.add(entry);
			String villagersOutputAsJSON = this.mapper.writeValueAsString(villagers);
			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkBody = content().json(villagersOutputAsJSON);
			this.mvc.perform(get("/villager/readAll")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(checkStatus)
					.andExpect(checkBody);

	}
		
		@Test
		public void testUpdate() throws Exception {
			Villager testVillager = new Villager(1, "Cephalobot", "Smug", "Octopus", 401, "donk donk", "Play");
			String testVillagerAsJSON = this.mapper.writeValueAsString(testVillager);

			Villager testSavedVillager = new Villager(1, "Cephalobot", "Smug", "Octopus", 401, "donk donk", "Play");
			String testSavedVillagerAsJSON = this.mapper.writeValueAsString(testSavedVillager);
			ResultMatcher checkStatus = status().isAccepted();
			ResultMatcher checkBody = content().json(testSavedVillagerAsJSON);
			this.mvc.perform(put("/villager/update/1")
					.content(testVillagerAsJSON)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(checkStatus)
					.andExpect(checkBody);
		}
}
