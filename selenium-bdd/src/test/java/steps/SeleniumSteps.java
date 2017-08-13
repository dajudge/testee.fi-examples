package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fi.testee.example.shoppinglist.BaseUrlProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class SeleniumSteps {
    @Inject
    private BaseUrlProvider baseUrlProvider;
    @Inject
    private WebDriver webDriver;

    @When("^I open (.*)$")
    public void open_path(final String path) throws Throwable {
        webDriver.get(baseUrlProvider.getBaseUrl() + path);
    }

    @When("^I enter \"([^\"]*)\" into the (.*) field$")
    public void input(final String value, final String id) throws Throwable {
        final WebElement input = eventually(() -> webDriver.findElement(By.id(id)));
        input.clear();
        input.sendKeys(value);
    }

    @When("^I click the \"([^\"]*)\" button$")
    public void click_button(final String caption) throws Throwable {
        eventually(() -> {
            final Set<WebElement> buttons = webDriver.findElements(By.tagName("button")).stream()
                    .filter(it -> it.getText().equals(caption))
                    .collect(toSet());
            assertFalse("No button with caption '" + caption + "' was found", buttons.isEmpty());
            assertFalse("Multiple buttons with caption '" + caption + "' were found", buttons.size() > 1);
            return buttons.iterator().next();
        }).click();
    }

    @Then("^the table should contain a row with value \"([^\"]*)\"$")
    public void assert_table_contains(final String value) throws Throwable {
        notLoadingAnymore();
        findRowByTitle(value);
    }

    @When("^I click the checkmark button of the \"([^\"]*)\" item$")
    public void iClickTheButtonOfTheItem(final String itemTitle) throws Throwable {
        findRowByTitle(itemTitle).findElement(By.tagName("button")).click();
    }

    @Then("^the table should contain (\\d+) items$")
    public void theTableShouldContainItems(final int count) throws Throwable {
        notLoadingAnymore();
        eventually(() -> {
            final List<WebElement> rows = webDriver.findElement(By.tagName("table"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));
            assertEquals(count, rows.size());
            return null;
        });
    }

    @When("^I add the following items$")
    public void add_items(final DataTable table) throws Throwable {
        for (final String item : table.asList(String.class)) {
            input(item, "itemToAdd");
            click_button("Add");
        }
    }

    private WebElement findRowByTitle(final String title) throws Throwable {
        return eventually(() -> {
            final List<WebElement> rows = webDriver.findElement(By.tagName("table"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));
            for (final WebElement row : rows) {
                final String rowValue = row.findElements(By.tagName("td")).get(1).getText();
                if (title.equals(rowValue)) {
                    return row;
                }
            }
            fail("No row with title '" + title + "' could be found.");
            return null;
        });
    }

    private void notLoadingAnymore() throws Throwable {
        eventually(() -> webDriver.findElement(By.className("loaderPlaceholder")));
    }

    private <T> T eventually(final Supplier<T> r) throws Throwable {
        return eventually(r, 30000);
    }

    private <T> T eventually(Supplier<T> r, int timeout) throws Throwable {
        final long start = currentTimeMillis();
        Throwable lastError = null;
        while (currentTimeMillis() - start < timeout) {
            try {
                return r.get();
            } catch (final Error | RuntimeException e) {
                lastError = e;
            }
            sleep(100);
        }
        throw lastError;
    }
}
