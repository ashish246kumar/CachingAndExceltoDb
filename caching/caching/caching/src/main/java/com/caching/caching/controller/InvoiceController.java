package com.caching.caching.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.caching.caching.entity.Invoice;
import com.caching.caching.helper.Helper;
import com.caching.caching.repository.InvoiceRepository;
import com.caching.caching.service.impl.ExcelDataServiceImpl;

@RestController
@CrossOrigin("*")
public class InvoiceController {

	@Autowired
	private ExcelDataServiceImpl excelDataServiceImpl;
	
	 @PostMapping("/upload")
	    public ResponseEntity<?> uploadInvoiceData(@RequestParam("file")MultipartFile file){
		 if(Helper.checkExcelFormat(file))
		 {
			 excelDataServiceImpl.save(file);
			 return ResponseEntity.ok(Map.of("Message" , " Invoice data uploaded and saved to database successfully"));
		 }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file");
	 }
	 @GetMapping("/getInvoice")
	    public ResponseEntity<List<Invoice>> getInvoice(){
	        return new ResponseEntity<>(excelDataServiceImpl.getAllInvoice(), HttpStatus.FOUND);
	    }
}
