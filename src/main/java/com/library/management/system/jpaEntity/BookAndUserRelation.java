package com.library.management.system.jpaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="book_and_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAndUserRelation {

    @Id
    private String bookId;

    private String userId;

}
