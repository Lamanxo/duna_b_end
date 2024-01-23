package com.duna.dunaback.service;

import com.duna.dunaback.dtos.OrderTechnicIn;
import com.duna.dunaback.dtos.OrderTechnicOut;
import com.duna.dunaback.entities.OrderTechnic;
import com.duna.dunaback.enums.PaymentType;
import com.duna.dunaback.enums.PaymentUnit;
import com.duna.dunaback.enums.ShiftType;
import com.duna.dunaback.repositories.OrderTechnicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderTechnicRepo orderTechnicRepo;
    private final VehicleService vehicleService;
    private final UserService userService;

    public OrderTechnicOut addOrderTechnic(OrderTechnicIn technicIn, Principal principal) {
        OrderTechnic orderTechnic = makeOrderTechnic(technicIn, principal);
        return makeOrderTechnicOut(orderTechnicRepo.save(orderTechnic));
    }

    private OrderTechnicOut makeOrderTechnicOut(OrderTechnic technic) {
        OrderTechnicOut technicOut = new OrderTechnicOut();
        technicOut.setId(technic.getId());
        technicOut.setTechnicType(technic.getTechnicType().getName());
        technicOut.setVehicleManufacturer(technic.getVehicleManufacturer().getName());
        technicOut.setVehicleModel(technic.getVehicleModel().getName());
        technicOut.setManufacturerDate(technic.getManufacturingDate());
        technicOut.setAdditionalEquipment(technic.getAdditionalEquipment());
        technicOut.setUnitAmount(technic.getUnitAmount());
        technicOut.setShiftType(technic.getShiftType().ordinal());
        technicOut.setAddress(technic.getAddress());
        technicOut.setOccupationStart(technic.getOccupationStart());
        technicOut.setOccupationEnd(technic.getOccupationEnd());
        technicOut.setOccupationDays(technic.getOccupationDays());
        technicOut.setAdditionalInfo(technic.getAdditionalInfo());
        technicOut.setPrice(technic.getPrice());
        technicOut.setPaymentUnit(technic.getPaymentUnit().ordinal());
        technicOut.setPaymentType(technic.getPaymentType().ordinal());
        return technicOut;
    }

    private OrderTechnic makeOrderTechnic(OrderTechnicIn technicIn, Principal principal) {
        OrderTechnic technic = new OrderTechnic();
        technic.setTechnicType(vehicleService.findTechnicTypeByName(technicIn.getTechnicType()));
        technic.setVehicleManufacturer(vehicleService.findVehicleManufacturerByName(technicIn.getVehicleManufacturer()));
        technic.setVehicleModel(vehicleService.findVehicleModelByName(technicIn.getVehicleModel()));
        technic.setManufacturingDate(technicIn.getManufacturerDate());
        technic.setAdditionalEquipment(technicIn.getAdditionalEquipment());
        technic.setImages(new ArrayList<>());
        technic.setUnitAmount(technicIn.getUnitAmount());
        technic.setShiftType(ShiftType.values()[technicIn.getShiftType()]);
        technic.setAddress(technicIn.getAddress());
        technic.setOccupationStart(technicIn.getOccupationStart());
        technic.setOccupationEnd(technicIn.getOccupationEnd());
        technic.setOccupationDays(technicIn.getOccupationDays());
        technic.setAdditionalInfo(technicIn.getAdditionalInfo());
        technic.setPrice(technicIn.getPrice());
        technic.setPaymentUnit(PaymentUnit.values()[technicIn.getPaymentUnit()]);
        technic.setPaymentType(PaymentType.values()[technicIn.getPaymentType()]);
        technic.setUser(userService.getUserByUsername(principal.getName()));
        technic.setCreatedAt(LocalDateTime.now());
        technic.setVerified(false);
        technic.setActive(false);

        return technic;
    }

}
