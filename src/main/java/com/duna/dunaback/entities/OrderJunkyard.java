package com.duna.dunaback.entities;

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
    private Long junkyardOrderType; //makeclass
    private Long dumpType; //makeclass
    private Long hazardClass; //makeclass
    private Long dumpVehicleClass; //makeclass
    //private ton or m3 //makeclass
    private Long weight;
    private String address;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdAt;
    private boolean isVerified;
    private boolean isActive;
}
