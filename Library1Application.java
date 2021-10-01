package com.example.library1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*

You may clone the UI app to interact with this app instead of using Postman.
The UI app offers a simple and better way to interact with the application:
See link below
https://github.com/Nwabueze/LibraryUI

If you prefer to use postman:

1. Registration Endpoint
URL: http://localhost:8080/library/reader/register,
Syntax: http://localhost:8080/library/reader/register
Method: POST,
Body:
{
"firstname": "Samuel"
"lastname": "Adiele",
"email": "sam1@gmail.com",
"password": "123456"
}

2. Login Endpoint
(asuming my email is sam1@gmail.com and password 123456)
Method: POST,
URL: http://localhost:8080/library/reader/login/sam1@gmail.com/123456
Syntax: http://localhost:8080/library/reader/login/{email}/{password}

3. Add book endpoint
(Requires login)
Method: POST,
URL: http://localhost:8080/library/book/add,
Syntax: http://localhost:8080/library/book/add,
Body:
{
"isbn": "123-123-123-123"
"title": "My Awesome Book Title",
"author": "Samuel Adiele",
"year": "2021"
}


4. Get all books in library
(No login required)
Method: GET,
URL: http://localhost:8080/library/book/all,
Syntax: http://localhost:8080/library/book/all

5. Borrow a book rom library
(Requires login and the book ISBN)
Method: PUT
URL: http://localhost:8080/library/reader/add/123-123-123-123
Syntax: http://localhost:8080/library/reader/add/{isbn}

6. View all users who have borrowed library books and have not returned it, including the associated books
(Requires login)
Method: GET
URL: http://localhost:8080/library/reader/borrowed
Syntax: http://localhost:8080/library/reader/borrowed

7. Return a book you borrowed from library
(Requires login)
Method: DELETE
URL: http://localhost:8080/library/reader/borrowed/123-123-123-123
Syntax: http://localhost:8080/library/reader/borrowed/{isbn}
 */

@SpringBootApplication
public class Library1Application {

	public static void main(String[] args) {
		SpringApplication.run(Library1Application.class, args);
	}

}
