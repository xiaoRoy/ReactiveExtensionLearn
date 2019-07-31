package com.learn.playground.subjectshowcase;

import com.learn.model.Book;
import io.reactivex.Observable;

public class BookService {

    public Observable<Book> loadBook() {
        return Observable.just(new Book("123", "Sea and Sky"));
    }
}
