package com.ait.ordermanager.controllers;

import com.ait.ordermanager.dao.OrderDAO;
import com.ait.ordermanager.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController
{
    @Autowired
    OrderDAO orderDAO;

    @GetMapping("orders")
    public List<Order> getAllOrders()
    {
        return orderDAO.findAll();
    }
}
