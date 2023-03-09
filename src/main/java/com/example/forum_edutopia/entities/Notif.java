package com.example.forum_edutopia.entities;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Notif")
public class Notif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String contenu;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    
    private User user;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User comment ;

   


}
