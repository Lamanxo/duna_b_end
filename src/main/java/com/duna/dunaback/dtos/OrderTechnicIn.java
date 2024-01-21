package com.duna.dunaback.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderTechnicIn {
    private String technicType;
    private String vehicleManufacturer;
    private String vehicleModel;
    private Integer manufacturerDate;
    private String additionalEquipment;
    private List<MultipartFile> images;
    private Integer unitAmount;
    private Integer shiftType;
    private String address;
    private LocalDateTime occupationStart;
    private LocalDateTime occupationEnd;
    private Integer occupationDays;
    private String additionalInfo;
    private Double price;
    private Integer paymentUnit;
    private Integer paymentType;
}
