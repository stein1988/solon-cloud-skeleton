package com.lonbon.cloud.user.app;

import com.lonbon.cloud.base.config.UUIDConverter;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;

@SolonMain
@Import(scanPackages = {"com.lonbon.cloud.user", "com.lonbon.cloud.base"})
public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args, app->{
            app.converters().register(new UUIDConverter()); 
        });
    }
}
