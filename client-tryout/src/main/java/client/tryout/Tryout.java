/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.tryout;

import javafx.application.Application;
import javafx.stage.Stage;
import org.granite.client.javafx.tide.JavaFXApplication;
import org.granite.client.tide.Context;
import org.granite.client.tide.impl.SimpleContextManager;
import org.granite.client.tide.server.*;
import org.granite.tide.data.model.Page;
import org.granite.tide.data.model.PageInfo;
import org.graniteds.tutorial.data.client.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Future;

/**
 *
 * @author oliver.guenther
 */
public class Tryout extends Application {

    public static class SoutResponder<T> implements TideResponder<T> {

        private final String head;

        public SoutResponder(String head) {
            this.head = head;
        }

        @Override
        public void result(TideResultEvent<T> event) {
            System.out.println(head + ".result: " + event.getResult());
        }

        @Override
        public void fault(TideFaultEvent event) {
            System.err.println(head + ".fault: " + event.getFault().getCode() + ":"
                    + event.getFault().getFaultDescription());
        }

    }

    private final static Context context = new SimpleContextManager(new JavaFXApplication()).getContext();

    public static void main(String[] args) throws Exception {
        Application.launch(Tryout.class, args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final ServerSession serverSession = context.set(new ServerSession("/data", "localhost", 8080));
        serverSession.addRemoteAliasPackage("org.graniteds.tutorial.data.client");
        context.set("accountService", new AccountService(serverSession));

        serverSession.start(); // <4>

        AccountService accountService = context.byType(AccountService.class);

        accountService.hello("TestValue", new SoutResponder<String>("Service.hello"));

//        Account a = new Account();
//        a.setEmail("horst@muh.de");
//        a.setName("Muh");
//        a.setWebSite("URL");
//        accountService.save(a, new SoutResponder<Void>("Service.save"));
        primaryStage.show();

        Future<Page<Account>> futurResult = accountService
                .findByFilter(new HashMap<String, String>(), new PageInfo(0, 1000), new SoutResponder<Page<Account>>("find"));

        for (Account account : futurResult.get().getResultList()) {
            System.out.println("Service.find: " + account);
            System.out.println(" -" + account.getPhones());
            Iterator<String> i1 = account.getPhones().iterator();
            System.out.println("phones.iterator:" + i1);
            System.out.println("phones.size()=" + account.getPhones().size());
            System.out.println("iterator.hasNext()=" + i1.hasNext());
            for (String phone : account.getPhones()) {
                System.out.println("  -P:" + phone);
            }
            System.out.println(" -" + account.getBoxes());
            Iterator<Box> iterator = account.getBoxes().iterator();
            System.out.println("boxes.iterator:" + iterator);
            System.out.println("boxes.size()=" + account.getBoxes().size());
            for (Box box : account.getBoxes()) {
                System.out.println("  -B:" + box);
            }
        }
        primaryStage.close();

    }

    @Override
    public void stop() throws Exception {
        context.byType(ServerSession.class).stop();
    }

}
