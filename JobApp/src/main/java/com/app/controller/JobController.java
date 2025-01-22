package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.JobPost;
import com.app.entity.PostResponse;
import com.app.service.JobService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

	@Autowired
	private JobService service;

	@GetMapping("jobPosts")
	public PostResponse getAllJobs(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "acs", required = false) String orderBy) {
		
		PostResponse allJobs = service.getAllJobs(pageNumber, pageSize, sortBy, orderBy);
		return allJobs;

	}

//	@GetMapping("jobPosts/filter/{keyword}")
//	public List<JobPost> searchBySkill(@PathVariable("keyword") String keyword) {
//		return service.searchBySkills(keyword);
//
//	}

	@GetMapping("/jobPost/{postId}")
	public JobPost getJob(@PathVariable int postId) {
		return service.getJob(postId);
	}

	@GetMapping("jobPosts/keyword/{keyword}")
	public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword) {
		return service.search(keyword);

	}

	@PostMapping("jobPost")
	public JobPost addJob(@RequestBody JobPost jobPost) {
		service.addJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}

	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}

	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable int postId) {
		service.deleteJob(postId);
		return "Deleted";
	}

	@GetMapping("load")
	public String loadData() {
		service.load();
		return "success";
	}

}
