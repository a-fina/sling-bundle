package net.al76.services;

import java.util.ArrayList;
import javax.mail.MessagingException;

public interface EmailService {
    public String sendMessage(ArrayList<String> recipients, String htmlBody, boolean debug) throws MessagingException;
    public String sendHelloMessage() throws MessagingException;
}
