package com.example.library1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Validated

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Reader")
public class Reader {

    @Id
    private String id;

    private  String firstname;

    private String lastname;

    @Indexed(unique = true)
    private String email;

    private String password;

    private boolean admin;

    private int count;

    private List<Book> books;

    public String getFirstname() {
        if(this.firstname == null)
            return "";
        return this.firstname;
    }

    public String getLastname() {
        if(this.lastname == null)
            return "";
        return this.lastname;
    }

    public String getEmail() {
        if(this.email == null)
            return "";
        return this.email;
    }

    public String getPassword(){
        if(this.password == null)
            return "";
        return this.password;
    }

    public void setAdmin(){
        this.admin = true;
    }

    public void setCount(){
        this.count = 0;
    }

    public void setPassword(String passw){
        this.password = passw;
    }

    public void setId(String readerId){
        this.id = readerId;
    }

    public void setBooks() {
        this.books = new ArrayList<>();
    }

}
