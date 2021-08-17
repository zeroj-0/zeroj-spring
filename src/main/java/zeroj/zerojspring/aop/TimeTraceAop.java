package zeroj.zerojspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    //공통 관심 사항을 어디에다가 적용해줄 것인지 타겟팅해주는 것(문법 따로 있음)
    //지금 적어 놓은 것은 패키지 하위 모두 해당된다는 것을 뜻함
    //execution(*패키지 이름..클래스명(parameter))
    @Around("execution(* zeroj.zerojspring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable{
        //proceed() : 다음 메소드로 실행되는 메소드
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
