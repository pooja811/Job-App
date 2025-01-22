package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.entity.JobPost;
import com.app.entity.PostResponse;
import com.app.repo.JobRepo;

@Service
public class JobService {

	@Autowired
	public JobRepo repo;
	private static final Logger logger = LoggerFactory.getLogger(JobService.class);

	// method to return all JobPosts
	public PostResponse getAllJobs1(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
		logger.info("inside getAllJobs");

		Sort sort = null;
		if (orderBy.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else if (orderBy.equalsIgnoreCase("desc")) {
			sort = Sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<JobPost> pagePost = repo.findAll(pageable);
		List<JobPost> content = pagePost.getContent();

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;

	}

	public PostResponse getAllJobs(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
		logger.info("Fetching all jobs with pageNumber: {}, pageSize: {}, sortBy: {}, orderBy: {}", pageNumber,
				pageSize, sortBy, orderBy);

		Sort sort = Sort.by(sortBy);
		sort = "desc".equalsIgnoreCase(orderBy) ? sort.descending() : sort.ascending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<JobPost> pagePost = repo.findAll(pageable);

		// Build and return the response (using builder pattern with lombok)
		return PostResponse.builder()
				.content(pagePost.getContent())
				.pageNumber(pagePost.getNumber())
				.pageSize(pagePost.getSize())
				.totalElements(pagePost.getTotalElements())
				.totalPages(pagePost.getTotalPages())
				.lastPage(pagePost.isLast())
				.build();
	}

//	public List<JobPost> searchBySkills(String keyword) {
//
//		return repo.findByPostProfileContainingOrPostTechStack(keyword, keyword);
//	}

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