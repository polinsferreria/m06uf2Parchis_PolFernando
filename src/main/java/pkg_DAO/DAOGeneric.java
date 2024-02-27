package pkg_DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class DAOGeneric<T, ID extends Serializable> implements IDAOGeneric<T, ID> {

    SessionFactory sessionFactory;

    // private final static Logger LOGGER =
    // Logger.getLogger(GenericDao.class.getName());
    public DAOGeneric() {
        sessionFactory = Utils.getSessionFactory();
    }

    @Override
    public void saveOrUpdate(T entity) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            //System.out.println("SaveOrUpdate successful for entity: " + entity.getClass().getSimpleName());

        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public T get(ID id) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            session.getTransaction().commit();

            return entity;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;

        }

    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void delete(Serializable id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();

        }
    }

    public void delete(Object entity) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();

        }
    }

    public List list() {
        Session session = sessionFactory.getCurrentSession();
        // TODO Auto-generated method stubSession session = sessionFactory.getCurrentSession();
        try {

            List<T> entities = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e").list();
            return entities;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

}
