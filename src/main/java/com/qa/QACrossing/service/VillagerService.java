package com.qa.QACrossing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.QACrossing.entity.Villager;
import com.qa.QACrossing.repo.VillagerRepo;

@Service
public class VillagerService implements ServiceMethods<Villager>{
	
	public VillagerRepo repo;
	
	public VillagerService(VillagerRepo repo) {
		this.repo = repo;
	}
	

	@Override
	public Villager create(Villager villager) {
		return this.repo.save(villager);
	}

	@Override
	public List<Villager> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Villager readById(long id) {
		Optional<Villager> getVillager = this.repo.findById(id);
		if(getVillager.isPresent()) {
			return getVillager.get();
		}
		return null;
	}

	@Override
	public Villager update(long id, Villager villager) {
		Optional<Villager> existingVillager = this.repo.findById(id);
		if(existingVillager.isPresent()) {
			Villager exists = existingVillager.get();
			exists.setHobbies(villager.getHobbies());
			exists.setCatchphrase(villager.getCatchphrase());
			exists.setBirthday(villager.getBirthday());
			exists.setName(villager.getName());
			exists.setPersonality(villager.getPersonality());
			exists.setSpecies(villager.getSpecies());
			return this.repo.saveAndFlush(exists);
			}
		return null;
	}	

	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
