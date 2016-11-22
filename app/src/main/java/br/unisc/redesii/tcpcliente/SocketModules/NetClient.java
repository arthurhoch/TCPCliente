package br.unisc.redesii.tcpcliente.SocketModules;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetClient {

    public static final int BUFFER_SIZE = 4096;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    private String host;
    private int port;


    public NetClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void connectWithServer() {
        try {
            if (socket == null) {
                socket = new Socket(this.host, this.port);
                socket.setReceiveBufferSize(BUFFER_SIZE);
                socket.setSendBufferSize(BUFFER_SIZE);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disConnectWithServer() {
        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendDataWithString(String message) {
        if (message != null) {
            connectWithServer();
            //out.write(message);
            //out.flush();
            out.println(message);
            out.flush();

        }
    }

    public String receiveDataFromServer() {
        try {
            String message = "";

            char[] buffer = new char[BUFFER_SIZE];
            in.read(buffer);
            message = String.copyValueOf(buffer).toString();

            disConnectWithServer();
            return message;
        } catch (IOException e) {
            return "Error receiving response:  " + e.getMessage();
        }
    }

    public String receiveDataFromServer2() {
        try {
            String message = "";
            message = in.readLine();
            disConnectWithServer();
            return message;
        } catch (IOException e) {
            return "Error receiving response:  " + e.getMessage();
        }
    }


}