package com.example.forum_edutopia.Repository;


import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Post;
import com.example.forum_edutopia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Comment s " +
            "WHERE s.post = ?1"
    )
    List<Comment> findCommentsByUser(User user);
    List<Comment> findByPost(Post post);
    Comment findCommentByIdCom(Integer id);


}



