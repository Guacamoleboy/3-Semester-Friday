package dk.project;

import dk.project.server.Server;

public class Main {

    // Attributes

    // _________________________________________________________________

    public static void main(String[] args) {

        // Server start @7070
        Server server = new Server();
        server.start(7070);

    }

}