package ru.base.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.base.shop.model.Orders;
import ru.base.shop.repository.OrdersRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public Orders getOrdersById(int id) {
        return ordersRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Заказ с ID: " + id + " не найден"));
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrdersByDate(LocalDate date) {
        return ordersRepository.findByOrderDate(date);
    }

    @Override
    public void removeOrderById(int id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public void removeAllOrders() {
        ordersRepository.deleteAll();
    }
}
