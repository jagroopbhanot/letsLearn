package aop.logging;
import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
@Aspect
@Component

@ConditionalOnExpression("${aspect.enabled:true}")
public class ElapsedTimeAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(aop.logging.TrackElapsedTime)")
    public Object elapsedTime(ProceedingJoinPoint point) throws Throwable {
        long executionStartTime = Instant.now().toEpochMilli();
        System.out.println("Execution of " + point.getSignature().getName() + " started at : " + Instant.now().toString());
        Object object = point.proceed();
        long executionEndTime = Instant.now().toEpochMilli();
        System.out.println("Execution of " + point.getSignature().getName() + " ended at : " + Instant.now().toString());
        System.out.println("Time taken to execute " + point.getSignature().getName() + " is : " + (executionEndTime - executionStartTime) + "ms");
        return object;
    }
}