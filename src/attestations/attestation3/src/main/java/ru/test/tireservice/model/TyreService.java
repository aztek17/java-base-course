package ru.test.tireservice.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Setter
@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TyreService extends BaseModel {

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "duration")
    private Integer durationMinutes;

    @OneToMany(mappedBy = "service")
    @ToString.Exclude
    private List<OrderItem> ordersItems = new ArrayList<>();

}
