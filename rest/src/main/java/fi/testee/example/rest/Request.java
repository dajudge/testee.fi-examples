package fi.testee.example.rest;

public class Request {
    private String value;

    public Request() {
    }

    public Request(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
