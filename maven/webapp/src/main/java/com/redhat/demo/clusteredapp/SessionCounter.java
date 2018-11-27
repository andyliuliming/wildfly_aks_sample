package com.redhat.demo.clusteredapp;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

@Stateful
@SessionScoped
public class SessionCounter {
    long counter = 0;

    public long getAndIncrement() {
        return counter++;
    }

}
