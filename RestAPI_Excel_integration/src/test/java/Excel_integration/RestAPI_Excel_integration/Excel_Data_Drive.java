package Excel_integration.RestAPI_Excel_integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Excel_Data_Drive {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// excel --> sheet --> row --> cell
		
		FileInputStream fis = new FileInputStream("C:\\Users\\91999\\OneDrive\\Desktop\\Test_Data.xlsx");
		//create a object for XSSFWorkbook class
		XSSFWorkbook workbook_excel = new XSSFWorkbook(fis);
		
		Integer no_of_sheets = workbook_excel.getNumberOfSheets();
		
		for(int i=0;i<no_of_sheets;i++)
		{
			
			/*
			 * Identify testcases column by scanning the entire 1st row 
			 * Once test cases column is identified then scan testcases column to identify the row which we required - in this case test 4
			 * after that grab the test 4 testcase row and pull all the data of that row
			 * */
			if(workbook_excel.getSheetName(i).equalsIgnoreCase("test")) {
				// got the access to the "test" sheet
				XSSFSheet sheet_name = workbook_excel.getSheetAt(i);
				// iterating through the rows in the test sheet because sheet is collection of rows
				Iterator<Row> rows = sheet_name.iterator();
				Row firstrow = rows.next();
				// once i got access to the first row iterating through the cells in the first row because row is collection of cells
				Iterator<Cell> firstrowcells = firstrow.cellIterator();
				int k =0;
				int column_required = 0;
				while(firstrowcells.hasNext()) {
					Cell value = firstrowcells.next();
					if(value.getStringCellValue().equalsIgnoreCase("Test_case")) {
						column_required = k;
						
					}
					k = k + 1;
					
				}
				System.out.println(column_required);
				
					 while(rows.hasNext()) {
					    	
					    	Row r = rows.next();
					    	if(r.getCell(column_required).getStringCellValue().equalsIgnoreCase("test4")) {
					    		
					    		Iterator<Cell> required_value = r.cellIterator();
					    		while(required_value.hasNext()) {
					    			
					    			System.out.println(required_value.next().getStringCellValue());
					    		}
					    		
					    		
					    	}
				}
			}
			
		}

	}

}
