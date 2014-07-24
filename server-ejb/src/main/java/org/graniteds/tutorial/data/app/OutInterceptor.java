/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.graniteds.tutorial.data.app;

import flex.messaging.messages.Message;
import org.granite.messaging.amf.process.AMF3MessageInterceptor;

/**
 *
 * @author oliver.guenther
 */
public class OutInterceptor implements AMF3MessageInterceptor {

    @Override
    public void before(Message request) {
        // System.out.println("Interceptor-Before:" + request);
    }

    @Override
    public void after(Message request, Message response) {
        if (response == null) {
            System.out.println("Interceptor-After: response == null");
        } else {
            System.out.println("Interceptor-After.body:" + response.getBody());
        }
    }

}
