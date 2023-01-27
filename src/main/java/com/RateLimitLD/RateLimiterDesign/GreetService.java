package com.RateLimitLD.RateLimiterDesign;

import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class GreetService {
    //A hashMap to store userId and the RateLimit data
    private HashMap<String,RateLimiter> userBucket=new HashMap<>();
    public String Serve(String userId){
        if(!userBucket.containsKey(userId)){
            userBucket.put(userId,new RateLimiter());
        }
        boolean IsRateLimitExceeded = userBucket.get(userId).IsRateLimitExceeded();
        if(!IsRateLimitExceeded) {
            return "hello";
        } else {
            throw new RuntimeException("Rate limit Exceeded");
        }
    }
}
