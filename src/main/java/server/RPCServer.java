package server;

import common.User;
import jdk.jfr.events.ExceptionThrownEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tanxincheng
 * @version 1.5
 * @description
 * @date 2021/6/25
 * @link
 */
public class RPCServer {
    public static void main(String args[]){
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");
            //BIO的方式监听Socket
            while (true){
                Socket socket = serverSocket.accept();
                //开启一个线程去处理
                new Thread(()->{
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        //读取客户端传过来的Id
                        Integer id = ois.readInt();
                        User userById = userService.getUserById(id);
                        //写入User对象给客户端
                        oos.writeObject(userById);
                        oos.flush();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }).start();
            }
        }
        catch (Exception e){

        }
    }
}
