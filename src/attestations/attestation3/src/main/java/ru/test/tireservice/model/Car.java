package ru.test.tireservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    public enum CarType {
        PASSENGER, SUV, TRUCK, BUS, COMMERCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CarType type;

    @Column(name = "tire_size")
    private Integer tireSize;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

}
