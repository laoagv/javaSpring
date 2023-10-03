package aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewLoggingAspect {
    @Around("execution(public String aop.UniLibrary.returnBook())")
    private Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку пытаются вернуть книгу");
        Object targetRes = null;
        try {
            targetRes = proceedingJoinPoint.proceed();
        }
        catch (Exception e){
            System.out.println("aroundReturnBookLoggingAdvice: исключение - "+ e);
            targetRes = "Неизвестное название книги";
        }
        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку успешно вернули книгу");
        return targetRes;
    }
}
