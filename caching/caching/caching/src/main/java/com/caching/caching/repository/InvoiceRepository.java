package com.caching.caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caching.caching.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

	
}
