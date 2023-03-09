package com.example.forum_edutopia.Service;

import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Likee;
import com.example.forum_edutopia.entities.Notif;
import com.example.forum_edutopia.entities.Post;
import com.example.forum_edutopia.entities.Reply;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IforumService {
    List<Post> findAll(String votecount);
    void save(Post post);
    void deleteById(int id);
    List<Comment> fetchall() ;

    Optional<Comment> findcommmentById(int id);
    Optional<Post> findpostbyid(int id);

    List<Notif> listenotif(int id);
    void deletePostById(int id);
    void voter(int id);

void unvoter(int id);
    void save(Comment comment, int id, int idp);

    List<Reply> replies(int id);
    void save(Reply rep , int id, int idp);

    List<Post> searchPosts(String searchTerm);

    void incrementPostView(int postId);

     void addLikeToPost(int IdPost, int userId);
    void addDisLikeToPost(int IdPost, int userId);
  //   Map<Integer, Integer> getNumberOfLikesForPosts(List<Integer> postIds);
    int getNumberOfLikesForPost(int postId);
    int getNumberOfdisLikesForPost(int postId);
    String filterComplaint(String complaint);
    final String[] badwords ={"merde","salop","exemple"};
    void setVoteCount(int totalPostCount);

    void addPostAttachedFile(int IdPost, String attachedFile);
    void updatePostAttachedFile(int IdPost, String attachedFile);
     void addComAttachedFile(int idCom, String attachedFile);
}
