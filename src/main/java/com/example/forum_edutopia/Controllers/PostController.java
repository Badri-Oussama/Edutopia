package com.example.forum_edutopia.Controllers;

import com.example.forum_edutopia.Service.ForumService;
import com.example.forum_edutopia.Service.IforumService;
import com.example.forum_edutopia.entities.Notif;
import com.example.forum_edutopia.entities.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    IforumService serviceForum ;
    @GetMapping
    public List<Post> getAllPosts() {
        return serviceForum.findAll("voteCount");
    }

    @GetMapping("getpostId/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Optional<Post> post = serviceForum.findpostbyid(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/votetri")
    public List<Post> getPosts() {
       return serviceForum.findAll("votevount");    }

    @PostMapping("/AddPosts")
    public ResponseEntity<Post>  createPost(@RequestBody Post post) {

       serviceForum.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post post) {
        Optional<Post> existingPost = serviceForum.findpostbyid(id);
        if (existingPost.isPresent()) {
            post.setIdPost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePosts(@PathVariable int id) {
        Optional<Post> post = serviceForum.findpostbyid(id);
        if (post.isPresent()) {
            serviceForum.deletePostById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{postId}/{mpm}")
    public  void addPostAttachedFile(@PathVariable("postId") int IdPost, @PathVariable("mpm") String attachedFile) {
        serviceForum.addPostAttachedFile(IdPost, attachedFile);
        ResponseEntity.ok().build();
    }
    @PostMapping("/{idCom}/{mpm}")
    public  void addComAttachedFile(@PathVariable("idCom") int idCom, @PathVariable("mpm") String attachedFile) {
        serviceForum.addComAttachedFile(idCom, attachedFile);
        ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/{attached-file}")
    public ResponseEntity<Post> updatePostAttachedFile
            (@PathVariable ("postId") int IdPost, @RequestParam  String attachedFile, @PathVariable("attached-file") String parameter) {
        serviceForum.updatePostAttachedFile(IdPost, attachedFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{g}")
    public ResponseEntity<List<Post>> searchPosts(@PathVariable("g") String searchTerm) {
        List<Post> matchingPosts = serviceForum.searchPosts(searchTerm);
        return ResponseEntity.ok(matchingPosts);
    }

    @GetMapping("/view/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") int postId) {
        Optional<Post> optionalPost = serviceForum.findpostbyid(postId);
        if (optionalPost.isPresent()) {
            serviceForum.incrementPostView(postId);
            Post post = optionalPost.get();
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/like/{idPost}/{idUser}")
    public ResponseEntity<String> addLikeToPost(@PathVariable Integer idPost, @PathVariable Integer idUser) {
        try {
            serviceForum.addLikeToPost (idPost, idUser);
            return ResponseEntity.ok("Like added to post with Id " + idPost + " by user with Id " + idUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding like: " + e.getMessage());
        }
    }
    @PostMapping("/Dislike/{idPost}/{idUser}")
    public ResponseEntity<String> addDisLikeToPost(@PathVariable Integer idPost, @PathVariable Integer idUser) {
        try {
            serviceForum.addDisLikeToPost(idPost, idUser);
            return ResponseEntity.ok("Like added to post with Id " + idPost + " by user with Id " + idUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding like: " + e.getMessage());
        }
    }
    @GetMapping("/{IdPost}/likes")
    public int getNumberOfLikesForPost(@PathVariable int IdPost) {
        return serviceForum.getNumberOfLikesForPost(IdPost);
    }
    @GetMapping("/{IdPost}/dislikes")
    public int getNumberOfDiLikesForPost(@PathVariable int IdPost) {
        return serviceForum.getNumberOfdisLikesForPost(IdPost);
    }
    @GetMapping("/notif/{IdPost}")
    public List<Notif>notifs(@PathVariable("IdPost") int id) {
        return serviceForum.listenotif(id);
    }
    @PostMapping("/vote/{IdPost}")
    public void vote(@PathVariable("IdPost") int id) {
         serviceForum.voter(id);
    }
    @PostMapping("/unvote/{IdPost}")
    public void unvote(@PathVariable("IdPost") int id) {
         serviceForum.unvoter(id);
    }

}






