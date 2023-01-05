package ru.gb.hibernate;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ModelFactory {
    private static SessionFactory factory;
        @PostConstruct
        public void init() {
            factory = new Configuration()
                    .configure("/hibernate.cfg.xml")
                    . buildSessionFactory();
        }

        public Session getSession() {
            System.out.println(factory.toString());
            return factory.openSession();
        }

        public void closeSession(Session session) {
            if (session != null) {
                session.close();
            }
        }

        @PreDestroy
        public void close() {
            if (factory != null) {
                factory.close();
            }
        }
    }

