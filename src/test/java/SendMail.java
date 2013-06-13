/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import junit.framework.TestCase;
import net.al76.util.HtmlMail;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author afinamore
 */
public class SendMail extends TestCase {

    public SendMail(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:

    public void testHello() {
        // Send mail
        try {

            ArrayList<String> recipients = new ArrayList<String>();
            recipients.add("alessio.finamore@mindagency.com");
            HtmlMail htmlm;
            htmlm = new HtmlMail("Hello subject", "<h1>Hello</h1> body", null);
            htmlm.send();

        } catch (EmailException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
