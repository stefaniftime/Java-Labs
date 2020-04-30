package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ClientThread extends Thread {
     private Socket socket = null;
     private GameServer server;
     String raspuns;
     public ClientThread(Socket socket,GameServer server){this.socket=socket;this.server=server;}
     public void run(){
         try{
             BufferedReader in = new BufferedReader(
             new InputStreamReader(socket.getInputStream()));
             String request = in.readLine();
             PrintWriter out = new PrintWriter(socket.getOutputStream());
             if(request.equals("Stop")){
                 raspuns = "Server Stopped";
                 out.println(raspuns);
                 server.stop();
             }
             else
             {
                 raspuns="Server received the request";
             }
             out.println(raspuns);
             out.flush();
     } catch (IOException e){
             System.err.println("Comm Error... "+ e);
         }finally {
             try{
                 socket.close();
             }catch (IOException e){System.err.println(e);}
         }
     }
}
