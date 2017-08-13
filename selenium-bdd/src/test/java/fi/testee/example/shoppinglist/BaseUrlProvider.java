package fi.testee.example.shoppinglist;

import fi.testee.rest.RestServer;

import javax.annotation.Resource;

public class BaseUrlProvider {
    @Resource
    private RestServer restServer;

    public String getBaseUrl() {
        return "http://localhost:" + restServer.getPort();
    }
}
