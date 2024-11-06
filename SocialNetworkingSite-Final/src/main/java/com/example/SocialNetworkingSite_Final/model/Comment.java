package com.example.SocialNetworkingSite_Final.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long cid;
    private String cmt;
    @Column(
            name = "`like`"
    )
    private int like;
    private Date cDate;
    @ManyToOne
    @JoinColumn(
            name = "post_pid"
    )
    private Post post;
}
