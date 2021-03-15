package com.library.management.system.jpaRepository;

import com.library.management.system.jpaEntity.Book;
import com.library.management.system.jpaEntity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookManagementRepository extends CrudRepository<Book, String> {

    public Book getBookByBookId(String bookId);

    public Book save(Book book);

    public void deleteByBookId(String bookId);

    //@Query("SELECT t FROM UserNote t WHERE t.userId = ?1")
    //public UserNote getUserNoteByUserId(String userId);

}
