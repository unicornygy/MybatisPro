package com.unicorn;

import com.unicorn.dao.UserDao;
import com.unicorn.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createSqlSessionFactory() throws IOException{
        String resource = "MyBatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(inputStream);
    }

    @Test
    public void testFindUserById() throws Exception{
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.findUserById(2);
        System.out.println(user);
        session.close();
        System.out.println("work done!");
    }

    @Test
    public void testFindUserByName() throws Exception{
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> userList = userDao.findUserByName("张三");
        for (User user: userList) {
            System.out.println(user);
        }
        session.close();
        System.out.println("work done!");
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setId(2);
        user.setBirth(new Date());
        user.setName("李四");
        user.setSex("女");
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.updateUser(user);
        session.commit();
        session.close();
        System.out.println("work done!");
    }

    @Test
    public void testDeleteUserById() throws Exception{
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.deleteUserById(1);
        session.commit();
        session.close();
        System.out.println("work done!");
    }

    @Test
    public void testInsertUser() throws Exception{
        User user = new User();
        user.setId(1);
        user.setBirth(new Date());
        user.setName("张三");
        user.setSex("男");
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.insertUser(user);
        session.commit();
        session.close();
        System.out.println("work done!");
    }
}
