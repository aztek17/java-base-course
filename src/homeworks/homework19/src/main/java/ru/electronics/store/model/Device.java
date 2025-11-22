package ru.electronics.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "device")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "price")
    private Integer price;

    public Device(String type, String brand, String modelName, Integer price) {
        this.type = type;
        this.brand = brand;
        this.modelName = modelName;
        this.price = price;
    }

}
