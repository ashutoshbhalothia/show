package com.backend.show.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureHandler.class);

    protected <T> T handleCompletableResponse(CompletableFuture<T> completableFuture, int timeout, T timeoutRes) {
        try {
            return completableFuture.get(timeout, TimeUnit.SECONDS);
        } catch (TimeoutException timeoutException) {
            LOGGER.error("timeout exception when reading response...", timeoutException);
            return timeoutRes;
        } catch (InterruptedException ie) {
            LOGGER.error("InterruptedException: ", ie);
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            LOGGER.error("ExecutionException: ", ee);
        }
        return timeoutRes;
    }

    protected <T> T handleCompletableResponse(CompletableFuture<T> completableFuture, T timeoutRes) {
        return handleCompletableResponse(completableFuture, 5, timeoutRes);
    }
}
