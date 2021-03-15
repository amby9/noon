package com.library.management.system.controllers;

import com.library.management.system.enums.Constants;
import com.library.management.system.jpaEntity.Book;
import com.library.management.system.requests.User;
import com.library.management.system.responses.GenericResponse;
import com.library.management.system.services.LibraryManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Library-Management-System")
@Api(value = "LibraryManagementSystemController", description = "All Operations for Library Management Services")
public class LibraryManagementSystemController {

    private final LibraryManagementService libraryManagementService;

    @Autowired
    public LibraryManagementSystemController(LibraryManagementService libraryManagementService) {
        this.libraryManagementService = libraryManagementService;
    }

    /*
    * API to add an user
    * */

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CREATED, message = Constants.HTTP_STATUS_CREATED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody User user) {
        GenericResponse genericResponse = libraryManagementService.createUser(com.library.management.system.jpaEntity.User
                .builder().isMembershipActive(user.isMembershipActive()).userId(user.getUserId()).userName(user.getUserName()).build());
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    /*
     * API to add a book
     * */

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CREATED, message = Constants.HTTP_STATUS_CREATED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        GenericResponse genericResponse = libraryManagementService.addBook(book);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    /*
     * API to delete a book by bookId
     * */

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable String bookId) {
        GenericResponse genericResponse = libraryManagementService.deleteBook(bookId);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    /*
     * API to borrow a book to a user by bookId & userId
     * */

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/book/borrow", method = RequestMethod.POST)
    public ResponseEntity<?> borrowBook(@RequestParam String userId,
                                          @RequestParam String bookId) {
        GenericResponse genericResponse = libraryManagementService.borrowBook(userId, bookId);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    /*
     * API to return a book by a user by bookId & userId
     * */

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/book/return", method = RequestMethod.POST)
    public ResponseEntity<?> returnBook(@RequestParam String userId,
                                         @RequestParam String bookId) {
        GenericResponse genericResponse = libraryManagementService.returnBook(userId, bookId);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
