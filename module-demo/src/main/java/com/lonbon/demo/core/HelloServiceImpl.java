package com.lonbon.demo.core;

import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Remoting;

@Mapping("/rpc/hello")
@Remoting
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(String name) {
        return String.format("Hello %s!", name);
    }
}