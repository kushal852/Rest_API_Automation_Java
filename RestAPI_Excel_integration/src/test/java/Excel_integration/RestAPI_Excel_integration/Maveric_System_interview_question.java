package Excel_integration.RestAPI_Excel_integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Maveric_System_interview_question {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("C:\\Users\\91999\\OneDrive\\Desktop\\Test_Data.xlsx");
		//create a object for XSSFWorkbook class
		XSSFWorkbook workbook_excel = new XSSFWorkbook(fis);
		
		ArrayList<String> a = new ArrayList();
		
		Integer number_of_sheets = workbook_excel.getNumberOfSheets();
        
		String text_required = null;
		for(int i =0; i<number_of_sheets;i++) {
			
			//System.out.println(workbook_excel.getSheetName(i));
			
			
			if (workbook_excel.getSheetName(i).contentEquals("maveric_interview")) {
				System.out.println(workbook_excel.getSheetName(i));
				XSSFSheet sheet = workbook_excel.getSheetAt(i);
				
				Iterator<Row> rows = sheet.iterator();
				 while(rows.hasNext()) {
					 Row r = rows.next();
					 Iterator<Cell> cellIterator = r.cellIterator();  
					 while (cellIterator.hasNext())   
					 {  
					 Cell cell = cellIterator.next();
					 text_required = cell.getStringCellValue();
					 }
			    
			    
                  
				

	}
				
			
		}
	}
		
		 System.out.println(text_required);
		
	}
}

