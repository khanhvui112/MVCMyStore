package com.sanvui.model.dao.Imp;

import com.sanvui.model.dao.BaseDao;
import com.sanvui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-11:35 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class BaseDaoImp<T> implements BaseDao<T> {
    private Class<T> classTyper;

    public BaseDaoImp(Class<T> classTyper) {
        this.classTyper = classTyper;
    }


    /*
     * find all
     * request: List object
     * */
    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtils.getSession()) {
            return session.createQuery("FROM " + classTyper.getName(), this.classTyper).list();
        }
    }

    /*
     * find by id
     * param: int id
     * request: a object
     * */
    @Override
    public T findById(int id) {

        try (Session session = HibernateUtils.getSession()) {
            return session.get(this.classTyper, id);
        }
    }

    /*
     * insert to database
     * param: List<Object>
     * if success return true else rollback and return false
     */
    @Override
    public boolean insert(List<T> list) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            for (T t : list) {
                session.save(t);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * delete object by id
     * if success return true else return false
     *
     * */
    @Override
    public boolean delete(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            T t = session.load(this.classTyper, id);
            if (t != null) {
                session.delete(t);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * update object
     * param: a object
     * if success return true else rollback and return false
     * */
    @Override
    public boolean update(List<T> list) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            for (T t : list) {
                session.update(t);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteMultiple(List<Integer> ids) {
        try (Session session = HibernateUtils.getSession()) {

            Transaction transaction = session.beginTransaction();
// Write all pending changes to the DB and clear persistence context
            session.flush();
            session.clear();
            String nameId = null;
            if(classTyper.getSimpleName().equalsIgnoreCase("Color")){
                nameId = "color_Id";
            }


// Remove all entities referenced in the List ids variable
            Query query = session.createQuery("DELETE "+classTyper.getName()+" WHERE "+nameId+" IN (:ids)");
            query.setParameter("ids", ids);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        }
    }
}
