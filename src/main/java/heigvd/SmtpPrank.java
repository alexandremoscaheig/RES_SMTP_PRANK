package heigvd;


import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmtpPrank {

    private ISmtpClient smtpClient;
    private List<RecipientGroup> recipientGroups;
    private List<Message> messages;


    public SmtpPrank(String hostname, int port, String fileName){
        recipientGroups = new ArrayList<>();
        messages = new ArrayList<>();
        this.readConfig(fileName);
        this.smtpClient = new SmtpClient(hostname, port);
        for(RecipientGroup group : recipientGroups){

            System.out.println("Select message to send to group " + group.getName() + " : ");
            for(Message message : messages){
                System.out.println(messages.indexOf(message) + " - " + message.getSubject());
            }
            System.out.print("Message ID : ");
            Scanner in = new Scanner(System.in);

            int selectedMessage = in.nextInt();

            this.smtpClient.sendEmail(group, messages.get(selectedMessage));
        }
    }

    private void readConfig(String fileName){
        try {
            File inputFile = new File(fileName);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);

            Element root = document.getRootElement();

            List<Element> groups = root.getChild("groups").getChildren();

            groups.forEach(group -> {
                // Create group
                RecipientGroup newGroup = new RecipientGroup(group.getChildText("name"));
                // Set from email
                Element from = group.getChild("from");
                newGroup.setSender(new EmailAccount(from.getText(), from.getAttributeValue("value")));

                // add recipients
                List<Element> dests = group.getChild("dests").getChildren();
                dests.forEach(dest -> {
                    newGroup.addRecipient(new EmailAccount(dest.getText(), dest.getAttributeValue("value")));
                });

                recipientGroups.add(newGroup);
            });

            // Load pre-saved messages
            List<Element> messages = root.getChild("messages").getChildren();
            messages.forEach(message -> {
                this.messages.add(new Message(message.getChildText("subject"), message.getChildText("body")));
            });

        } catch(JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
