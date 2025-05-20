package com.example.bhd.service;

import com.example.bhd.dto.FoodDetailDTO;
import com.example.bhd.dto.response.InvoiceResponse;
import com.example.bhd.entity.FoodDetail;
import com.example.bhd.entity.Invoice;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(List<FoodDetailDTO> foodDetailList, HttpSession session);

    void saveInvoice(HttpSession session);
}
