package com.RateLimitLD.RateLimiterDesign;

import java.util.LinkedList;
import java.util.Queue;


public class RateLimiter {
    private Queue<Long> timeStamps;  //Using a queue to store the timeStamps of a user

    private int requestsAllowedPerMinute = 2;
    private long TimeFrameInSeconds = 60;  //we refresh the queue after this interval of time
    public RateLimiter(){
        timeStamps = new LinkedList<>();
    }

   /**
     *  This method first calls 'RemoveOlderTimeStamps' method to remove any timeStamps which are
     *  greater than our TimeFrameInSeconds. so if a user hit the endpoint before 2 minutes then that
     *  entry is removed from the queue.This leaves only the requests which are in the last 60 seconds.
     *  Then it appends the current timeStamp and checks if the size of the queue is within the number of requests
     *  that is allowed and returns a boolean. */
   public boolean IsRateLimitExceeded(){
        long currentTime = System.currentTimeMillis();
        RemoveOlderTimeStamps(currentTime);
        timeStamps.add(currentTime);
        if(timeStamps.size() > requestsAllowedPerMinute){
            return true;
        }
        return false;
   }
    public void RemoveOlderTimeStamps(Long currentTime){
            if(timeStamps == null) return;
            while(!timeStamps.isEmpty()) {
                long olderTimeStamps = timeStamps.peek();
                long deltaTime = (olderTimeStamps - currentTime) / 1000;
                if (deltaTime >= TimeFrameInSeconds) {
                    timeStamps.poll();
                } else {
                    break;
                }
            }
    }

}
