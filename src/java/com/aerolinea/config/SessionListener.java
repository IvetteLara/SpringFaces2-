/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author java
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("======== Session is created ========");
        se.getSession().setMaxInactiveInterval(5*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("======== Session is dropped ========");
    }
    
}
