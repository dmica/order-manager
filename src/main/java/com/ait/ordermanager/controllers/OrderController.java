package com.ait.ordermanager.controllers;
import com.ait.ordermanager.dao.OrderDAO;
import com.ait.ordermanager.dto.Order;
import com.ait.ordermanager.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("orders/{orderId}")
    public Order getWPhoneById(@PathVariable int orderId)
    {
        Optional<Order> foundOrder = orderDAO.findById(orderId);
        if(foundOrder.isPresent())
            return foundOrder.get();
        else
            throw new OrderNotFoundException("Unable to retrieve order with id:" + orderId);
    }

    @DeleteMapping("orders/{orderId}")
    public void deleteOrderById(@PathVariable int orderId)
    {
        orderDAO.deleteOrder(orderId);
    }

    @PostMapping("orders/")
    public ResponseEntity createOrder(@RequestBody Order newOrder)
    {
        orderDAO.createOrder(newOrder);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(newOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("orders/")
    public ResponseEntity updateOrder(@RequestBody Order newOrder)
    {
        boolean updated = orderDAO.updateOrder(newOrder);
        if(updated)
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newOrder.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }
}
