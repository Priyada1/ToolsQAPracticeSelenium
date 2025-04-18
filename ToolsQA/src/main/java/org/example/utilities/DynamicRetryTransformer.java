package org.example.utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DynamicRetryTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
      //  annotation.setRetryAnalyzer(RetryAnalyzer.class);

        if (testMethod != null && testMethod.getName().contains("flaky")) {
            annotation.setRetryAnalyzer(FlakyRetryAnalyzer.class); // Use FlakyRetryAnalyzer for flaky tests it's count =6
        } else {
            annotation.setRetryAnalyzer(RetryAnalyzer.class); // Use RetryAnalyzer for other tests it's count =2
        }
        /*
        The DynamicRetryTransformer sets the retryAnalyzer based on the test method's name.
        Tests with "flaky" in their name use FlakyRetryAnalyzer with a higher retry count.
        Other tests use RetryAnalyzer with a standard retry count.
         */
    }
}