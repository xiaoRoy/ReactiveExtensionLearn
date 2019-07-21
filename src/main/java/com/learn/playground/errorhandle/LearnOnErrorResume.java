package com.learn.playground.errorhandle;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class LearnOnErrorResume {

	public static void main(String[] args) {
		new LearnOnErrorResume().loadBookWithFlatMap();
	}

	private Observable<Book> loadBook(){
		return Observable.create(new ObservableOnSubscribe<Book>() {
			@Override
			public void subscribe(ObservableEmitter<Book> emitter) throws Exception {
				emitter.onNext(null);
			}
		});
	}

	private void loadBookNormally(){
		loadBook()
				.doOnNext(book -> {
					System.out.println("loadBookNormally.doOnNext:" + book);})
				.doOnError(throwable -> {
					System.out.println("loadBookNormally.doOnError:" + throwable.getMessage());
				})
				.map(new Function<Book, String>() {
					@Override
					public String apply(Book book) throws Exception {
						return "not null";
					}
				})
				.subscribe(new LearnObserver<String>());
	}

	private void loadBookWithFlatMap(){
		loadBook()
				.doOnNext(book -> {
					System.out.println("loadBookNormally.doOnNext:" + book);})
				.flatMap(new Function<Book, ObservableSource<?>>() {
					@Override
					public ObservableSource<?> apply(Book book) throws Exception {
						return Observable.just("Book");
					}
				})
				.subscribe(new LearnObserver<>());
	}

	private static class LearnObserver<T> implements Observer<T>{
		@Override
		public void onSubscribe(Disposable disposable) {

		}

		@Override
		public void onNext(T t) {
			System.out.println("LearnObserver.onNext");
		}

		@Override
		public void onError(Throwable throwable) {
			System.out.println("LearnObserver.onError:" + throwable.getMessage());
		}

		@Override
		public void onComplete() {

		}
	}
}
