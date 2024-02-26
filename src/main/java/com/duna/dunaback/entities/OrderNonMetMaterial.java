package com.duna.dunaback.entities;

import com.duna.dunaback.enums.PaymentType;
import com.duna.dunaback.enums.ShiftType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_non_met_material")
public class OrderNonMetMaterial {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nonMetMatType; //makeclass
    private Long materialFractions; //makeclass
    //private ton or m3 //makeclass
    private ShiftType shiftType;
    //private dostavkd //makeclass enum
    private String address;
    private String description;
    private Double price;
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdAt;
    private boolean isVerified;
    private boolean isActive;
}
