package com.learn.reactive.chap3.coreoperator.carrecognition;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CarRecognition {

    private Observable<CarPhoto> cars(){
        return Observable.create(new ObservableOnSubscribe<CarPhoto>() {
            @Override
            public void subscribe(ObservableEmitter<CarPhoto> emitter) throws Exception {

            }
        });
    }

    private Observable<LicensePlate> recognize(CarPhoto carPhoto){
        return Observable.create(new ObservableOnSubscribe<LicensePlate>() {
            @Override
            public void subscribe(ObservableEmitter<LicensePlate> emitter) throws Exception {
                /*
                *  a rather expensive optical character recognition algorithm
                *  that returns the registration number from the license plate of the cars.
                *
                * */
            }
        });
    }

    public static void main(String[] args) {
        CarRecognition carRecognition = new CarRecognition();
        Observable<LicensePlate> observable
                = carRecognition.cars().flatMap(new Function<CarPhoto, ObservableSource<LicensePlate>>() {
            @Override
            public ObservableSource<LicensePlate> apply(CarPhoto carPhoto) throws Exception {
                return carRecognition.recognize(carPhoto);
            }
        });
    }
}
