package fi.testee.examples.resourcedef;

import fi.testee.junit4.TestEEfi;
import fi.testee.util.resourcedef.ResourceDef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.enterprise.concurrent.ManagedExecutorService;

import static org.junit.Assert.assertSame;

@RunWith(TestEEfi.class)
public class SessionBeanTest {
    @ResourceDef
    @Mock
    private EJBContext ejbContext;
    @ResourceDef
    @Mock
    private ManagedExecutorService managedExecutorService;

    @EJB
    private SessionBean sessionBean;

    @Test
    public void has_ejb_context() {
        assertSame(ejbContext, sessionBean.getEjbContext());
    }

    @Test
    public void has_executorService() {
        assertSame(managedExecutorService, sessionBean.getManagedExecutorService());
    }
}
