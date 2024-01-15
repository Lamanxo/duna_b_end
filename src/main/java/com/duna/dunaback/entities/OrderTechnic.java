package com.duna.dunaback.entities;

import com.duna.dunaback.enums.PaymentType;
import com.duna.dunaback.enums.PaymentUnit;
import com.duna.dunaback.enums.ShiftType;

import java.time.LocalDateTime;
import java.util.List;

public class OrderTechnic {

    private TechnicType technicType;
    private VehicleManufacturer vehicleManufacturer;
    private VehicleModel vehicleModel;
    private Integer manufacturingDate;
    private String additionalEquipment;
    private List<FileData> images;
    private Integer unitAmount;
    private ShiftType shiftType;
    private String address;
    private LocalDateTime occupationStart;
    private LocalDateTime occupationEnd;
    private Integer occupationDays;
    private String additionalInfo;
    private Double price;
    private PaymentUnit paymentUnit;
    private PaymentType paymentType;
    private User user;
    private LocalDateTime createdAt;


}
