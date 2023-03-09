package com.example.forum_edutopia.Controllers;

import com.example.forum_edutopia.Service.ForumService;
import com.example.forum_edutopia.Service.IforumService;
import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/comments")
    public class CommentController {

        @Autowired
        IforumService serviceForum ;



        @GetMapping
        public List<Comment> getAllComments() {
            return serviceForum.fetchall();
        }
        @GetMapping("/rep/{id}")
        public List<Reply> getAllrep(@PathVariable("id" )  int id){
            return serviceForum.replies(id);
        }

        @PostMapping("/addreply/{idU}/{idC}")
        public void addrep (@RequestBody Reply rep ,@PathVariable("idU" ) int id ,@PathVariable("idC" ) int idp) {
        	serviceForum.save(rep, id, idp);
        }



            @PostMapping("/{id}/{idp}")
            public void  createComment(@RequestBody Comment comment,@PathVariable("id") int id,@PathVariable("idp") int idp) {
               /* String filterComplaint = filterComplaint(comment.setContenuCom());
                comment.setContenuCom(filterComplaint);*/
                 serviceForum.save(comment,id,idp);
            }

            @PutMapping("/{id}/{idu}/{idp}")
            public void updateComment(@PathVariable int id, @RequestBody Comment comment,@PathVariable("id") int idu,@PathVariable("idp") int idp) {
                Optional<Comment> existingComment = serviceForum.findcommmentById(id);
                if (existingComment.isPresent()) {
                    comment.setIdCom(id); 
                }
            }

            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteComment(@PathVariable int id) {
                Optional<Comment> comment = serviceForum.findcommmentById(id);
                if (comment.isPresent()) {
                    serviceForum.deleteById(id);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            }






        }


