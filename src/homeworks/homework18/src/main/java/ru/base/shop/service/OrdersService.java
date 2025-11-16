package ru.base.shop.service;

import ru.base.shop.model.Orders;

import java.time.LocalDate;
import java.util.List;

public interface OrdersService {

    /**
     * Получить заказ по идентификатору
     *
     * @param id - идентификатор заказа
     * @return - Заказ
     */
    Orders getOrdersById(int id);

    /**
     * Получить все заказы
     *
     * @return - Список заказов
     */
    List<Orders> getAllOrders();

    /**
     * Получить заказ по дате
     *
     * @param date - дата
     * @return - Заказ
     */
    Orders getOrdersByDate(LocalDate date);

    /**
     * Удалить заказ по идентификатору
     *
     * @param id - идентификатор заказа
     */
    void removeOrderById(int id);

    /**
     * Удалить все заказы
     */
    void removeAllOrders();

}
