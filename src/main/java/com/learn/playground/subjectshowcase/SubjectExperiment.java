package com.learn.playground.subjectshowcase;

import com.learn.model.Book;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class SubjectExperiment {

    private final BookService bookService;

    private final BehaviorSubject<Book> bookBehaviorSubject = BehaviorSubject.create();

    public SubjectExperiment(BookService bookService) {
        this.bookService = bookService;
        bookBehaviorSubject.doOnNext(book -> {
            System.out.println("doOnNext: bookBehaviorSubject");
        });
    }

    public void startLoadBook() {
        bookService
                .loadBook()
                .doOnSubscribe(disposable -> {
                    System.out.println("onSubscribe");
                })
                .subscribe(bookBehaviorSubject);
    }

    public void startLoadBookAnother() {
        bookService.loadBook()
                .doOnSubscribe(disposable -> {
                    System.out.println("onSubscribeAnother");
                })
                .subscribe(bookBehaviorSubject::onNext);
    }

    public Observable<Book> loadBook() {
        return bookBehaviorSubject;
    }

    public static void main(String[] args) throws InterruptedException {
        SubjectExperiment subjectExperiment = new SubjectExperiment(new BookService());
        subjectExperiment.loadBook().subscribe(book -> {
            System.out.println("before");
            System.out.println(book);
        });
        subjectExperiment.startLoadBook();
        subjectExperiment.loadBook().subscribe(book -> {
            System.out.println("after");
            System.out.println(book);
        });
    }
}
