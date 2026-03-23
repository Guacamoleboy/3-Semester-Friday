package dk.project;

import dk.project.server.Server;

public class Main {

    // Attributes
    private static final Server server = new Server();

    // _________________________________________________________________

    public static void main(String[] args) {

        // Server start
        server.start();

    }

}