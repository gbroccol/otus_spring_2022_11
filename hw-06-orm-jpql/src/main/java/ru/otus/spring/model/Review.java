package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @Column(name = "message", nullable = false, length = 300)
    private String message;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

}
