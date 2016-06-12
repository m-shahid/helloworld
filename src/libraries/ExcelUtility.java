package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static libraries.Constants.*;

public class ExcelUtility {
	
	private static String uiMapPath;
	private static String testDataPath;
	private String filePath;
	private File workingDirectory;
	public static LinkedHashMap<String, LinkedHashMap<String, String>> outerUIMap;
	public static LinkedHashMap<String, LinkedHashMap<String, String>> outerTestDataMap;
	
	public ExcelUtility(){
		
		/*File wd = new File("");
		System.out.println(wd.getAbsolutePath());*/
		workingDirectory = new File("");
		uiMapPath = workingDirectory.getAbsolutePath()+uiMapFile;
		testDataPath = workingDirectory.getAbsolutePath()+testDataFile; 
		filePath = "";
		//readExcelFile(filePath);
		readUIMap();
		readTestData();
	}
	
	public static void readExcelFile(String filePath){
		
		FileInputStream fs=null;
		
		try{
			
			fs = new FileInputStream(new File(filePath));
			//fs = new FileInputStream("D:\\NewWorkspace\\phaseLearn\\assets\\TestData\\GWC_PhaseII_TestData.xlsx");
			
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			
			int numberOfSheets = workbook.getNumberOfSheets();
			System.out.println("Number of sheets are : "+numberOfSheets);
			
			for(int i=0; i<numberOfSheets; i++){
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				System.out.println("Number of rows in sheet '"+workbook.getSheetName(i)+"' are : "+sheet.getLastRowNum()+"");
			}
			
		}catch(IOException e){
			System.out.println("Exception");
		}finally{
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static LinkedHashMap readUIMap(){
		
		FileInputStream fs=null;
		String key=null, value=null;
		String sheetName=null;
		//Using LinkedHashMap because this will iterate in the order in which the entries were put into the map
		outerUIMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		
		//ArrayList allow fast random read access, so you can grab any element in constant time
		LinkedHashMap<String, String> innerMap = new LinkedHashMap<String, String>();
		
		try{
			
			fs = new FileInputStream(new File(uiMapPath));
			
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			
			System.out.println("Number of sheets in UIMap :"+workbook.getNumberOfSheets());
			
			for(int i=0; i<workbook.getNumberOfSheets(); i++){
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				sheetName = sheet.getSheetName();
				System.out.println("Number of rows in sheet '"+workbook.getSheetName(i)+"' are : "+sheet.getLastRowNum()+"");
				Iterator rowIterator = sheet.rowIterator();
				
				while(rowIterator.hasNext()){
					
					XSSFRow row = (XSSFRow)rowIterator.next();
					XSSFCell cell;
					
					int lastCell = row.getLastCellNum();
					for(int j=0; j<lastCell; j++){
						
						cell = row.getCell(j);
						
						if(j==1){
							key = cell.getStringCellValue();
						}else if(j==2){
							value = cell.getStringCellValue();
						}
						
					}
					innerMap.put(key, value);
				}
				outerUIMap.put(sheetName, innerMap);
			}
		}catch(IOException e){
			System.out.println("Exception while reading UIMap");
		}catch(NullPointerException e){
			System.out.println("Exception while reading Test Data file. Field might be empty");
		}finally{
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outerUIMap;
	}

	public static String getLocator(String locatorName){
		
		String outerKey; 
		String innerKey;
		LinkedHashMap<String, String> outerValue;
		String innerValue=null;
		boolean foundLocator = false;
		
		try{
			
			Set setOuterUIMap = (Set)outerUIMap.entrySet();
			
			
			Iterator setIterator = setOuterUIMap.iterator();
			
			while(setIterator.hasNext()){
				
				Map.Entry mapEntry = (Map.Entry)setIterator.next();
				
				outerKey = (String)mapEntry.getKey();
				outerValue = (LinkedHashMap)mapEntry.getValue();
				
				Set setOuterValue = (Set)outerValue.entrySet();
				Iterator setOuterValueIterator = setOuterValue.iterator();
				
				while(setOuterValueIterator.hasNext()){
					
					Map.Entry mapOuterValueEntry = (Map.Entry)setOuterValueIterator.next();
					
					innerKey = (String)mapOuterValueEntry.getKey();
					
					if(innerKey.equalsIgnoreCase(locatorName)){
						innerValue = (String)mapOuterValueEntry.getValue();
						foundLocator = true;
						break;
					}
				}
				
				if(foundLocator == true){
					break;
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception while retriving web locator");
		}
		return innerValue;
	}

	public static LinkedHashMap readTestData(){
		
		FileInputStream fs;
		LinkedHashMap<String, String> innerMap = new LinkedHashMap<String, String>();
		outerTestDataMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		
		String sheetName;
		String key=null;
		String value=null;
		
		try{
			fs = new FileInputStream(new File(testDataPath));
			
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			
			for(int i=0; i<workbook.getNumberOfSheets(); i++){
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				sheetName = workbook.getSheetName(i);
				//System.out.println("Working sheet is : "+sheetName);
				
				XSSFRow row = sheet.getRow(0);
				int lastCell = row.getLastCellNum();
				//System.out.println("Last cell in above sheet is : "+lastCell);
				
				for(int j=0; j<lastCell; j++){
					
					//System.out.println("Last row is : "+sheet.getLastRowNum());
					
					//giving hard core value 1 in for loop instead of last row
					for(int k=0; k<=1; k++){
						
						row = sheet.getRow(k);
						XSSFCell cell = row.getCell(j);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						
						//Now its considering only first row as Key and second row as Value.
						if(k==0){
							key = cell.getStringCellValue();
						}else if(k==1){
							value = cell.getStringCellValue();
						}
					}
					
						innerMap.put(key, value);
					
				}
				
				outerTestDataMap.put(sheetName, innerMap);
				innerMap = new LinkedHashMap<String, String>();
			}
		}catch(IOException e){
			System.out.println("Exception while reading Test data file");
		}catch(NullPointerException e){
			System.out.println("Exception while reading Test Data file. Field might be empty");
		}
		
		return outerTestDataMap;
	}

	public String get(String testData, String testSheet){
		
		String outerKey;
		String innerKey;
		LinkedHashMap<String, String> outerValue;
		String innerValue = null;
		
		try{
			Set testDataSet = (Set)outerTestDataMap.entrySet();
			
			Iterator testDataIterator = testDataSet.iterator();
			
			while(testDataIterator.hasNext()){
				
				Map.Entry mapEntry = (Map.Entry)testDataIterator.next();
				outerKey = (String)mapEntry.getKey();
				
				if(testSheet.equalsIgnoreCase(outerKey)){
					
					outerValue = (LinkedHashMap<String, String>)mapEntry.getValue();
					
					Set innerTestDataSet = (Set)outerValue.entrySet();
					Iterator innerTestDataIterator = innerTestDataSet.iterator();
					
					while(innerTestDataIterator.hasNext()){
						
						Map.Entry innerMapEntry = (Map.Entry)innerTestDataIterator.next();
						innerKey = (String)innerMapEntry.getKey();
						
						if(innerKey.equalsIgnoreCase(testData)){
							innerValue = (String)innerMapEntry.getValue();
							break;
						}
					}
					break;
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception while retriving test data. DEBUG get() for more information");
			//e.printStackTrace();
		}
		return innerValue;
	}
}
