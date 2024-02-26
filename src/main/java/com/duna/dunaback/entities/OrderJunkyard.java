package com.duna.dunaback.entities;

import com.duna.dunaback.entities_mini.DumpType;
import com.duna.dunaback.entities_mini.DumpVehicleClass;
import com.duna.dunaback.entities_mini.HazardClass;
import com.duna.dunaback.entities_mini.JunkyardOrderType;
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
@Table(name = "order_junkyard")
public class OrderJunkyard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "junkyard_order_type_id")
    private JunkyardOrderType junkyardOrderType;
    @ManyToOne
    @JoinColumn(name = "dump_type_id")
    private DumpType dumpType;
    @ManyToOne
    @JoinColumn(name = "hazard_class_id")
    private HazardClass hazardClass;
    @ManyToOne
    @JoinColumn(name = "dump_vehicle_class_id")
    private DumpVehicleClass dumpVehicleClass;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "weight_type_id")
    private WeightType weightType;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "address")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
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
