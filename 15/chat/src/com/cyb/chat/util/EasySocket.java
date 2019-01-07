package com.cyb.chat.util;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Cyb
 */
public class EasySocket {

    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public EasySocket(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public Socket getSocket() {
        return socket;
    }
}
