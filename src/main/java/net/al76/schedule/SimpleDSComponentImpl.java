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


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.scheduler.Scheduler;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just a simple DS Component
 */
@Component(metatype=true)
@Service
public class SimpleDSComponent implements Runnable {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private BundleContext bundleContext;
    @Reference
    private Scheduler scheduler;
    
    String jobName = "case2";
    
    public String sayHello() {
        return"HeeloDS";
    }
    public void run() {
                String schedulingExpression = "0/2 * * * * ?";
                 
                Map<String, Serializable> config1 = 
                        new HashMap<String, Serializable>();
                boolean canRunConcurrently = true;
                
                final Runnable job1 = new Runnable() {
                    public void run() {
                        String name = "job5";
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

