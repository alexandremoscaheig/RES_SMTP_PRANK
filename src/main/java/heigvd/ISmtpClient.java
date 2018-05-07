package heigvd;

interface ISmtpClient {
    String COUCOU = "EHLO";
    String EOF = "250 ";
    String OK = "220 ";
    String FROM = "MAIL FROM: ";
    String TO = "RCPT TO: ";
    String START_DATA = "DATA";
    String HEADER_FROM = "from: ";
    String HEADER_TO = "to: ";
    String HEADER_SUBJECT = "subject: ";
    String HEADER_SEPARATOR = ",";

    public void sendEmail(RecipientGroup group, Message message);
}
