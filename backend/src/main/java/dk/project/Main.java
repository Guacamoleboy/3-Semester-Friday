package dk.project;

import dk.project.server.Server;
import dk.project.util.KeyGenerator;

public class Main {

    // Attributes
    private static final Server server = new Server();

    // _________________________________________________________________________________________________________________

    public static void main(String[] args) {

        // Server start
        server.start();

        // KeyGenerator.generateNumberKey(6); - [WORKS]
        // System.out.println("UUID Test | String | " + KeyGenerator.generateUUID("string")); - [WORKS]
        // System.out.println("UUID Test | UUID | " + KeyGenerator.generateUUID("uuid")); - [WORKS]
        // System.out.println("UUID Test | Invalid | " + KeyGenerator.generateUUID("invalid")); - [WORKS]
        // System.out.println(KeyGenerator.generate16Key()); - [WORKS]
        // System.out.println(KeyGenerator.generate32Key()); - [WORKS]
        // System.out.println(KeyGenerator.generate64Key()); - [WORKS]
        // System.out.println(KeyGenerator.generate96Key()); - [WORKS]
        //System.out.println(KeyGenerator.generate128Key()); - [WORKS]

    }

}