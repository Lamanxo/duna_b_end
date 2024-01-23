package com.duna.dunaback.controllers;

import com.duna.dunaback.dtos.OrderTechnicIn;
import com.duna.dunaback.dtos.OrderTechnicOut;
import com.duna.dunaback.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/secured/order/technic")
    public OrderTechnicOut createTechnicOrder(Principal principal, @RequestBody OrderTechnicIn technicIn) {
        return ordersService.addOrderTechnic(technicIn, principal);
    }

}
