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

@Validated

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Book")
public class Book {

    @Id
    private String id;

    @Indexed(unique = true)
    private String isbn;

    private String title;

    @NotBlank
    @NonNull
    private String author;

    @NotBlank
    @NonNull
    private String year;

    public String getISBN(){
        if(this.isbn == null)
            return "";
        return this.isbn;
    }

    public String getTitle(){
        if(this.title == null)
            return "";
        return this.title;
    }

    public String getAuthor(){
        if(this.author == null)
            return "";
        return this.author;
    }

    public String getYear(){
        if(this.year == null)
            return "";
        return this.year;
    }


    public void setId(String bookId){
        this.id = bookId;
    }
}
