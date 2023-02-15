package com.example.forum_edutopia.Repository;

import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}