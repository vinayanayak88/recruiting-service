package com.heavenhr.recruiting.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.heavenhr.recruiting.constant.Constant;
import com.heavenhr.recruiting.vo.Application;

/** LoggingAspect triggers a notification when there is a change in the application status
 * @author Vinaya Nayak
 * @date 12-Dec-2017
 * LoggingAspect.java
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspect {
	

	/**
	 * Gets triggered when there is a update to an application status as well as when an offer gets created newly
	 * @param joinPoint
	 * @param returnValue
	 */
    @AfterReturning( pointcut = "execution(* com.heavenhr.recruiting.service.handler.JobApplicationServiceHandler.updateStatus(..)) || execution(* com.heavenhr.recruiting.service.handler.JobApplicationServiceHandler.createApplication(..))", returning = "returnValue")
    public void logAfterStatusUpdate(JoinPoint joinPoint, Object returnValue)
    {
        System.out.println(LoggingAspect.class + "Triggering LoggingAspect.logAfterStatusUpdate()");
        if(null != returnValue) {
        	Application app = (Application)returnValue;
        	String status = app.getApplcationStatus();
        	switch(status) {
        	case Constant.APPLIED : System.out.println(LoggingAspect.class + "Triggering email for the applicant and also the HR");
        	break;
        	case Constant.INVITED : System.out.println(LoggingAspect.class + "Schedule an appointment for interview and email the schedule to the applicant");
        	break;
        	case Constant.REJECTED : System.out.println(LoggingAspect.class + "Triggering email for the applicant and update the company database");
        	break;
        	case Constant.HIRED : System.out.println(LoggingAspect.class + "Triggering email for the hiring manager");
        	break;
        	}
        }
    }

}
