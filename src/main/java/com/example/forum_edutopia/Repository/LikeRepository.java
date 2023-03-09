package com.example.forum_edutopia.Repository;
import com.example.forum_edutopia.entities.Likee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository  extends JpaRepository<Likee,Integer> {


    //List<Likee> findByPostId(int IdPost);

	@Query("SELECT COUNT(l) FROM Likee l WHERE l.post.IdPost= :IdPost AND l.etat=true")
    int countLikesForPost(@Param("IdPost") int IdPost);
	@Query("SELECT COUNT(l) FROM Likee l WHERE l.post.IdPost= :IdPost AND l.etat=false")
    int countLikesForPostdis(@Param("IdPost") int IdPost);
}






