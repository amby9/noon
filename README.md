Library Management System

LibraryManagementSystem is a spring boot rest application which would provide the CRUD operations for Library related
operations.

LibraryManagementSystemController contains following APIs:
1. Library-Management-System/create/user - POST - Used to create a user
2. Library-Management-System/book - POST - Used to add a book
3. Library-Management-System/book/{bookId} - DELETE - Used to delete a book by bookId
4. Library-Management-System/book/borrow - POST - Used to borrow a book by a user
5. Library-Management-System/book/return - POST - Used to return a book by a user

Used MySql as the Database for this application.

Used Hibernate for ORM.

Swagger Documentation for accessing APIs : http://localhost:8080/swagger-ui.html#/