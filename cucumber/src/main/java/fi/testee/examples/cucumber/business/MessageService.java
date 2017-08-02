package fi.testee.examples.cucumber.business;

import fi.testee.examples.cucumber.adapter.TwitterAdapter;
import fi.testee.examples.cucumber.dataaccess.MessageDao;
import fi.testee.examples.cucumber.model.Message;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.Set;

@Singleton
public class MessageService {
    @EJB
    private MessageDao messageDao;
    @EJB
    private TwitterAdapter twitterAdapter;

    public void addMessage(final Message message) {
        twitterAdapter.publishOnTwitter(message.getText());
        messageDao.persist(message);
    }

    public Set<Message> getAllMessages() {
        return messageDao.getAll();
    }
}
