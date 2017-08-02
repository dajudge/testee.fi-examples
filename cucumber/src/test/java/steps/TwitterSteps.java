package steps;

import cucumber.api.java.en.Then;
import fi.testee.examples.cucumber.adapter.TwitterAdapter;

import javax.ejb.EJB;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class TwitterSteps {
    // Always use @EJB when accessing mocks, so CDI doesn't inject a proxy instead of the actual mock
    @EJB
    private TwitterAdapter twitterAdapter;

    @Then("^the message \"([^\"]*)\" was published to twitter$")
    public void theMessageWasPublishedToTwitter(final String message) {
        verify(twitterAdapter).publishOnTwitter(message);
    }

    @Then("^no other message was published to twitter$")
    public void noOtherMessageWasPublishedToTwitter() throws Throwable {
        verifyNoMoreInteractions(twitterAdapter);
    }
}
