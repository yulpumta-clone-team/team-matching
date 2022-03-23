package com.projectmatching.app.domain.liking.repository;

import com.projectmatching.app.domain.liking.entity.UserLiking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikingRepository extends JpaRepository<UserLiking,Long> {
    
}
