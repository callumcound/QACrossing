package com.qa.QACrossing.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Villager {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	public String name;
	
	@Column(nullable = false)
	public String personality;
	
	@Column(nullable = false)
	public String species;
	
	@Column(nullable = false)
	public int birthday;
	
	@Column(unique = true, nullable = false)
	public String catchphrase;
	
	@Column
	public String hobbies;
	
	public Villager() {}
	
	public Villager(String name, String personality, String species, int birthday, String catchphrase, String hobbies) {
		this.name = name;
		this.personality = personality;
		this.species = species;
		this.birthday = birthday;
		this.catchphrase = catchphrase;
		this.hobbies = hobbies;
	}
	
	public Villager(long id, String name, String personality, String species, int birthday, String catchphrase, String hobbies) {
		this.id = id;
		this.name = name;
		this.personality = personality;
		this.species = species;
		this.birthday = birthday;
		this.catchphrase = catchphrase;
		this.hobbies = hobbies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	public String getCatchphrase() {
		return catchphrase;
	}

	public void setCatchphrase(String catchphrase) {
		this.catchphrase = catchphrase;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Villager [name=" + name + ", personality=" + personality + ", species=" + species + ", birthday="
				+ birthday + ", catchphrase=" + catchphrase + ", hobbies=" + hobbies + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, catchphrase, hobbies, name, personality, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Villager other = (Villager) obj;
		return birthday == other.birthday && Objects.equals(catchphrase, other.catchphrase)
				&& Objects.equals(hobbies, other.hobbies) && Objects.equals(name, other.name)
				&& Objects.equals(personality, other.personality) && Objects.equals(species, other.species);
	}
	

}
