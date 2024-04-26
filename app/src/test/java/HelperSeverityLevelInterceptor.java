import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Severity;

public class HelperSeverityLevelInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> result = new ArrayList<>();
        for (IMethodInstance method : methods) {
            Severity severity = method.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Severity.class);
            if (severity != null && (severity.value().equals(SeverityLevel.BLOCKER) || severity.value().equals(SeverityLevel.CRITICAL) || severity.value().equals(SeverityLevel.NORMAL))) {
                result.add(method);
            }
        }
        return result;
    }

}

