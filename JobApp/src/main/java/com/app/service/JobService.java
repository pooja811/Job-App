package com.app.service;

import com.app.entity.JobPost;
import com.app.repo.JobRepo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

	@Autowired
	public JobRepo repo;
	private static final Logger logger = LoggerFactory.getLogger(JobService.class);

	// method to return all JobPosts
	public List<JobPost> getAllJobs() {
		logger.info("inside getAllJobs");
		return repo.findAll();

	}

	// method to add a jobPost
	public void addJob(JobPost jobPost) {
		logger.info("inside addJob");
		repo.save(jobPost);

	}

	// method to get job by id
	public JobPost getJob(int postId) {
		logger.info("inside getJob");
		return repo.findById(postId).orElse(new JobPost());
	}

	// method to update job with job post object
	public void updateJob(JobPost jobPost) {
		logger.info("inside updateJob");
		repo.save(jobPost);

	}

	// method to delete job post by id
	public void deleteJob(int postId) {
		logger.info("inside deleteJob");
		repo.deleteById(postId);

	}

	public void load() {
		// arrayList to store store JobPost objects
		logger.info("inside load");
		List<JobPost> jobs = new ArrayList<>(List.of(
				new JobPost(1, "Software Engineer", "Exciting opportunity for a skilled software engineer.", 3,
						List.of("Java", "Spring", "SQL", "API")),
				new JobPost(2, "Data Scientist", "Join our data science team and work on cutting-edge projects.", 5,
						List.of("Python", "Machine Learning", "TensorFlow", "API")),
				new JobPost(3, "Frontend Developer",
						"Create API amazing user interfaces with our talented frontend team.", 2,
						List.of("JavaScript", "React", "CSS", "API")),
				new JobPost(4, "Network Engineer", "Design and API maintain our robust network infrastructure.", 4,
						List.of("Cisco", "Routing", "Firewalls")),
				new JobPost(5, "UX Designer", "Shape the user experience with your creative design skills.", 3,
						List.of("UI/UX Design", "Adobe XD", "Prototyping"))

		));

		repo.saveAll(jobs);

	}

	public List<JobPost> search(String keyword) {

		return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}

}