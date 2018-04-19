package services.impl;

import db.MyHibernateSessionFactory;
import entity.Students;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.StudentsDAO;

import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {
    @Override
    public List<Students> queryAllStudents() {

        Transaction tx = null;
        List<Students> list = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            String sql = "from Students";
            tx = session.beginTransaction();
            Query query = session.createQuery(sql);
            list = query.list();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
        return list;
    }

    @Override
    public Students queryStudentBySid(String sid) {
        Transaction tx = null;
        Students student = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            String sql = "from Students where sid=?";
            tx = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter(0, sid);
            student = (Students) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
        return student;
    }

    @Override
    public boolean addStudents(Students s) {
        String newid = getNewSid();
        s.setSid(newid);
        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean updateStudents(Students s) {
        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.update(s);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean deleteStudents(String sid) {
        Transaction tx = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Students students = session.get(Students.class, sid);
            session.delete(students);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    private String getNewSid() {
        Transaction tx = null;
        String sid = null;
        Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            String sql = "select max(sid) from Students";
            Query query = session.createQuery(sql);
            String temp = (String) query.uniqueResult();
            tx.commit();
            if (temp == null || "".equals((temp))) {
                sid = "S0000001";
            } else {
                sid = "S" + StringUtils.leftPad(String.valueOf(Integer.parseInt(temp.substring(1)) + 1), 7, "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
        return sid;
    }
}
