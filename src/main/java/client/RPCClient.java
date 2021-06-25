package client;

import common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @author tanxincheng
 * @version 1.5
 * @description
 * @date 2021/6/25
 * @link
 */
public class RPCClient {
    public static void main(String args[]){
        try{
            //建立socket连接
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //传给服务器id
            objectOutputStream.writeInt(new Random().nextInt());
            //将缓冲区内的内容全部输出
            objectOutputStream.flush();

            User user = (User) objectInputStream.readObject();
            System.out.println("服务端返回的User:"+user);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }
    }
}
