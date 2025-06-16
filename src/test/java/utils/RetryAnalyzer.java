package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int attempt = 0;
    private static final int maxRetry = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxRetry) {
            attempt++;
            System.out.println("Retrying: " + result.getName() + " | Attempt: " + (attempt + 1));
            return true;
        }
        return false;
    }
}
