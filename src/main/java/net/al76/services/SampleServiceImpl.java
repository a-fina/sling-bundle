package net.al76.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
import org.apache.sling.commons.scheduler.Scheduler;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/
/**
 * @scr.component immediate="true" metatype="false"
 * 
 * @scr.service interface="SampleServiceImpl"
 */
public class SampleServiceImpl implements Runnable {
        
        private Logger logger = LoggerFactory.getLogger(this.getClass());
       
        private BundleContext bundleContext;
        @Reference
        private Scheduler scheduler;
	
        String jobName = "caseSample";
        
        public String sayHello() {
                String schedulingExpression = "0/2 * * * * ?";
                 
                Map<String, Serializable> config1 = 
                        new HashMap<String, Serializable>();
                boolean canRunConcurrently = true;
                
                final Runnable job1 = new Runnable() {
                    public void run() {
                        String name = "sampleJob";
                        logger.info("Executing job: " + name);
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
                
                logger.info("Schedule Service TEST Run: " + jobName );
		return "hello";
	}

        protected void activate(ComponentContext ctx) {
            logger.info("Sample Service deactivate");
            this.bundleContext = ctx.getBundleContext();
            sayHello();
        }
	protected void deactivate(ComponentContext componentContext) {
            logger.info("Sample Service deactivate");

            this.scheduler.removeJob(jobName);

            this.bundleContext = null;      
        }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
