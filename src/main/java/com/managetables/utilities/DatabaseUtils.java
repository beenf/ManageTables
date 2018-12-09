package com.managetables.utilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.util.List;
import java.util.Map;

public class DatabaseUtils {
    private static SessionFactory factory;

    public DatabaseUtils() {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Map<String,Object>> sqlQuery(String sql) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Map<String,Object>> results = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String,Object>> aliasToValueMapList = query.list();

            results = aliasToValueMapList;
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

}
