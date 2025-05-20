package com.example.bhd.repository;

import com.example.bhd.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    // Custom query methods can be defined here if needed
}
