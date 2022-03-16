	package com.qa.QACrossing.rest;

	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
			RequestBuilder req = post("/villager/create").content(testVillagerAsJSON).contentType(MediaType.APPLICATION_JSON);

			Villager testSavedVillager = new Villager(1, "Barold", "Lazy", "Cub", 302, "cubby", "Play");
			String testSavedVillagerAsJSON = this.mapper.writeValueAsString(testSavedVillager);
		
			ResultMatcher checkStatus = status().isCreated();
			ResultMatcher checkBody = content().json(testSavedVillagerAsJSON);

			this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}

//		@Test
//		public void testCreate2() throws Exception {
//			// URL body method headers
//			Villager testVillager = new Villager(20, "Daffy", "Toon World", "Male");
//			String testVillagerAsJSON = this.mapper.writeValueAsString(testVillager);
//			RequestBuilder req = post("/villager/create").content(testVillagerAsJSON).contentType(MediaType.APPLICATION_JSON);
	//
//			Villager testSavedVillager = new Villager(2, 20, "Daffy", "Toon World", "Male");
//			String testSavedVillagerAsJSON = this.mapper.writeValueAsString(testSavedVillager);
//			// this will check the status code of my response
//			ResultMatcher checkStatus = status().isCreated();
//			// this will check the body of the response
//			ResultMatcher checkBody = content().json(testSavedVillagerAsJSON);
	//
//			// run the request and check both matchers
//			this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
//		}

		@Test
		public void testReadById() throws Exception {
			RequestBuilder req = get("/villager/readById/1");

			ResultMatcher checkStatus = status().isOk();

			Villager savedVillager = new Villager();
			String savedVillagerAsJSON = this.mapper.writeValueAsString(savedVillager);

			ResultMatcher checkBody = content().json(savedVillagerAsJSON);

			this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
		
		@Test
		public void testReadAll() throws Exception{
			//makeObject
			Villager entry  = new Villager();
			//makeArray
			List<Villager> villagers = new ArrayList<>();
			//addObjectToArray
			villagers.add(entry);
			String villagersOutputAsJson = this.mapper.writeValueAsString(villagers);
			
			this.mvc.perform(get("/villager/readAll")
					//reqBuilder
					.contentType(MediaType.APPLICATION_JSON))
					//result matcher
					.andExpect(status().isOk())
					//resultMatcher
					.andExpect(content().json(villagersOutputAsJson));

	}
}
