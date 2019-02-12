package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.TestBase;

public class TestUtils extends TestBase{
	
	public static String getScreenshot(String testCaseName) throws IOException{
		TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            	Date date = new Date();
            	System.out.println(dateFormat.format(date));
                
                String fileWithPath= System.getProperty("user.dir")+"//screenshots//"+testCaseName+"_"+dateFormat.format(date)+".png";
                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);
                return fileWithPath;
                
                
	}
	
	public static String[][] readData() {

		String[][] data = null;

		try {
			// File file = new File("./Data/Datasheet.xlsx");
			File file = new File(System.getProperty("user.dir") + "\\Data\\Datasheet.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			// get the number of rows
			int rowCount = sheet.getLastRowNum();
			System.out.println("Row count is " + rowCount);

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			System.out.println("Column count is " + columnCount);
			data = new String[rowCount][columnCount];

			
			// loop through the rows
			for (int i = 1; i <= rowCount; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the
						// columns
						try {
							String cellValue = "";
							try {
								cellValue = row.getCell(j).getStringCellValue();
							} catch (NullPointerException e) {

							}

							data[i - 1][j] = cellValue	; // add to the data array
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			workbook.close();
			fis.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}
	

}
