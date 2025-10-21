package SpecificTestcases;

import org.testng.annotations.Test;

public class GroupingTestCases {


    @Test(groups = {"sanity"})
    public void testSanity1() {
        // Test case for sanity testing
        System.out.println("Running sanity test 1");
    }

    @Test(groups = {"sanity"})
    public void testSanity2() {
        // Test case for sanity testing
        System.out.println("Running sanity test 2");
    }

    @Test(groups = {"regression"})
    public void testRegression1() {
        // Test case for regression testing
        System.out.println("Running regression test 1");
    }

    @Test(groups = {"regression", "sanity"}) //A test can belong to multiple groups
    public void testRegression2() {
        // Test case for regression testing
        System.out.println("Running sanity/regression test 2");
    }

    @Test
    public void testOther() {
        //Test case that does not belong to any group
        System.out.println("Running a test case which is not in sanity or regression group");
    }
}