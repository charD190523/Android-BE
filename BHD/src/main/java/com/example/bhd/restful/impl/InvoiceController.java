package com.example.bhd.restful.impl;

import com.example.bhd.dto.FoodDetailDTO;
import com.example.bhd.dto.response.InvoiceResponse;
import com.example.bhd.entity.Invoice;
import com.example.bhd.factory.GeneralResponse;
import com.example.bhd.factory.ResponseFactory;
import com.example.bhd.restful.InvoiceOperations;
import com.example.bhd.service.InvoiceService;
import com.example.bhd.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Service
@RestController
@AllArgsConstructor
public class InvoiceController implements InvoiceOperations {

    private final InvoiceService invoiceService;

    private final SessionService sessionService;

    @Override
    public ResponseEntity<GeneralResponse<InvoiceResponse>> createInvoice(@RequestBody List<FoodDetailDTO> foodDetailList, HttpSession session) {
        log.info("Creating invoice with food details: {}", foodDetailList);
        return ResponseEntity.ok(ResponseFactory.success(invoiceService.createInvoice(foodDetailList,session )));
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> saveInvoice(HttpSession session) {
        try {
            invoiceService.saveInvoice(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseFactory.fail("Failed to save invoice: " + e.getMessage()));
        }
        return ResponseEntity.ok(ResponseFactory.success("Invoice saved successfully"));
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> cancelBooking(HttpSession session) {
        try {
            sessionService.removeAllAttributes(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseFactory.fail("Failed to cancel booking: " + e.getMessage()));
        }
        return ResponseEntity.ok(ResponseFactory.success("Booking cancelled successfully"));
    }
}
