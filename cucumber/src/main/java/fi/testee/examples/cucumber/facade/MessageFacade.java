package fi.testee.examples.cucumber.facade;

import fi.testee.examples.cucumber.business.MessageService;
import fi.testee.examples.cucumber.model.Message;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Singleton
public class MessageFacade {
    @EJB
    private MessageService messageService;

    public void addMessage(final String messageText) {
        messageService.addMessage(new Message(
                System.currentTimeMillis(),
                messageText
        ));
    }

    public Set<String> getMessages() {
        return messageService.getAllMessages().stream()
                .map(Message::getText)
                .collect(toSet());
    }
}
