/*
 * #%L
 * TestNG System.out printer
 * %%
 * Copyright (C) 2014 Mats Henrikson
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.little_fluffy_clouds.testng;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * A very simple class that simply prints the class and method names of TestNG tests being run,
 * together with the elapsed time in milliseconds and the test outcome.
 */
public class SystemOutTestPrinter implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(final IInvokedMethod method, final ITestResult testResult) { }

    @Override
    public void afterInvocation(final IInvokedMethod m, final ITestResult r) {
        if (!m.isTestMethod()) return;

        final ITestNGMethod method = m.getTestMethod();
        final String testName = method.getTestClass().getRealClass().getSimpleName();
        final ITestResult testResult = m.getTestResult();
        final long elapsed = testResult.getEndMillis() - testResult.getStartMillis();
        System.out.printf("%s.%s(): %s, %d millis\n",
                testName, method.getMethodName(), Status.forCode(testResult.getStatus()), elapsed);
    }

    /**
     * {@link org.testng.ITestResult}
     */
    @SuppressWarnings({"MagicNumber", "UnusedDeclaration"})
    private enum Status {
        OK(1),
        FAIL(2),
        SKIP(3),
        OK_PCT_FAIL(4),
        STARTED(16),
        UNKNOWN(-1);

        final int code;

        Status(final int code) { this.code = code; }

        static Status forCode(final int c) {
            for (final Status s : Status.values()) {
                if (s.code == c) return s;
            }
            return UNKNOWN;
        }
    }
}
