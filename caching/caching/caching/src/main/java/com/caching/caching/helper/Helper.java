package com.caching.caching.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.caching.caching.entity.Invoice;
import java.util.*;

public class Helper {

	public static boolean checkExcelFormat(MultipartFile file) {
		
		String contentType=file.getContentType();
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}
		else {
			return false;
		}
	}
	public static  List<Invoice> getExcelDataAsList(InputStream is) {
		
		List<Invoice>list=new ArrayList<>();
		try {
			  XSSFWorkbook workbook = new XSSFWorkbook(is);
			  XSSFSheet sheet = workbook.getSheet("Sheet1");
			  int rowNumber=0;
			  Iterator<Row>iterator=sheet.iterator();
			  while(iterator.hasNext()) {
				  Row row=iterator.next();
				  if(rowNumber==0) {
					  rowNumber++;
					  continue;
				  }
				  Iterator<Cell>cells=row.iterator();
				  int cid=0;
				  Invoice inv = new Invoice();
				  while(cells.hasNext()) {
					  Cell cell=cells.next();
					 
					  switch (cid){
					  
					  case 0:
						  inv.setName(cell.getStringCellValue());
						  System.out.println(cell.getStringCellValue()+"******");
						  break;
					  case 1:
						  System.out.println(cell.getNumericCellValue()+"******");
						  inv.setAmount((double)cell.getNumericCellValue());
						  break;
					  case 2:
						  inv.setNumber(Double.toString(cell.getNumericCellValue()));
						  break;
					  case 3:
						  inv.setReceivedDate(Double.toString(cell.getNumericCellValue()));
						  break;
					  default:
							break;
							
						  
					  }
					  cid++;
				  }
				  list.add(inv);
			  }
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
