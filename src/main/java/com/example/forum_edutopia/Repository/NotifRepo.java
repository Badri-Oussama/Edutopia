package com.example.forum_edutopia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum_edutopia.entities.Notif;

@Repository
public interface NotifRepo extends JpaRepository<Notif, Integer>{

}
