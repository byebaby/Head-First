package com.cyb.chat.client;

import com.cyb.chat.entity.User;
import com.cyb.chat.handler.cmd.*;
import com.cyb.chat.handler.server.*;
import com.cyb.chat.model.Result;
import com.cyb.chat.util.EasySocket;
import com.cyb.chat.util.ThreadPool;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Cyb
 */
public class Client {

    private static Scanner scanner;
    private static User user;
    private static Instant instantHeart = Instant.now();
    private static final int HEART_TIMEOUT = 20;

    static {
        while (true) {
            scanner = new Scanner(System.in);
            try {
                System.out.println("请输入服务器IP地址:");
                String serverHost = scanner.nextLine();
                System.out.println("请输入服务器端口号:");
                Integer serverPort = Integer.parseInt(scanner.nextLine());
                System.out.println("正在连接服务器...");
                EasySocket easySocket = new EasySocket(new Socket(serverHost, serverPort));
                System.out.println("服务器连接成功");
                user = new User(easySocket);
                System.out.println("客户端初始化完成");
                System.out.println("=============================================");
            } catch (NumberFormatException e) {
                System.out.println("格式异常（错误原因：" + e.getMessage() + "）");
                continue;
            } catch (ConnectException e) {
                System.out.println("连接服务器失败（错误原因：" + e.getMessage() + "）");
                continue;
            } catch (IOException e) {
                System.out.println("输入/输出流异常（错误原因：" + e.getMessage() + "）");
                continue;
            } catch (Exception e) {
                System.out.println("启动失败（错误原因：" + e.getMessage() + "）");
                continue;
            }
            break;
        }
    }

    private void go() {
        ThreadPool.getScheduledThreadPoolExecutor().scheduleWithFixedDelay(new ReadRunnable(), 0, 100, TimeUnit.MILLISECONDS);
        ThreadPool.getScheduledThreadPoolExecutor().scheduleWithFixedDelay(new HeartRunnable(), 0, 5, TimeUnit.SECONDS);
        System.out.println("请输入使用的昵称");
        while (true) {
            String msg = scanner.nextLine();
            if (Duration.between(instantHeart, Instant.now()).getSeconds() > HEART_TIMEOUT) {
                return;
            }
            cmdMsgHandler(user, msg);
        }
    }

    public static void main(String[] args) {
        new Client().go();
    }

    private static void cmdMsgHandler(User user, String msg) {
        BaseCmdHandler baseCmdHandler = new MsgCmdHandler(null);
        baseCmdHandler = new CmdCmdHandler(baseCmdHandler);
        baseCmdHandler = new NameCmdHandler(baseCmdHandler);
        baseCmdHandler = new EmptyCmdHandler(baseCmdHandler);
        baseCmdHandler.handlerResult(user, msg);
    }

    private static void serverMsgHandler(User user, Result result) {
        BaseServerHandler baseServerHandler = new CmdServerHandler(null);
        baseServerHandler = new NameServerHandler(baseServerHandler);
        baseServerHandler = new NotifyServerHandler(baseServerHandler);
        baseServerHandler = new MsgServerHandler(baseServerHandler);
        baseServerHandler = new HeartServerHandler(baseServerHandler);
        baseServerHandler.handlerResult(user, result);
    }

    private static class ReadRunnable implements Runnable {
        @Override
        public void run() {
            try {
                if (user.readReady()) {
                    instantHeart = Instant.now();
                    Result result = user.readMsg();
                    serverMsgHandler(user, result);
                }
                if (Duration.between(instantHeart, Instant.now()).getSeconds() > HEART_TIMEOUT) {
                    System.out.println(user.getHostAddress() + "服务器已退出");
                    try {
                        user.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ThreadPool.getScheduledThreadPoolExecutor().shutdownNow();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class HeartRunnable implements Runnable {
        @Override
        public void run() {
            user.tellHeart();
        }
    }
}

