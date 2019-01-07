package com.cyb.chat.server;

import com.cyb.chat.entity.User;
import com.cyb.chat.handler.client.*;
import com.cyb.chat.model.Result;
import com.cyb.chat.util.EasySocket;
import com.cyb.chat.util.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author Cyb
 */
public class Server {
    private static ServerSocket serverSocket;
    private static Vector<User> users = new Vector<>();


    static {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("服务端正在启动...");
            System.out.println("输入服务端监听端口:");
            Integer port;
            try {
                port = Integer.parseInt(scanner.nextLine());
                serverSocket = new ServerSocket(port);
            } catch (Exception e) {
                System.out.println("服务端启动失败（错误原因：" + e.getMessage() + "）");
                continue;
            }
            System.out.println("服务端启动成功,监听端口: " + port);
            System.out.println("==========================================================");
            break;
        }
    }

    private void go() {
        ThreadPool.getScheduledThreadPoolExecutor().scheduleWithFixedDelay(new HeartRunnable(), 0, 5, TimeUnit.SECONDS);
        while (true) {
            try {
                EasySocket easySocket = new EasySocket(serverSocket.accept());
                ThreadPool.getThreadPoolExecutor().execute(new ClientRunnable(easySocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().go();
    }

    private static void clientMsgHandler(Vector<User> users, User user, Result result) {
        BaseClientHandler baseClientHandler = new CmdClientHandler(null);
        baseClientHandler = new NameClientHandler(baseClientHandler);
        baseClientHandler = new MsgClientHandler(baseClientHandler);
        baseClientHandler = new HeartClientHandler(baseClientHandler);
        baseClientHandler = new EmptyClientHandler(baseClientHandler);
        baseClientHandler.handlerResult(users, user, result);
    }

    private static class ClientRunnable implements Runnable {
        private User user;
        private Instant instantHeart = Instant.now();
        private int heartTimeout = 20;

        ClientRunnable(EasySocket easySocket) {
            System.out.println(easySocket.getSocket().getRemoteSocketAddress() + "已连接");
            user = new User(easySocket);
            users.add(user);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(100);
                    if (user.readReady()) {
                        instantHeart = Instant.now();
                        Result result = user.readMsg();
                        clientMsgHandler(users, user, result);
                    }
                    if (Duration.between(instantHeart, Instant.now()).getSeconds() > heartTimeout) {
                        BaseClientHandler.chatTellNotify(users, "用户退出: " + user.getName());
                        users.remove(user);
                        System.out.println(user.getHostAddress() + "已退出");
                        user.close();
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class HeartRunnable implements Runnable {
        @Override
        public void run() {
            for (User user : users) {
                user.tellHeart();
            }
        }
    }
}


