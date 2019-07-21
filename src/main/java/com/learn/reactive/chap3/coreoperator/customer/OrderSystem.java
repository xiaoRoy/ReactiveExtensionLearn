package com.learn.reactive.chap3.coreoperator.customer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.ArrayList;

public class OrderSystem {

    private Observable<Customer> generateCustomerObservable(Customer customer) {
        return Observable.create(new ObservableOnSubscribe<Customer>() {
            @Override
            public void subscribe(ObservableEmitter<Customer> emitter) throws Exception {
                emitter.onNext(customer);
                emitter.onComplete();
            }
        });
    }

    private Observable<Order> mapFlatMapOneToMany(){
        OrderSystem orderSystem = new OrderSystem();
        Customer customerToHandle = new Customer("11", new ArrayList<>());
        Observable<Order> orderObservable = generateCustomerObservable(customerToHandle)
                .map(customer -> customer.getOrders())
                .flatMap(orders -> Observable.fromIterable(orders));
        return orderObservable;
    }

    private Observable<Order> flatMapOneToMany(){
        OrderSystem orderSystem = new OrderSystem();
        Customer customerToHandle = new Customer("11", new ArrayList<>());
        Observable<Order> orderObservable = generateCustomerObservable(customerToHandle)
                .flatMap(customer -> Observable.fromIterable(customer.getOrders()));
        return orderObservable;
    }

    private Observable<Order> flatMapIterable(){
        OrderSystem orderSystem = new OrderSystem();
        Customer customerToHandle = new Customer("11", new ArrayList<>());
        Observable<Order> orderObservable = generateCustomerObservable(customerToHandle)
                .flatMapIterable(customer -> customer.getOrders());
        return orderObservable;
    }
}
