package com.wyjax.sse;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HelloController {
    private UserChannels channels = new UserChannels();
//
//    @GetMapping("/alarm")
//    public Flux<Stock> stocks(@RequestParam(required = false) String data) {
//        return Flux.interval(Duration.ofSeconds(3))
//                .map(t -> Stock.builder()
//                        .code(data)
//                        .value(randomValue())
//                        .build());
//    }
//
//    private int randomValue() {
//        return ThreadLocalRandom.current().nextInt(1000) + 10000;
//    }

    @GetMapping("/channels/users/{userId}/messages")
    public Flux<ServerSentEvent<String>> connect(
            @PathVariable("userId") Long userId) {
        System.out.println(userId + " : " + "요청");
        return channels.connect(userId) // UserChannel 리턴
                .toFlux() // Flux 리턴
                .map(str -> ServerSentEvent.builder(str).build());
    }

    @PostMapping(path = "/channels/users/{userId}/messages")
    public void send(@PathVariable("userId") Long userId,
                     @RequestBody String message) {
        System.out.println(userId + " : " + "응답");
        channels.send(userId, message);
    }
}