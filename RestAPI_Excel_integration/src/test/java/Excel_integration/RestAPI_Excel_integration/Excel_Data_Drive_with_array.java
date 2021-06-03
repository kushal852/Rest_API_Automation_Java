package Excel_integration.RestAPI_Excel_integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Data_Drive_with_array {

	public ArrayList<String> get_data(String testcasename,String sheetname,String Xl_path) throws IOException{
		
		FileInputStream fis = new FileInputStream(Xl_path);
		//create a object for XSSFWorkbook class
		XSSFWorkbook workbook_excel = new XSSFWorkbook(fis);
		
		ArrayList<String> a = new ArrayList();
		
		Integer number_of_sheets = workbook_excel.getNumberOfSheets();
		
		for(int i =0; i<number_of_sheets;i++) {
			
			//System.out.println(workbook_excel.getSheetName(i));
			
			
			if (workbook_excel.getSheetName(i).contentEquals(sheetname)) {
				XSSFSheet sheet = workbook_excel.getSheetAt(i); //using the object get access to sheet
				
				/*
				 * after getting access to a particular sheet in the excel
				 * indentify the cell which is having test cases data
				 * from the test case cell column get the values of the data for which testcase we need
				 * 
				 * */
				Iterator<Row> rows = sheet.iterator();
			    Row firstrow = rows.next();
			    Iterator<Cell> cells = firstrow.cellIterator();// rows are collection of cells.
			    int k = 0;
			    int column_required = 0;
			    if(cells.hasNext()) {
			    	
			    	Cell value = cells.next();
			    	if(value.getStringCellValue().equalsIgnoreCase("Test_case")) {
			    		
			    		column_required = k;
			    		//System.out.println(column_required);
			    		
			    		
			    	}
			    	
			    	k = k+1;
			    }
				
			    //System.out.println(k);
			    
			    while(rows.hasNext()) {
			    	
			    	Row r = rows.next();
			    	if(r.getCell(column_required).getStringCellValue().equalsIgnoreCase(testcasename)) {
			    		
			    		Iterator<Cell> required_value = r.cellIterator();
			    		while(required_value.hasNext()) {
			    			
			    			Cell c = required_value.next();
			    			
			    			if(c.getCellType() == CellType.STRING) {
			    				a.add(c.getStringCellValue());
			    				
			    			}
			    			else {
			    				a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
			    			}
			    			
			    			
			    			
			    		}
			    		
			    		
			    	}
			    }
				
				
			}
			
			
			
		}


	return a;


	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Excel_Data_Drive_with_array data = new Excel_Data_Drive_with_array();
		ArrayList d = data.get_data("test4","test","C:\\Users\\91999\\OneDrive\\Desktop\\Test_Data.xlsx");
		
		for(int i=0;i<d.size();i++) {
			
			System.out.println(d.get(i));
		}

	}

}
