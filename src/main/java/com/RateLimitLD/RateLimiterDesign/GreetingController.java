package com.RateLimitLD.RateLimiterDesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {
    @Autowired
    GreetService greetService;

    @RequestMapping("/greet/{userId}")
    public String Greet(@PathVariable String userId){
        return greetService.Serve(userId);
    }
}
