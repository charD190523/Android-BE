package com.example.bhd.restful;

import com.example.bhd.dto.FoodDetailDTO;
import com.example.bhd.dto.response.InvoiceCommonDTO;
import com.example.bhd.dto.response.InvoiceDetailDTO;
import com.example.bhd.dto.response.InvoiceResponse;
import com.example.bhd.entity.Invoice;
import com.example.bhd.factory.GeneralResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/invoice")
public interface InvoiceOperations {

    @PostMapping("/create")
    ResponseEntity<GeneralResponse<InvoiceResponse>> createInvoice(@RequestBody List<FoodDetailDTO> foodDetailList, HttpSession session);

    @GetMapping("/save")
    ResponseEntity<GeneralResponse<String>> saveInvoice(HttpSession session);

    @GetMapping("/cancel")
    ResponseEntity<GeneralResponse<String>> cancelBooking(HttpSession session);

    @GetMapping("/getAll")
    ResponseEntity<GeneralResponse<List<InvoiceCommonDTO>>> getAllInvoice(HttpSession session);

    @GetMapping("/getDetail")
    ResponseEntity<GeneralResponse<InvoiceDetailDTO>> getInvoiceDetail(Integer id);
}

