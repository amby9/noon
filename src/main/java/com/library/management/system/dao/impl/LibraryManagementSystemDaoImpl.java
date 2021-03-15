package com.library.management.system.dao.impl;

import com.library.management.system.dao.LibraryManagementSystemDao;
import com.library.management.system.enums.ErrorCodes;
import com.library.management.system.exceptions.LibraryManagementPlatformException;
import com.library.management.system.jpaEntity.Book;
import com.library.management.system.jpaEntity.BookAndUserRelation;
import com.library.management.system.jpaEntity.User;
import com.library.management.system.jpaRepository.BookAndUserManagementRepository;
import com.library.management.system.jpaRepository.BookManagementRepository;
import com.library.management.system.jpaRepository.UserManagementRepository;
import com.library.management.system.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class LibraryManagementSystemDaoImpl implements LibraryManagementSystemDao {

    @Autowired
    private BookManagementRepository bookManagementRepository;

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private BookAndUserManagementRepository bookAndUserManagementRepository;

    @Override
    public GenericResponse createUser(User user){

        /*
        * Validations for request
        * */

        if(user==null || StringUtils.isBlank(user.getUserId()) || StringUtils.isBlank(user.getUserName())){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BAD_REQUEST);
        }

        if(userManagementRepository.getUserByUserId(user.getUserId())!=null){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.USER_WITH_ID_ALREADY_EXISTS);
        }

        /*
         * Validations for request
         * */

        user.setCreatedDate(new Date());
        userManagementRepository.save(user);

        return GenericResponse.builder().status("user created with id : "+user.getUserId()).build();
    }

    @Override
    public GenericResponse addBook(Book book) {

        /*
         * Validations for request
         * */

        if(book==null || StringUtils.isBlank(book.getBookId()) || StringUtils.isBlank(book.getAuthorName())
        || StringUtils.isBlank(book.getBookName()) || StringUtils.isBlank(book.getPublisher())){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BAD_REQUEST);
        }

        if(bookManagementRepository.getBookByBookId(book.getBookId())!=null){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BOOK_WITH_ID_ALREADY_EXISTS);
        }

        /*
         * Validations for request
         * */

        bookManagementRepository.save(book);

        return GenericResponse.builder().status("book added to inventory").build();
    }

    @Override
    public GenericResponse deleteBook(String bookId){

        /*
         * Validations for request
         * */

        if(StringUtils.isBlank(bookId)) throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BAD_REQUEST);

        if(bookManagementRepository.getBookByBookId(bookId)==null){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.INVALID_BOOK_ID_OR_USER_ID);
        }

        if(bookAndUserManagementRepository.getBookAndUserRelationByBookId(bookId)!=null){
            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.CAN_NOT_REMOVE_BOOK_ALREADY_BOOKED_BY_USER);
        }

        /*
         * Validations for request
         * */

        bookManagementRepository.deleteById(bookId);

        return GenericResponse.builder().status("book deleted from inventory with id : "+bookId).build();
    }

    @Override
    public GenericResponse borrowBook(String userId, String bookId){

        if(StringUtils.isBlank(bookId) || StringUtils.isBlank(userId)) throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BAD_REQUEST);

        Book book = bookManagementRepository.getBookByBookId(bookId);
        User user = userManagementRepository.getUserByUserId(userId);

        if(book!=null && user!=null) {

            BookAndUserRelation bookAndUserRelation = bookAndUserManagementRepository.getBookAndUserRelationByBookId(bookId);

            if (bookAndUserRelation == null) {

                List<BookAndUserRelation> bookAndUserRelations = bookAndUserManagementRepository.getBookAndUserRelationByUserId(userId);

                /*
                * Validation for user requesting more than 2 books.
                * */
                if(bookAndUserRelations!=null && bookAndUserRelations.size()==2){

                    throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.MAX_BOOKS_BOOKED_BY_USER);

                }

                bookAndUserManagementRepository.save(BookAndUserRelation.builder().bookId(bookId).userId(userId).build());

                return GenericResponse.builder().status("book with id : " + bookId + " borrowed by user with id : " + userId).build();

            }

            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BOOK_ALREADY_BOOKED_BY_ANOTHER_USER);

        }

        throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.INVALID_BOOK_ID_OR_USER_ID);

    }

    @Override
    public GenericResponse returnBook(String userId, String bookId){

        if(StringUtils.isBlank(bookId) || StringUtils.isBlank(userId)) throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.BAD_REQUEST);

        Book book = bookManagementRepository.getBookByBookId(bookId);
        User user = userManagementRepository.getUserByUserId(userId);

        if(book!=null && user!=null) {

            BookAndUserRelation bookAndUserRelation = bookAndUserManagementRepository.getBookAndUserRelationByBookId(bookId);

            if (bookAndUserRelation != null && StringUtils.equalsIgnoreCase(bookAndUserRelation.getUserId(), userId)) {

                bookAndUserManagementRepository.deleteById(bookId);

                return GenericResponse.builder().status("book with id : " + bookId + " returned by user with id : " + userId).build();

            }

            throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.INVALID_BOOK_ID_OR_USER_ID);

        }

        throw new LibraryManagementPlatformException(HttpStatus.BAD_REQUEST.value(), ErrorCodes.INVALID_BOOK_ID_OR_USER_ID);

    }

}
