package com.wyjax.sse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;

public class UserChannel {
    private Flux<String> flux;
    private FluxSink<String> sink;
    private UnicastProcessor<String> processor;

    public UserChannel() {
        processor = UnicastProcessor.create();
        this.sink = processor.sink();
        this.flux = processor.share();
    }

    public void send(String message) {
        sink.next(message);
    }

    public Flux<String> toFlux() {
        return flux;
    }
}
