package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Data - не писать - любое сравнение или логирование приведет к запросам в БД - сделает hashCode и toString, включая lazy поля
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "message", nullable = false, length = 300)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

}
