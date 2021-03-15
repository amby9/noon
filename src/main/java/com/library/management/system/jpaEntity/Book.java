package com.library.management.system.jpaEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    private String bookId;

    private String bookName;

    private String authorName;

    private String publisher;
}
