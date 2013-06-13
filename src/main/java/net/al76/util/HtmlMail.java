/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.al76.util;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author afinamore
 */
public class HtmlMail {

    private HtmlEmail email = new HtmlEmail();
    private String htmlBody;
    private ArrayList<String> recipients;
    ArrayList<InternetAddress> emailRecipients = new ArrayList<InternetAddress>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HtmlMail(String subject, String htmlBody, ArrayList<String> recipients) throws AddressException, EmailException {
        this(subject, htmlBody, recipients, false);
    }
    public HtmlMail(String subject, String htmlBody, ArrayList<String> recipients, boolean debug) throws AddressException, EmailException {
        for (String internetAddress : recipients) {
            InternetAddress addr = new InternetAddress(internetAddress);
            emailRecipients.add(addr);
        }
        this.htmlBody = htmlBody;
        // Create the email message
        if (debug){
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("ale.finam", "4l3dicebay0"));
            email.setTLS(true);
            email.setFrom("ale.finam@gmail.com", "Ale Bundle Email");
        }

        email.setSubject(subject);
        email.setTo(emailRecipients);
        email.setHtmlMsg(htmlBody);
        email.setTextMsg("Your email client does not support HTML messages");
        // set the alternative message
    }

    public HtmlEmail getMessage() {
        return this.email;
    }
    public String send() throws EmailException {
        return email.send();
    }
}
