package com.library.management.system.dao;

import com.library.management.system.jpaEntity.Book;
import com.library.management.system.jpaEntity.User;
import com.library.management.system.responses.GenericResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryManagementSystemDao {

    public GenericResponse createUser(User user);

    public GenericResponse addBook(Book book);

    public GenericResponse deleteBook(String bookId);

    public GenericResponse borrowBook(String userId, String bookId);

    public GenericResponse returnBook(String userId, String bookId);

}
