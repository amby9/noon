package com.library.management.system.services.impl;

import com.library.management.system.dao.LibraryManagementSystemDao;
import com.library.management.system.jpaEntity.Book;
import com.library.management.system.jpaEntity.User;
import com.library.management.system.responses.GenericResponse;
import com.library.management.system.services.LibraryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryManagementServiceImpl implements LibraryManagementService {

    private final LibraryManagementSystemDao libraryManagementSystemDao;

    @Autowired
    public LibraryManagementServiceImpl(LibraryManagementSystemDao libraryManagementSystemDao){
        this.libraryManagementSystemDao = libraryManagementSystemDao;
    }

    @Override
    public GenericResponse createUser(User user){
        return libraryManagementSystemDao.createUser(user);
    }

    @Override
    public GenericResponse addBook(Book book){
        return libraryManagementSystemDao.addBook(book);
    }

    @Override
    public GenericResponse deleteBook(String bookId){
        return libraryManagementSystemDao.deleteBook(bookId);
    }

    @Override
    public GenericResponse borrowBook(String userId, String bookId){
        return libraryManagementSystemDao.borrowBook(userId, bookId);
    }

    @Override
    public GenericResponse returnBook(String userId, String bookId){
        return libraryManagementSystemDao.returnBook(userId, bookId);
    }

}
