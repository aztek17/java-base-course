package ru.demo.tv.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name = "TV")
@NoArgsConstructor
@AllArgsConstructor
public class Tv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Integer price;

    @Column(name = "voice_assistant")
    private boolean voiceAssistant;

    @Column(name = "screen_size")
    private Integer screenSize;

}
