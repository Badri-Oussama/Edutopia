package com.example.forum_edutopia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private   int userId;

       

        private   String firstname;

        private   String lastname;

        private   String email;

        @Enumerated(EnumType.STRING)
        private  Role role;

        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private Set<Post> Posts;
        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private List<Notif> Notifs;

        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private Set<Comment> Comments;
       /* @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private  Set<Ratings> ratings;

        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private Set<Report> reports;

        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private Set<Subscription> Subscriptions;

    }*/

}
