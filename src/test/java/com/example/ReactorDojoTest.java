package com.example;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ReactorDojoTest {
    ReactorDojo reactorDojo = new ReactorDojo();

    @Test
    public void identity() throws Exception {
        Flux<String> input = Flux.just("hoge", "foo", "bar");
        Flux<String> output = reactorDojo.identity(input);

        StepVerifier
                .create(output)
                .expectNext("hoge", "foo", "bar")
                .verifyComplete();
    }

    @Test
    public void toUppercase() throws Exception {
        Flux<String> input = Flux.just("hoge", "foo", "bar");
        Flux<String> output = reactorDojo.toUppercase(input);

        StepVerifier
                .create(output)
                .expectNext("HOGE", "FOO", "BAR")
                .verifyComplete();
    }

    @Test
    public void toOddNumbers() throws Exception {
        Flux<Integer> input = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Flux<Integer> output = reactorDojo.oddNumbers(input);

        StepVerifier
                .create(output)
                .expectNext(1, 3, 5, 7, 9)
                .verifyComplete();
    }

    @Test
    public void commaSplit() throws Exception {
        Flux<String> input = Flux.just("aaa,bbb,", "ccc,ddddd,eee,", "ffff,g,", "hh,", "iiiii,jjj,");
        Flux<String> output = reactorDojo.commaSplit(input);

        StepVerifier
                .create(output)
                .expectNext("aaa", "bbb", "ccc", "ddddd", "eee", "ffff", "g", "hh", "iiiii", "jjj")
                .verifyComplete();
    }

    @Test
    public void commaSplitAdvanced() throws Exception {
        Flux<String> input = Flux.just("aaa,bb", "b,ccc,ddddd,e", "ee,ff", "ff,g,hh,i", "i", "iii,jjj,");
        Flux<String> output = reactorDojo.commaSplit(input);

        StepVerifier
                .create(output)
                .expectNext("aaa", "bbb", "ccc", "ddddd", "eee", "ffff", "g", "hh", "iiiii", "jjj")
                .verifyComplete();
    }

    @Test
    public void commaSplitAdvancedInfinite() throws Exception {
        Flux<String> input = Flux.just("aaa,bb", "b,ccc,ddddd,e", "ee,ff", "ff,g,hh,i", "i", "iii,jjj,").repeat();
        Flux<String> output = reactorDojo.commaSplit(input);

        StepVerifier
                .create(output)
                .expectNext("aaa", "bbb", "ccc", "ddddd", "eee", "ffff", "g", "hh", "iiiii", "jjj")
                .expectNext("aaa", "bbb", "ccc", "ddddd", "eee", "ffff", "g", "hh", "iiiii", "jjj")
                .expectNext("aaa", "bbb", "ccc", "ddddd", "eee", "ffff", "g", "hh", "iiiii", "jjj")
                .verifyComplete();
    }
}
