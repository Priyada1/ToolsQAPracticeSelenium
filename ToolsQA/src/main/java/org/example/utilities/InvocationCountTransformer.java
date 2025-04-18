package org.example.utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class InvocationCountTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (testMethod != null && testMethod.getName().contains("Performance")) {
            annotation.setInvocationCount(20); // Run performance tests 20 times
        }
        /*
           You can use IAnnotationTransformer to set the invocationCount of the @Test annotation, which effectively simulates retries for
            certain scenarios. However, this is not the same as a true retry mechanism, as it runs the test multiple times regardless of
            whether it fails.
         */
    }
}