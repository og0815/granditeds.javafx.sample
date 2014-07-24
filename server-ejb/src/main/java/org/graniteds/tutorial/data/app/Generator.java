package org.graniteds.tutorial.data.app;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import org.graniteds.tutorial.data.entities.Account;
import org.graniteds.tutorial.data.entities.Box;

import java.util.*;

/**
 *
 * @author oliver.guenther
 */
@Singleton
@Startup
public class Generator {

    private final static Random R = new Random();

    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext sc;

    @PostConstruct
    public void make() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Account a = new Account("Generate" + i, "email" + i + "@example.com");
            for (int j = 0; j < R.nextInt(7); j++) {
                a.addPhone("+49" + R.nextInt(10000000));
            }
            em.persist(a);
            accounts.add(a);
        }
        em.flush();
        for (Account account : accounts) {
            for (int j = 0; j < R.nextInt(7); j++) {
                account.add(new Box("B" + j + "atA" + account.getId()));
            }
        }

        EntityManagerFactory emf = (EntityManagerFactory) sc.lookup("java:global/data/EntityManagerSupporter");
        EntityManager em = emf.createEntityManager();
        System.out.println("IS found : " + emf);
    }

}
