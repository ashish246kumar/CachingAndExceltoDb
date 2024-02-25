package com.caching.caching.service.impl;

import java.util.List;
import java.io.File;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.caching.caching.entity.Invoice;
import com.caching.caching.helper.Helper;
import com.caching.caching.repository.InvoiceRepository;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ExcelDataServiceImpl {

	@Autowired
	private InvoiceRepository ivoiceRepository;
	
	public void save(MultipartFile file) {
		try {
			List<Invoice> l=Helper.getExcelDataAsList(file.getInputStream());
			ivoiceRepository.saveAll(l);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public List<Invoice>getAllInvoice(){
		return ivoiceRepository.findAll();
	}
	

}
