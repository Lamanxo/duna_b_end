package com.duna.dunaback.entities;

import com.duna.dunaback.enums.PaymentType;
import com.duna.dunaback.enums.PaymentUnit;
import com.duna.dunaback.enums.ShiftType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_technic")
public class OrderTechnic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technic_type_id")
    private TechnicType technicType;
    @ManyToOne
    @JoinColumn(name = "vehicle_manufacturer_id")
    private VehicleManufacturer vehicleManufacturer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;
    @Column(name = "manufacturing_date")
    private Integer manufacturingDate;
    @Column(name = "additional_equipment")
    private String additionalEquipment;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable
            (name = "order_technic",
            joinColumns = @JoinColumn (name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<FileData> images;
    @Column(name = "unit_amount")
    private Integer unitAmount;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "shift_type_id")
    private ShiftType shiftType;
    @Column(name = "address")
    private String address;
    @Column(name = "occupation_start")
    private LocalDateTime occupationStart;
    @Column(name = "occupation_end")
    private LocalDateTime occupationEnd;
    @Column(name = "occupation_days")
    private Integer occupationDays;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "price")
    private Double price;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_unit_id")
    private PaymentUnit paymentUnit;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type_id")
    private PaymentType paymentType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "is_verified")
    private boolean isVerified;
    @Column(name = "is_active")
    private boolean isActive;

}
