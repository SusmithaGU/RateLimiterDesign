package com.RateLimitLD.RateLimiterDesign;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetServiceTest {
    GreetService greetService=new GreetService();
    @Test
    public void ServeTest(){
        assertEquals(greetService.Serve("user1"),"hello");
    }
    @Test
    public void ServeTest2(){
        greetService.Serve("user1");
        greetService.Serve("user1");
        Exception exception = assertThrows(RuntimeException.class, () -> greetService.Serve("user1"));
        assertEquals("Rate limit Exceeded", exception.getMessage());
    }

}