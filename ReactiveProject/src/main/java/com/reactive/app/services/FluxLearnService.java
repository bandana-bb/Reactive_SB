package com.reactive.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class FluxLearnService {

    //all opeartors example goes here
    //creating flux


    public Flux<String> getFlux(){
       return Flux.just("Bandana", "Bharti", "Radhe").log();
    }


    public Flux<String> fruitFlux(){
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
        return Flux.fromIterable(fruits).log();
    }


    //get empty flux
    public Flux<Void> getBlank(){
        return Flux.empty();
    }


    //map with flux

    public Flux<String> mapExampleFlux(){
        Flux<String> mapp = getFlux().map(String::toUpperCase);
        return mapp.log();
    }

    //filter

    public Flux<String> filterMapExampleFlux(){
        Flux<String> filterFlux= getFlux().filter(name->name.length()>5);
        return filterFlux.log();
    }

    //flatMap

    public Flux<String> flatMapExampleFlux(){
        //return  getFlux().flatMap(name->Flux.just(name.split(""))).log();
        return getFlux().flatMap(name->Flux.just("Test flux")).log().delayElements(Duration.ofMillis(2000));
    }

    //transform example
    public Flux<String> transformExampleFlux(){
        Function<Flux<String>,Flux<String>> funInter=(name)->name.map(String::toUpperCase);

        return getFlux().transform(funInter).log();
    }

    //defaultifempty()
    //switchIfEmpty

    public Flux<String> ifExampleFlux(int leng){
        return getFlux().filter(name->name.length()>leng)
                //.defaultIfEmpty("Learn reactive programming")
                .switchIfEmpty(fruitFlux())
        .log();

    }

    //concat(static) /concatWith(instance)
    public Flux<String> concatExampleFlux(){
        //return  Flux.concat(getFlux(),fruitFlux()).log();
        return  getFlux().concatWith(fruitFlux()).log();
    }

    //Synchronous method merge and mergeWith

    public Flux<String> mergeExampleFlux(){
        return  Flux.merge(getFlux(), fruitFlux()).delayElements(Duration.ofMillis(3000)).log();
    }

    //zip and zipWith

    public Flux<Tuple2<String,Integer>> zipExampleFlux(){
        return Flux.zip(getFlux(),Flux.just(123,132,23)).log();
    }

    public Flux<String> sideEffectExampleFlux(){
        return getFlux().doOnNext(name->{
            System.out.println(name + " on next");
        }).doOnSubscribe(data->{
            System.out.println(data + " on subscribe");
        }).doOnEach(data->{
            System.out.println(data + " onEach");
        }).doOnComplete(()->{
            System.out.println("completed");
        });
    }
}

