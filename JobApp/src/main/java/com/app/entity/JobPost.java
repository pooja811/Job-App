package com.app.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {

	@Id
	private int postId;
	private String postProfile;
	private String postDesc;
	private int reqExperience;

	@Convert(converter = JsonToListConverter.class)
	private List<String> postTechStack;

}
