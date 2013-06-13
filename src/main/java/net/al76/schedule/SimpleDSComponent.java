/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package net.al76.schedule;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Workflow;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.mail.MessagingException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.jcr.api.SlingRepository;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just a simple DS Component
 */
@Component(metatype = true)
@Service
public class SimpleDSComponent implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BundleContext bundleContext;

    @Reference
    private Scheduler scheduler;
    @Reference
    private SlingRepository repository;
    @Reference
    private WorkflowService workflowService;
    String jobName = "case2";

/**
    @Reference(policy = ReferencePolicy.DYNAMIC)
 	private MessageGateway<HtmlEmail> messageGateway;
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY, policy = ReferencePolicy.STATIC)
    protected MessageGatewayService mailService;
**/

    @Reference
    MessageGatewayService messageGatewayService;

    public String sayHello() {
        // ERROR scheduler is null ->> run();
        return "HeeloDS";
    }

    public void run() {
        String schedulingExpression = "0/10 * * * * ?";

        Map<String, Serializable> config1 =
                new HashMap<String, Serializable>();
        boolean canRunConcurrently = true;

        final Runnable job1;
        job1 = new Runnable() {
            public void run() {
                Session adminSession = null;
                try {
                    String name = "Workjflow Provcess SendMail";
                    logger.info("Executing job: " + name);

                    adminSession = repository.loginAdministrative(null);
                  /* 
                    MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
                    if(messageGateway == null){
                        throw new MessagingException("Unable to retrieve message gateway for HTMLEmails, " + 
                                                     "please configure Gateway in OSGi Console");
                    }else{
                        logger.info("MARK Message gateway for HTMLEmails is OK");
                    }
                    */        
                    // starting a workflow
                    /*******
                    WorkflowSession wfSession = workflowService.getWorkflowSession(adminSession);
                    WorkflowModel model = wfSession.getModel("/etc/workflow/models/prova-al75-models/jcr:content/model");
                    WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", "/content/mytest/apriconto");
                    wfSession.startWorkflow(model, wfData);
                    ********/

                    /* querying and managing a workflow
                     Workflow workflows[] workflows = wfSession.getWorkflows("RUNNING");
                     Workflow workflow= wfSession.getWorkflow(id);
                     wfSession.suspendWorkflow(workflow);
                     wfSession.resumeWorkflow(workflow);
                     wfSession.terminateWorkflow(workflow);
                } catch (WorkflowException ex) {
                    ex.printStackTrace();
                     **/
                } catch (RepositoryException ex) {
                    ex.printStackTrace();
                } finally{
                    adminSession.logout();
                }
            }
        };


        try {
            this.scheduler.addJob(jobName,
                    job1,
                    config1,
                    schedulingExpression,
                    canRunConcurrently);
        } catch (Exception e) {
            logger.error("error schedule: ");
            e.printStackTrace();
            job1.run();
        }

        logger.info("Schedule Service TEST Run: " + jobName);
    }

    protected void activate(ComponentContext ctx) {

        this.bundleContext = ctx.getBundleContext();
        logger.info("Schedule Service activate");
        run();

    }

    protected void deactivate(ComponentContext componentContext) {
        logger.info("Schedule Service deactivate");

        this.scheduler.removeJob(jobName);

        this.bundleContext = null;
    }
}
