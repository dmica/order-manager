package com.ait.ordermanager;

import com.ait.ordermanager.dao.OrderDAO;

public class TestOrderDAO {
    public static void main(String[] args)
    {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println("All Orders: " + orderDAO.findAll());
        System.out.println("Order with id 2: " + orderDAO.findById(2));
    }
}
