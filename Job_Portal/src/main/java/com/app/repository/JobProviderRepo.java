package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.JobProvider;

@Repository
public interface JobProviderRepo extends JpaRepository<JobProvider, Long> {

	Optional<JobProvider> findByUserNameAndPassword(String userName, String password);

	Optional<JobProvider> findByUserName(String userName);
}
