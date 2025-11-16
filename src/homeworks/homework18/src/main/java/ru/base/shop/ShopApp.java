package ru.base.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.base.shop.repository.OrdersRepository;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class ShopApp implements CommandLineRunner {

    private final OrdersRepository ordersRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Заказ с ID 3: " + ordersRepository.findById(3));
        System.out.println("Заказ с датой 2025-12-07 : " + ordersRepository.findByOrderDate(LocalDate.of(2025, 12, 7)));
        System.out.println("Список все заказов до удаления по ID: " + ordersRepository.findAll());
        ordersRepository.deleteById(3);
        System.out.println("Список все заказов после удаления по ID: " + ordersRepository.findAll());
        ordersRepository.deleteAll();
        System.out.println("Список заказов после отчистки: " + ordersRepository.findAll());

    }
}
