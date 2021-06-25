package server;

import common.User;
import service.UserService;

import java.util.UUID;
import java.util.Random;
/**
 * @author tanxincheng
 * @version 1.5
 * @description
 * @date 2021/6/25
 * @link
 */
public class UserServiceImpl implements UserService{
    public User getUserById(Integer id){
        System.out.println("客户端查询了"+id+"的用户");
        //模拟从数据库查询用户的行为
       User user = User.builder().name(UUID.randomUUID().toString()).id(id).sex(true).build();
       return user;
    }
}
