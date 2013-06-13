package net.al76.services;

import net.al76.util.HtmlMail;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Service(value = EmailService.class)
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    String jobName = "caseSample";
    @Reference
    MessageGatewayService messageGatewayService;

    @Override
    public String sendMessage(ArrayList<String> recipients, String htmlBody, boolean debug) throws MessagingException {
        logger.info("Schedule Service TEST Run: " + jobName);

        MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
        if (messageGateway == null) {
            throw new MessagingException("Unable to retrieve message gateway for HTMLEmails, "
                    + "please configure Gateway in OSGi Console");
        } else {
            logger.info("MARK Message gateway for HTMLEmails is OK");
            HtmlMail email;

            try {
                email = new HtmlMail("CQ5 Mind-Email-Service", htmlBody, recipients, debug);
                email.getMessage();
                messageGateway.send(email.getMessage());

            } catch (AddressException ex) {
                ex.printStackTrace();
            } catch (EmailException ex) {
                ex.printStackTrace();
            }
        }

        return "Mail Sent OK";
    }

    @Activate
    protected void activate() {
        logger.info("SampleServiceImpl START ___MARK");
    }

    @Deactivate
    protected void deactivate() {
        logger.info("SampleServiceImpl STOP ___MARK");
    }

    public String sendHelloMessage() throws MessagingException {
        ArrayList<String> recipients = new ArrayList<String>();
        recipients.add("alessio.finamore@mindagency.com");

        sendMessage(recipients, "<h1>Hello From Mind Service</h1>", true);

        return "Message Sent OK";
    }

}
