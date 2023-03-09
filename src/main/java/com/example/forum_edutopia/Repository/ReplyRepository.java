package com.example.forum_edutopia.Repository;


import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Post;
import com.example.forum_edutopia.entities.Reply;
import com.example.forum_edutopia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

  

}



