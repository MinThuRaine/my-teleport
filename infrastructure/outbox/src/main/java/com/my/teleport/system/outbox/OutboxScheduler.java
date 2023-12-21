package com.my.teleport.system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}

