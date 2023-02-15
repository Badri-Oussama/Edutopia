package com.example.forum_edutopia.Service;

import com.example.forum_edutopia.Repository.CommentRepository;
import com.example.forum_edutopia.Repository.PostRepository;
import com.example.forum_edutopia.Repository.UpvoteRepository;
import com.example.forum_edutopia.Repository.UserRepository;
import com.example.forum_edutopia.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
    @Slf4j
    public class ForumService {

        @Autowired
        CommentRepository commentRepository;
        @Autowired
        PostRepository postRepository;
        @Autowired
        UpvoteRepository upvoteRepository;
        @Autowired
        UserRepository userRepository;


       /* public Post addPost(Post post) {
            post.setCreatedAt(Instant.now());
            return postRepository.save(post);
        }
*/

    }
