/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.al76.servlets;

/**
 *
 * @author afinamore
 */
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
 
import javax.servlet.ServletException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@SlingServlet(
    paths={"/services/mytest/mySearchServlet"}
)
@Properties({
    @Property(name="service.pid", value="net.al76.servlets.MyServlet",propertyPrivate=false),
    @Property(name="service.description",value="Al76 Sservice call logging servlet", propertyPrivate=false),
    @Property(name="service.vendor",value="Al76 Team", propertyPrivate=false)
})
public class MyServlet extends SlingAllMethodsServlet
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
        //Do something fun here
        logger.info("MARK GET request from: " + request.getRemoteAddr());
    }
 
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
        //Do something fun here
        logger.info("MARK POST request from: " + request.getRemoteAddr());
    }
}