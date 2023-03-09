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
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPost;
    @Size(max=3000 , min = 3 ,message = "error message")
    private String contenu;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Size(max=50 , min = 3 ,message = "title invalid")
    private String title ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date Created_AT;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private boolean updated ;
    private String Attached_file ;
    private int views ;
    private Integer voteCount;
    @Value("0")
    private Integer nblike ;
    @Value("0")
    private Integer nbdislike ;
    

    /*  private int upvote ; */


   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JoinColumn(name = "firstname", referencedColumnName = "firstname")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> Comments;
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Likee> likes = new ArrayList<>();
    public int getNumLikes() {
        return likes.size();
    }


}
