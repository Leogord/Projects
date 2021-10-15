package com.qa.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.main.data.Toon;

@Repository

public interface ToonRepo extends JpaRepository<Toon, Integer>{


}
