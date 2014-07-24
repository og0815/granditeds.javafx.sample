package org.graniteds.tutorial.data.services;

import java.util.Map;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.model.Page;
import org.granite.tide.data.model.PageInfo;
import org.graniteds.tutorial.data.entities.Account;

// tag::service[]
@RemoteDestination // <1>
@DataEnabled(topic = "dataTopic", publish = DataEnabled.PublishMode.ON_SUCCESS) // <2>
public interface AccountService {

    public void save(Account account);

    public void remove(Account account);

    public Page<Account> findByFilter(Map<String, String> accountFilter, PageInfo pageInfo);

    public String hello(String name);
}
// end::service[]
