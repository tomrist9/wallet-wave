package com.walletwave.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport(){
        return Mono.just("Support team is on the way. Please try again later.");
    }
}
