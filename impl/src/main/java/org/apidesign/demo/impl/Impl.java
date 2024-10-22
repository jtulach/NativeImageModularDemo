package org.apidesign.demo.impl;

import org.apidesign.demo.api.Api;

public final class Impl implements Api {
    @Override
    public String hello() {
        return "Hi from Impl!";
    }
}
