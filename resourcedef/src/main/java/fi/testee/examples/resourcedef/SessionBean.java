package fi.testee.examples.resourcedef;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;

@Singleton
public class SessionBean {
    @Resource
    private EJBContext ejbContext;
    @Resource
    private ManagedExecutorService managedExecutorService;

    public ManagedExecutorService getManagedExecutorService() {
        return managedExecutorService;
    }

    public EJBContext getEjbContext() {
        return ejbContext;
    }
}
