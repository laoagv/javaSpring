package aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* aop.UniLibrary.*(..))")
        private void allMethodsUniLibrary(){}
    @Pointcut("execution(public void aop.UniLibrary.returnMagazine())")
        private void onlyReturnMagazine(){}
    @Pointcut("allMethodsUniLibrary() && !onlyReturnMagazine()")
        private void allMethodsExceptReturnMagazine(){}

    @Pointcut("execution(public void aop.UniLibrary.getBook(aop.Book))")
        private void getBookWithObjectBook(){}
    @Before("getBookWithObjectBook()")
    private void beforeGetBookObjectAdvice(){
        System.out.println("beforeGetBookObjectAdvice: writing log 72# book with object");
    }
    @Before("allMethodsExceptReturnMagazine()")
    private void beforeAllMethodsExceptReturnMagazineAdvice(){
        System.out.println("beforeAllMethodsExceptReturnMagazineAdvice: writing log 10#");
    }
    @Pointcut("execution(* aop.UniLibrary.get*())")
        private void allGetsMethodsUniLibrary(){}

    @Before("allGetsMethodsUniLibrary()")
    private void beforeGatLoggingAdvice(){
        System.out.println("beforeGatLoggingAdvice: writing log 1#");
    }

    @Pointcut("execution(* aop.UniLibrary.return*())")
        private void allReturnMethodsUniLibrary(){}
    @Before("allReturnMethodsUniLibrary()")
    private void beforeReturnLoggingAdvice(){
        System.out.println("beforeGatLoggingAdvice: writing log 2#");
    }

    @Pointcut("allGetsMethodsUniLibrary() || allReturnMethodsUniLibrary()")
        private void allGetAndReturnMethodsUniLibrary(){}


    @Before("allGetAndReturnMethodsUniLibrary()")
    public void beforeGetAndReturnLoggingAdvice(){
        System.out.println("beforeGetAndReturnLoggingAdvice: writing log 3#");
    }
}
