/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.al76.workflows;

import java.util.ArrayList;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.HtmlEmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

 /**
 * @scr.component metatype="false" immediate="true"
 * @scr.service interface="com.day.cq.workflow.exec.WorkflowProcess"
 *
 * @scr.property name="service.description" value=
 *               "This is the Protection workflow step for test"
 * @scr.property name="service.vendor" value="Mind"
 * @scr.property name="process.label"
 *               value="Protection MyWorkflowProcess Bundle"
 */

public class MyWorkflowProcess implements WorkflowProcess {

    private static final Logger log = LoggerFactory
            .getLogger(MyWorkflowProcess.class);


 	/** @scr.reference policy="dynamic" */
 	private MessageGateway<HtmlEmail> messageGateway;

 	/** @scr.reference policy="static" */
 	private MessageGatewayService messageGatewayService;

    public void execute(WorkItem item, WorkflowSession session,
			MetaDataMap metaData) throws WorkflowException {
        log.info(" Stoi eseguendo il Workfloew MARK : item: " + item + " session:" + session);
     }  

    }
