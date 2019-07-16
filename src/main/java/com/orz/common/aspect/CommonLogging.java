package com.orz.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class CommonLogging {

	@Around("execution(* com.orz.base..*(..))") 
	public Object commonLogging(ProceedingJoinPoint joinPoint) throws Throwable { 
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object obj = joinPoint.proceed();
		
		sw.stop();
		Long runtime = sw.getTotalTimeMillis();
		
		String type = joinPoint.getSignature().getDeclaringTypeName();		
		
		log.info("runtime : " + runtime + "(ms) - " + type + "." + joinPoint.getSignature().getName() + "()");
		
		return obj;
	}
}
