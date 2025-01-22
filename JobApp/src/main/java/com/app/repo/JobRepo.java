package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JobPost;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

	List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

	//@Query(value = "SELECT * FROM userdata.job_post WHERE post_profile LIKE %:postProfile% OR JSON_CONTAINS(post_tech_stack, :postTechStack)", nativeQuery = true)
	
//	@Query("SELECT jp FROM userdata.job_post jp WHERE jp.postProfile LIKE %:postProfile% OR :postTechStack MEMBER OF jp.postTechStack")
//	List<JobPost> findByPostProfileContainingOrPostTechStack(@Param("postProfile") String postProfile, @Param("postTechStack") String postTechStack);

	
}