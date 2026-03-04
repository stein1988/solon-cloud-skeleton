package com.lonbon.cloud.user.app;

import com.lonbon.cloud.base.config.UUIDConverter;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;
import org.noear.solon.docs.DocDocket;

@SolonMain
@Import(scanPackages = {"com.lonbon.cloud.user"})
public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args);
    }
}


//@Configuration
//public class DocConfig {
//    @Bean("appApi")
//    public DocDocket appApi() {
//        return new DocDocket()
//                .groupName("app端接口")
//                .apis("com.swagger.demo.controller.app");
//
//    }
//}
