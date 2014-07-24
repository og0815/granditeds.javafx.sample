package org.graniteds.tutorial.data.services;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.granite.tide.data.model.Page;
import org.granite.tide.data.model.PageInfo;
import org.graniteds.tutorial.data.entities.Account;

import java.util.Map;

// tag::service-impl[]
@Stateless
@Local(AccountService.class)
public class AccountServiceBean implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Account account) {
        entityManager.merge(account); // <1>
    }

    @Override
    public void remove(Account account) {
        account = entityManager.find(Account.class, account.getId());
        entityManager.remove(account);
    }

    @Override
    public Page<Account> findByFilter(Map<String, String> filter, PageInfo pageInfo) { // <2>
        return new AccountSearch(entityManager).findByFilter(filter, pageInfo);
    }

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}
// end::service-impl[]
