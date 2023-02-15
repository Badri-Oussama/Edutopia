package com.example.forum_edutopia.Repository;

import com.example.forum_edutopia.entities.Post;
import com.example.forum_edutopia.entities.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

}
