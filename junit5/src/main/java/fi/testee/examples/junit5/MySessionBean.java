package fi.testee.examples.junit5;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import java.util.concurrent.ExecutorService;

@Singleton
public class MySessionBean {
    @Resource(mappedName = "concurrent/test")
    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
