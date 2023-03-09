package com.example.forum_edutopia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCom;
    @Size(max=3000 , min = 3 ,message = "error message")
    private String contenu;
    private Date Created_AT;
    private String Attached_file;
   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JoinColumn(name = "firstname", referencedColumnName = "firstname")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Comment comment;
    private Integer voteCount;

}


