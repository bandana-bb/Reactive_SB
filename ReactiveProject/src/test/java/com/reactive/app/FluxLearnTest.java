package com.reactive.app;

import com.reactive.app.services.FluxLearnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@SpringBootTest
public class FluxLearnTest {
    @Autowired
    private FluxLearnService learnService;
    @Autowired
    private FluxLearnService fluxLearnService;
    @Test
    public void simpleFluxLearn() {
//        fluxLearnService.getFlux().subscribe(data->{
//            System.out.println(data);
//            System.out.println("Done with FluxLearnService");
//        });

        fluxLearnService.fruitFlux().subscribe(System.out::println);


    }
    @Test
    public void mapFluxLearn() {

        Flux<String> capFlux=  fluxLearnService.mapExampleFlux();
        StepVerifier.create(capFlux).expectNextCount(3).verifyComplete();

        fluxLearnService.mapExampleFlux().subscribe(System.out::println);
    }
   @Test
    public void  filterFluxLearn() {
        Flux<String> filterFux=fluxLearnService.filterMapExampleFlux();
        StepVerifier.create(filterFux).expectNextCount(2).verifyComplete();
    }

    @Test
    public void flatMapFluxLearn() {
        Flux<String> flatMapFlux=fluxLearnService.flatMapExampleFlux();
        StepVerifier.create(flatMapFlux).expectNextCount(3).verifyComplete();
    }
   @Test
    public void transformFluxLearn() {
        Flux<String> transformFlux=fluxLearnService.transformExampleFlux();
        StepVerifier.create(transformFlux).expectNextCount(3).verifyComplete();
    }

    @Test
    public void ifExampleFluxLearn() {
        Flux<String> stringFlux = fluxLearnService.ifExampleFlux(8);
        StepVerifier.create(stringFlux).expectNextCount(3).verifyComplete();
    }

    @Test
    public void concatFluxLearn() {
        Flux<String> concatFlu1=fluxLearnService.concatExampleFlux();
        StepVerifier.create(concatFlu1).expectNextCount(6).verifyComplete();
    }

    @Test
    public void mergeFluxLearn() {
        Flux<String> mergeFlux=fluxLearnService.mergeExampleFlux();
        StepVerifier.create(mergeFlux).expectNextCount(6).verifyComplete();
    }

    @Test
    public void zipFluxLearn() {
        Flux<Tuple2<String, Integer>> tuple2Flux = fluxLearnService.zipExampleFlux();
        StepVerifier.create(tuple2Flux).expectNextCount(3).verifyComplete();
    }

    @Test
    public void sideFluxLearn() {
        fluxLearnService.sideEffectExampleFlux().log().subscribe(System.out::println);
    }
}
