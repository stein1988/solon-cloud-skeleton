package features;

import com.lonbon.demo.App;
import com.lonbon.demo.HelloService;

import org.junit.jupiter.api.Test;

// import org.noear.nami.annotation.NamiClient;
import org.noear.solon.test.SolonTest;

@SolonTest(App.class)
public class HelloServiceTest {
    // @NamiClient(name = "demo-app", path = "/rpc/hello")
    HelloService helloService;

    @Test
    public void hello() {
//        assert helloService.hello("world").contains("world");
//        assert helloService.hello("solon").contains("solon");
    }
}