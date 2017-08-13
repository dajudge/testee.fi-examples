package fi.testee.example.shoppinglist.util;

/**
 * It's always a good idea to wrap the time into a bean so you can mock it in tests.
 *
 * @author Alex Stockinger, IT-Stockinger
 */
public class TimeService {
    public long timestamp() {
        return System.currentTimeMillis();
    }
}
