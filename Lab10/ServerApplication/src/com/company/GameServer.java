package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private boolean running = true;
    public static final int PORT = 8100;
    private ServerSocket serverSocket;

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(PORT);
        while (isRunning()) {
            try {
                System.out.println("Waiting for a client");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            } catch (IOException E) {
                    System.out.println("Asta e acuma");
                    return;
            }
        }

    }
    private synchronized boolean isRunning() {
        return this.running;
    }

    public synchronized void stop() {
        this.running = false;
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            System.out.println("Received Stop request, Stopping...");
            System.exit(0);
        }
    }

}
