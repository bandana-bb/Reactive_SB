package com.reactive.app;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;

@SpringBootTest
class ReactiveProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void workingWithMono() throws InterruptedException {
		System.out.println("Working with Mono");

		/*
		* Mono using error(),just(),then()


		//Mono is publisher that have 0 or 1 items
		//log() helps to provide the log trace of the mono publisher

		//create mono
		Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));
		Mono<String> m1 = Mono.just("Hello Mono Publisher").log().then(errorMono);



		//consume the mono by subscribing
		m1.subscribe(data->{
			System.out.println("data = "+data);
		});

		errorMono.subscribe(System.out::println);

        */


		System.out.println("Use of zip() ");
		Mono<String> m1 = Mono.just("Learing reactive SB");
		Mono<String> m2 = Mono.just("Along with java8");
		Mono<Integer> m3 = Mono.just(12345);
		Mono<Tuple3<String, String, Integer>> combibeMono= Mono.zip(m1, m2,m3);
        combibeMono.subscribe(System.out::println);
		combibeMono.subscribe(data->{
				System.out.println(data.getT1());
				System.out.println(data.getT2());
				System.out.println(data.getT3());

			Mono<Tuple2<String, String>> tuple2Mono = m1.zipWith(m2);
			tuple2Mono.subscribe(data_1->{
				System.out.println(data_1.getT1());
				System.out.println(data_1.getT2());
			});
		});

		System.out.println("Use of Map");

		Mono<String> map = m1.map(item -> item.toUpperCase());
		Disposable subscribe = map.subscribe(System.out::println);


		System.out.println("Use of FlatMap ****************************");
		Mono<String[]> monoFlatMap = m1.flatMap(it -> Mono.just(it.split(" ")));
		monoFlatMap.subscribe(data-> {
			for(String s:data){
			System.out.println(s);
		}});

		System.out.println("Use of FlatMapMany to convert into flux ****************************");

		Flux<String[]> fluxi = m1.flatMapMany(i -> Mono.just(i.split(" "))).log();
		fluxi.subscribe(data->{
			for(String s:data){
				System.out.println(s);
			}
		});

		System.out.println("*****************Use of concatWith ************");
		System.out.println(Thread.currentThread().getName());
		Flux<String> fluxdata = m1.concatWith(m2).log().delayElements(Duration.ofMillis(2000));
		fluxdata.subscribe((data)-> {
			System.out.println(Thread.currentThread().getName());
			System.out.println(data);
		});
		Thread.sleep(5000);
		System.out.println("Terminating main thread");


	}

}
