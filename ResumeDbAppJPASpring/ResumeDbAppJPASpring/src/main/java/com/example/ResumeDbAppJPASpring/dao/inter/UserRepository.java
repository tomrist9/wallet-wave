package com.example.ResumeDbAppJPASpring.dao.inter;

import com.example.ResumeDbAppJPASpring.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByName
}
