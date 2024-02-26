package com.duna.dunaback.entities;

import com.duna.dunaback.entities_mini.MaterialFractions;
import com.duna.dunaback.entities_mini.NonMetalMaterialType;
import com.duna.dunaback.enums.DeliveryType;
import com.duna.dunaback.enums.PaymentType;
import com.duna.dunaback.enums.ShiftType;
import com.duna.dunaback.enums.WeightType;
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
    @ManyToOne
    @JoinColumn(name = "non_metal_material_type_id")
    private NonMetalMaterialType nonMetMatType;
    @ManyToOne
    @JoinColumn(name = "material_fractions_id")
    private MaterialFractions materialFractions;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "weight_type_id")
    private WeightType weightType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "shift_type_id")
    private ShiftType shiftType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delivery_type_id")
    private DeliveryType deliveryType;
    @Column(name = "address")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type_id")
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "is_verified")
    private boolean isVerified;
    @Column(name = "is_active")
    private boolean isActive;
}
