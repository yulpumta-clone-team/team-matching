package com.projectmatching.app.domain.comment.repository;

import com.projectmatching.app.domain.comment.entity.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentRepository  extends JpaRepository< UserComment,Long> {


}
