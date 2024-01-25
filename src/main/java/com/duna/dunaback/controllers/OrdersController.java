package com.duna.dunaback.controllers;

import com.duna.dunaback.dtos.OrderTechnicIn;
import com.duna.dunaback.dtos.OrderTechnicOut;
import com.duna.dunaback.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/secured/order/technic")
    public OrderTechnicOut createTechnicOrder(Principal principal, @RequestBody OrderTechnicIn technicIn) {
        return ordersService.addOrderTechnic(technicIn, principal);
    }

    @PostMapping("/secured/order/technic/photo")
    public String addImageToOrder(Principal principal, @RequestParam("image") MultipartFile file, @RequestParam("orderId") Long orderId) throws IOException {
        return ordersService.addPhotoToOrder(file, principal, orderId);
    }

    @GetMapping("/secured/order/technic/photo_names/{orderId}")
    public List<String> getAllImageNamesOfOrder(Principal principal, @PathVariable Long orderId) {
        return ordersService.findAllImageNamesByOrderId(principal, orderId);
    }

    /*@GetMapping("/secured/order/technic/download_photo/{fileName}")
    public byte[] downloadOrderPhotoByName(@PathVariable String fileName, Principal principal) throws IOException {
        return ordersService.downloadOrderImageByName(fileName, principal);
    }
*/
    @GetMapping("/secured/order/technic/download_photo/{fileName}")
    public ResponseEntity<?> downloadOrderPhotoByName(@PathVariable String fileName, Principal principal) throws IOException {
        byte[] imageData = ordersService.downloadOrderImageByName(fileName, principal);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
