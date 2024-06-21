package utilities;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//Apache POI classes for working with Excel files
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//Represents an Excel sheet for .xlsx files
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader
{
	// Static variable to store key-value pairs, can be accessed without an object.
	public static HashMap<String, String> storeValues = new HashMap<>();

	@SuppressWarnings("incomplete-switch")
	// Method 'data' returns a List of HashMaps, each representing a row in the Excel sheet
	public static List<HashMap<String, String>> data(String filepath, String sheetName) {
		
		// List to store HashMaps, each HashMap represents a row in the Excel sheet.
        List<HashMap<String, String>> mydata = new ArrayList<>();
		
		try {
			FileInputStream fs = new FileInputStream(filepath);
			try (XSSFWorkbook workbook = new XSSFWorkbook(fs)) {
				// Get the XSSFSheet object for the given 'sheetName'
				XSSFSheet sheet = workbook.getSheet(sheetName);
				// Get the header row, assuming it's the first row in the sheet
				Row HeaderRow = sheet.getRow(0);
				// Iterate over the rows of the sheet starting from the second row.
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) 
					{
					Row currentRow = sheet.getRow(i);
					 // HashMap to store the current row's data
					HashMap<String, String> currentHash = new HashMap<String, String>();
					// Iterate over the cells of the current row.
					for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) 
						{
						Cell currentCell = currentRow.getCell(j);
						switch (currentCell.getCellType()) 
							{
								case STRING:
									 // Put the cell's value in 'currentHash', key is the header cell's value.
									currentHash.put(HeaderRow.getCell(j).getStringCellValue(),currentCell.getStringCellValue());
								break;
							}
						}
					mydata.add(currentHash);
					}
			}
			// Close the FileInputStream.
			fs.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List of HashMaps
		return mydata;
	}
}
