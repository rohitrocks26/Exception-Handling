package com.keystone.common.aspect;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class LoggingAspect {
	private Logger logger;

	public LoggingAspect() {
		logger = LoggerFactory.getLogger(getClass());
		logger.info("HEY");
	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}

	@Pointcut("execution(* com.ccsp.accums.ledger.controller.*Controller.*(..))")
	public void methodPointcut() {
	}

	@Around("requestMapping() && methodPointcut()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		Calendar start = null;
		//StopWatch sw = new StopWatch();
		String name = pjp.getSignature().getName();
		try {
			System.out.println("ASPECT!" +name);
			 start = Calendar.getInstance();
			logger.info( "method" + name + "started at" +start.getTime());
			
		//	sw.start();
			
			return pjp.proceed();
					
		} finally {
			//sw.stop();
			Calendar stop = Calendar.getInstance();			
			logger.info( "method" + name + "stopped at" +stop.getTime());
			long startMillis = start.getTimeInMillis();
			long stopMillis = stop.getTimeInMillis();
			
			double durationMillis = stopMillis - startMillis;
			logger.info( "method " + name  + "took" + (durationMillis / 1000.0) + " seconds" );
			
		}
	}
}
