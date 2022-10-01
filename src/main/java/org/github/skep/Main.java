package org.github.skep;

import org.github.skep.controller.UserServer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UserServer server = new UserServer();
        server.startServer();
        server.blockUntilShutdown();
    }
}
