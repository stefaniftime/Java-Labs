package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class GameClient {
public static void main(String[] args) throws IOException{
    String serverAddress = "127.0.0.1";
    String request;
    int PORT = 8100;
    try{
        while(true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter request");
            request = keyboard.nextLine();
            if(request.equals("Exit"))
                break;
            Socket socket = new Socket(serverAddress, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(request);
            if (request.equals("Stop")) {
                System.out.println("Server Stopped, Exiting...");
                break;
            }
            String response = in.readLine();
            System.out.println(response);
        }
    }catch(UnknownHostException e){
        System.err.println("No server listening... ");
    }
}
}
