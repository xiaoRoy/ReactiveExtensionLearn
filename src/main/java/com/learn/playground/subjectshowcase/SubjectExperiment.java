package com.learn.playground.subjectshowcase;

import com.learn.model.Book;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class SubjectExperiment {

    private final BookService bookService;

    private final BehaviorSubject<Book> bookBehaviorSubject = BehaviorSubject.create();

    public SubjectExperiment(BookService bookService) {
        this.bookService = bookService;
    }

    public void startLoadBook() {
        bookService
                .loadBookAnother()
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
        boolean hasValue = ((BehaviorSubject<Book>) subjectExperiment.loadBook()).hasValue();
        System.out.println(hasValue);
        subjectExperiment.loadBook()
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Book book) {
                        System.out.println("after");
                        System.out.println(book);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
