<%@page session="false" contentType="text/html; charset=utf-8" %><%
%><%@ page import="com.day.cq.wcm.api.WCMMode,
                   net.al76.services.EmailService,
                   java.util.ArrayList" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects/>

<%
final EmailService service = sling.getService(EmailService.class);

ArrayList<String> recipients = new ArrayList<String>();
recipients.add("alessio.finamore@mindagency.com");
// Se é false usa le impostazioni SMTP di CQ5
// altrimenti usa il mio account gmail
boolean debug = true;
%>

The hello service says: "<%= service.sendHelloMessage() %>"</br>
The send service says: "<%= service.sendMessage(recipients, "<h1>Hello</h1> Email Service Body", debug) %>"</br>
