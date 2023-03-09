package com.example.forum_edutopia.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class Likee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLike;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;

    private boolean etat ;





}
