package CzytanieExcela;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

// "C:/users/xe050991252/Desktop/NIPy 3.2017 GTH.xls"
public interface CzytanieExcela {
	
	

//	static File nipy = new File("C:/NIP/NIPy.xls");
	
	public default void czytajExcela() throws FileNotFoundException {
		PrintStream excel = new PrintStream(new FileOutputStream(System.getenv("ProgramFiles(X86)")+"/NIPy/output.txt"));
		System.setOut(excel);
		


	try {
	    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(Dominika.plikznipami));
	    HSSFWorkbook wb = new HSSFWorkbook(fs);
	    HSSFSheet sheet = wb.getSheetAt(0);
	    HSSFRow row;
	    HSSFCell cell;
	    
	    

	    int rows; // No of rows
	    rows = sheet.getPhysicalNumberOfRows();
		

	    int cols = 0; // No of columns
	    int tmp = 0;

	    // This trick ensures that we get the data properly even if it doesn't start from first few rows
	    for(int i = 0; i < 10 || i < rows; i++) {
	        row = sheet.getRow(i);
	        if(row != null) {
	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	            if(tmp > cols) cols = tmp;
	        }
	    }

	    for(int r = 1; r < rows; r++) {
	        row = sheet.getRow(r);
	        if(row != null) {
	            for(int c = 2; c < 3 ;c++) {
	                cell = row.getCell((short)c);
	                if(cell != null) {
	                	
	                	System.out.println(cell);
	                	
	                	
	                }
	              
	            }
	            
	        }
	    
	    }
	   
    	
	} catch(Exception ioe) {
	    ioe.printStackTrace();
	}}}
