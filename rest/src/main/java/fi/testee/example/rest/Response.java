package fi.testee.example.rest;

public class Response {
    private String value;

    public Response() {
    }

    public Response(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
