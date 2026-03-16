package com.lonbon.cloud.user.app;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.SolonMain;

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
