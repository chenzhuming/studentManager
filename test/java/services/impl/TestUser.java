package services.impl;

import db.MyHibernateSessionFactory;
import entity.Students;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import services.UsersDAO;

import java.sql.Date;

public class TestUser {
    @Test
    public void testLogin(){
        UsersDAO usersDAO = new UsersDAOImpl();
        Users users = new Users();
        users.setPassword("12345678");
        users.setUsername("chen");
        Assert.assertEquals(true,usersDAO.usersLogin(users));
    }
    @Test
    public void saveStudents(){
        Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Students s1 = new Students("s0000001","张三丰","男", Date.valueOf("1989-12-19"),"天津");
        Students s2 = new Students("s0000002","郭靖","男", Date.valueOf("1989-12-19"),"天津");
        Students s3 = new Students("s0000003","黄蓉","女", Date.valueOf("1989-12-19"),"天津");
        tx = session.beginTransaction();
        session.save(s1);
        session.save(s2);
        session.save(s3);
        tx.commit();
    }
    @Test
    public void save(){
        Students s1 = new Students("","张三aaa","男", Date.valueOf("1989-12-19"),"天津");
        StudentsDAOImpl sss = new StudentsDAOImpl();
        Assert.assertEquals(true,sss.addStudents(s1));
    }
}
