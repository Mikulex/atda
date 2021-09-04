package com.mikulex.atda;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Task, Long>{
    
}
