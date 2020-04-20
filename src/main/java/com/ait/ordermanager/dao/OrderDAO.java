package com.ait.ordermanager.dao;

import com.ait.ordermanager.dto.Order;
import com.ait.ordermanager.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO {
    private List<Order> orderList = new LinkedList<>(Arrays.asList(
            new Order(0, "Max Smith", "12 South Road", "Cork", "Republic of Ireland", 2),
            new Order(1, "Alex Connolly", "1 Chaneese Quater", "Athlone", "Republic of Ireland", 3),
            new Order(2, "Kerry Connolly", "86 Seasamy Street", "Athlone", "Republic of Ireland", 4),
            new Order(3, "Anna Kelly", "423 Long Drive Road", "Dublin", "Republic of Ireland", 1),
            new Order(4, "John Smith", "9 North Road", "Dublin", "Republic of Ireland", 1),
            new Order(5, "Mariah Kelly", "73 Upper Street Smitfield", "Kerry", "Republic of Ireland", 4),
            new Order(6, "Lary Lee", "534 Sundrive Road", "Kerry", "Republic of Ireland", 4),
            new Order(7, "Max O'Connor", "6 Henry Street", "Cork", "Republic of Ireland", 5),
            new Order(8, "Ben Sullivan", "432 O'Connel Street", "Cork", "Republic of Ireland", 1),
            new Order(9, "Gavin Kelly", "15 East Wall Road", "Dublin", "Republic of Ireland", 2),
            new Order(10, "Yvonne Smith", "765 Sandymount Drive", "Dublin", "Republic of Ireland", 2)
    ));

    private Order findOrder(int orderId)
    {
        Order found = null;
        for (Order order : orderList)
        {
            if(order.getId() == orderId)
            {
                found = order;
                break;
            }
        }
        return found;
    }

    public List<Order> findAll()
    {
        return orderList;
    }

    public Optional<Order> findById(int id)
    {
        Order foundOrder = findOrder(id);
        if(foundOrder != null)
            return Optional.ofNullable(foundOrder);
        else
            throw new OrderNotFoundException("Unable to find order with id: " +id);
    }

    public void deleteOrder(int id)
    {
        Order foundOrder = findOrder(id);
        if(foundOrder != null)
            orderList.remove(foundOrder);
        else
            throw new OrderNotFoundException("Unable to find order with id: " + id);
    }

    public void createOrder(Order newOrder)
    {
        orderList.add(newOrder);
    }

    public boolean updateOrder(Order order)
    {
        boolean updated = true;
        Order foundOrder = findOrder(order.getId());
        if(foundOrder != null)
        {
            int index = orderList.indexOf(foundOrder);
            orderList.remove(index);
            orderList.add(index, order);
        }
        else
        {
            orderList.add(order);
            updated = false;
        }
        return updated;
    }
}


