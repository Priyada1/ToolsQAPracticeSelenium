package SpecificTestcases;

import org.example.utilities.ExcelUtilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ReadDataFromExcelUsingDataProvider {

    Object[][] testData;
    @DataProvider(name = "data-provider",parallel = true)
    public Object[][] fetchExcelData() throws IOException {

        String path ="/Users/chakrapanipriyadarshi/Desktop/Web-Automation-March-2025/ToolsQAPracticeSelenium/ToolsQA/src/test/test-resources/TestData1.xlsx";
        String sheetName = "Team";
        testData=ExcelUtilities.getExcelData(path,sheetName);
        return testData;
    }



    @Test(dataProvider = "data-provider", description = "Read data from excel using data provider")
    public void printExcelTestData(String col1, String col2, String col3, String col4){
        System.out.println("Taking data from data provider: ");
        System.out.println(Thread.currentThread().getName() + ">>>>>>>>"+col1 + " | " + col2 + " | " + col3 + " | " + col4);
       // System.out.println(Thread.currentThread().getName() + ">>>>>>>ID: " + testData[0][0] + " Name: " + testData[0][1]);
    }


    @Test(description = "Read data from excel without data provider")
    public void readDataFromExcelWithoutDataProvider() throws IOException {
        String path ="/Users/chakrapanipriyadarshi/Desktop/Web-Automation-March-2025/ToolsQAPracticeSelenium/ToolsQA/src/test/test-resources/TestData1.xlsx";
        String sheetName = "Team";
        Object[][] data=ExcelUtilities.getExcelData(path,sheetName);

        // System.out.println("Taking data from excel without data provider: "+data[0][0]+" | "+data[0][1]+" | "+data[0][2]+" | "+data[0][3]);

         for(int i=0;i<data.length;i++)
         {
             for(int j=0;j<data[i].length;j++)
             {
                    System.out.print(data[i][j]+" ||| ");
             }
             System.out.println();
         }

//        for (Object[] datum : data) {
//            for (Object o : datum) {
//                System.out.println(o + " | ");
//            }
//        }
    }


}