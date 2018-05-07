package heigvd;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class SmtpClient implements ISmtpClient {
    private int port;
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;

    public SmtpClient(String hostname, int port) {
        this.port = port;
        try {
            socket = new Socket(hostname, port);
            out = new OutputStreamWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            waitCommandConfirmation();
        } catch (IOException e) {
            return;
        }
    }

    private String read(){
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String line){
        this.write(line, true);
    }

    public void write(String line, boolean newLine){
        try {
            String output = newLine ? line + "\r\n" : line;
            out.write(output);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(RecipientGroup group, Message message){
        this.hello();
        this.setSender(group.getSender());
        for(EmailAccount recipient: group.getRecipients()){
            this.addRecipient(recipient);
        }
        this.sendMessage(group, message);
        System.out.println("Message sent to " + group.getName());
    }

    private void hello(){
        this.write(COUCOU + " HEIG_PRANK");
        this.waitCommandConfirmation();
    }

    private void setSender(EmailAccount sender){
        this.write(FROM + sender.getEmail());
        this.waitCommandConfirmation();
    }

    private void addRecipient(EmailAccount recipient){
        this.write(TO + recipient.getEmail());
        this.waitCommandConfirmation();
    }

    private void sendMessage(RecipientGroup group, Message message){
        this.write(START_DATA);
        this.write(HEADER_FROM + group.sender.toString());
        this.write(HEADER_TO, false);
        String recipientsHeader = "";
        for(EmailAccount recipient: group.getRecipients()){
            recipientsHeader += recipient.toString() + HEADER_SEPARATOR;
        }
        recipientsHeader = recipientsHeader.substring(0, recipientsHeader.length() - 1);
        this.write(recipientsHeader);
        this.write(HEADER_SUBJECT + message.getSubject());
        this.write(message.getBody());
        this.write(".");
        waitCommandConfirmation();

    }

    private void waitCommandConfirmation(){
        // If error, loop silently.
        String input;

        while((input = this.read()) != null){
            if(input.indexOf(EOF) == 0 || input.indexOf(OK) == 0)
                break;
        }
    }
}
