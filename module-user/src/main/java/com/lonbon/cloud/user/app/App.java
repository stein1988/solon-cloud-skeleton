package com.lonbon.cloud.user.app;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;

@SolonMain
@Import(scanPackages = "com.lonbon.cloud.user")
public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args);
    }
}
