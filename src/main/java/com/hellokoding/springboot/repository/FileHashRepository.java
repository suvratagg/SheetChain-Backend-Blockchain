package com.hellokoding.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hellokoding.springboot.entity.FileHashDetails;
import com.hellokoding.springboot.entity.FileHashPK;

/**
 * 
 * @author akashmalik
 *
 */
public interface FileHashRepository extends JpaRepository<FileHashDetails, FileHashPK>{

	@Query("FROM filehashdetails f where f.fileHashPK.username = ?1 and f.fileHashPK.fileName = ?2")
	FileHashDetails findByUserNameAndFileHash(String username, String fileName);
	
}
