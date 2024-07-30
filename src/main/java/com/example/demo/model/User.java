package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"User\"")
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private String email;
	
    public User() {
    }
    
	public User(Integer id, String name, String password, Integer age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.email = email;
	}
	
	
}
