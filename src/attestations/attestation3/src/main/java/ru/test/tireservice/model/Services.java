package ru.test.tireservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "duration")
    private Integer durationMinutes;

    @ManyToMany(mappedBy = "services")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private Car.CarType carType;

}
