package com.example.bhd.dto;

import com.example.bhd.entity.SeatDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDetailListDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<SeatDetail> seatDetailList;

    public void addSeatDetail(SeatDetail seatDetail) {
        if (seatDetailList == null) {
            seatDetailList = new ArrayList<>();
        }
        seatDetailList.add(seatDetail);
    }

    public List<Integer> getSeatDetailIdList() {
        List<Integer> seatDetailIdList = new ArrayList<>();
        if (seatDetailList != null) {
            for (SeatDetail seatDetail : seatDetailList) {
                seatDetailIdList.add(seatDetail.getId());
            }
        }
        return seatDetailIdList;
    }

    public void removeSeatDetail(SeatDetail seatDetail) {
        if (seatDetailList != null) {
            seatDetailList.remove(seatDetail);
        }
    }

}
