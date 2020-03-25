package com.wyjax.sse;

import java.util.concurrent.ConcurrentHashMap;

public class UserChannels {
    private ConcurrentHashMap<Long, UserChannel> map = new ConcurrentHashMap<>();

    public UserChannel connect(Long userId) {
        return map.computeIfAbsent(userId, key -> new UserChannel());
    }

    public void send(Long userId, String message) {
        UserChannel userChannel = map.get(userId);
        if (userChannel != null) {
            userChannel.send(message);
        }
    }
}