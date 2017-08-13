package fi.testee.example.shoppinglist.facade.transport;

public class ShoppingListItemTO {
    private String uuid;
    private long timestamp;
    private String title;

    public ShoppingListItemTO() {
    }

    public ShoppingListItemTO(final String uuid, final long timestamp, final String title) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
