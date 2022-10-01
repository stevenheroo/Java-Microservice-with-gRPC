package org.github.skep.controller;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.github.skep.service.UserServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServer {
    private static final int HOST_PORT = 50051;
    private static final Logger LOG = Logger.getLogger(UserServer.class.getName());

    private Server server;

    public void startServer() {
        LOG.info("Starting server");
        try {
            server = ServerBuilder.forPort(HOST_PORT)
                    .addService(new UserServiceImpl())
                    .build()
                    .start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOG.log(Level.SEVERE,"Server shutdown");
                UserServer.this.stopServer();
            }));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Server did not start", e);
        }
    }

    public void stopServer() {
        LOG.info("Stopping server");
        try {
            if (server != null) {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}