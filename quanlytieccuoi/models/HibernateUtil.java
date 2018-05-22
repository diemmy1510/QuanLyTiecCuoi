/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author LeMy
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration configure = new Configuration();
        configure.addAnnotatedClass(KhachHang.class);
        configure.addAnnotatedClass(Mon.class);
        configure.addAnnotatedClass(Menu.class);
        configure.addAnnotatedClass(Tiec.class);
        configure.addAnnotatedClass(Bep.class);
        configure.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder
                = new StandardServiceRegistryBuilder().applySettings(configure.getProperties());
        FACTORY = configure.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
