package com.model2.mvc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	
	public LogAspect() {
		// TODO Auto-generated constructor stub
	}
	
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable{
		
//		System.out.println("This is Log Around Invoke");
//		System.out.println("[Before Around] target & target's Method " +joinPoint.getClass().getName()+"."+joinPoint.getSignature());
//		System.out.println("[Before Around] method parameter" +joinPoint.getArgs()[0]);
		
		Object obj = joinPoint.proceed();
		
		System.out.println("[After Around] Return Value " +obj);
		
		return obj;
	}
	
}
