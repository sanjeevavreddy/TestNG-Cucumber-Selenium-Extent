package utils;

import com.aventstack.extentreports.Status;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import java.util.HashMap;
import java.util.Map;

public class ExtentCucumberAdapter implements ConcurrentEventListener {

    private static final Map<String, String> scenarioStep = new HashMap<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
    }

    private void handleStepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep step) {
            String stepText = step.getStep().getKeyword() + step.getStep().getText();
            String status = event.getResult().getStatus().name();

            // Get the ExtentTest for current thread
            var test = ExtentTestManager.getTest();

            switch (event.getResult().getStatus()) {
                case PASSED -> test.pass(stepText);
                case FAILED -> {
                    Throwable error = event.getResult().getError();
                    test.fail(stepText + " ❌\n" + (error != null ? error.getMessage() : ""));
                }
                case SKIPPED -> test.skip(stepText);
                default -> test.info(stepText);
            }
        }
    }
}
