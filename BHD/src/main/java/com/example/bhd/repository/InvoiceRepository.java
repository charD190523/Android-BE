package com.example.bhd.repository;

import com.example.bhd.dto.response.InvoiceCommonDTO;
import com.example.bhd.dto.response.InvoiceDetailDTO;
import com.example.bhd.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("SELECT DISTINCT new com.example.bhd.dto.response.InvoiceCommonDTO(" +
            "    i.id," +
            "    m.movieName, " +
            "    s.showDate, " +
            "    s.startTime, " +
            "    i.totalPrice " +
            ") " +
            "FROM Ticket t " +
            "JOIN t.seatDetail sd " +
            "JOIN sd.showtime s " +
            "JOIN s.movie m " +
            "JOIN t.invoice i " +
            "WHERE sd.userId = :userId " +
            "AND s.showDate IS NOT NULL " +
            "AND s.startTime IS NOT NULL")
    List<InvoiceCommonDTO> findAllInvoiceCommon(@Param("userId") Integer userId);
    // Custom query methods can be defined here if needed

    @Query("SELECT new com.example.bhd.dto.response.InvoiceDetailDTO(" +
            "    m.movieName, " +
            "    s.showDate, " +
            "    s.startTime, " +
            "    i.totalPrice, " +
            "    SUM(t.price) " +
            ") " +
            "FROM Invoice i " +
            "JOIN i.tickets t " +
            "JOIN t.seatDetail sd " +
            "JOIN sd.showtime s " +
            "JOIN s.movie m " +
            "LEFT JOIN i.foodDetails d " +
            "LEFT JOIN d.food f " +
            "WHERE i.id = :id " +
            "AND s.showDate IS NOT NULL " +
            "AND s.startTime IS NOT NULL " +
            "GROUP BY m.movieName, s.showDate, s.startTime, i.totalPrice")
    InvoiceDetailDTO findInvoiceDetailById(Integer id);
}
