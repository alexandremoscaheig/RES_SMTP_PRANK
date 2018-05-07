package heigvd;

import java.util.ArrayList;
import java.util.List;

public class RecipientGroup {

    private String name;
    List<EmailAccount> recipients;
    EmailAccount sender;


    public RecipientGroup(String name){
        this.name = name;
        recipients = new ArrayList<EmailAccount>();
    }

    public void addRecipient(EmailAccount emailAccount){
        this.recipients.add(emailAccount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmailAccount getSender() {
        return sender;
    }

    public void setSender(EmailAccount sender) {
        this.sender = sender;
    }

    public List<EmailAccount> getRecipients() {
        return recipients;
    }
}
