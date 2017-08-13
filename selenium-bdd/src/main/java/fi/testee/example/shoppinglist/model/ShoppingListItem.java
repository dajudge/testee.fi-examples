package fi.testee.example.shoppinglist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShoppingListItem {
    @Id
    private String uuid;

    private long timestamp;

    private String title;

    public ShoppingListItem() {
    }

    public ShoppingListItem(final String uuid, final long timestamp, final String title) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
