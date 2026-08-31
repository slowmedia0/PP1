package com.slowmedia0.PP1.services;

import com.slowmedia0.PP1.DTO.CartElemMapper;
import com.slowmedia0.PP1.DTO.OrderDTO;
import com.slowmedia0.PP1.DTO.OrderMapper;
import com.slowmedia0.PP1.database.CartElemRepository;
import com.slowmedia0.PP1.database.OrderElemRepository;
import com.slowmedia0.PP1.database.OrderRepository;
import com.slowmedia0.PP1.entity.CartElem;
import com.slowmedia0.PP1.entity.Order;
import com.slowmedia0.PP1.entity.OrderElem;
import com.slowmedia0.PP1.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    CartElemRepository cartElemRepository;
    CartElemMapper cartElemMapper;
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    public OrderService(CartElemRepository cartElemRepository, CartElemMapper cartElemMapper, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.cartElemRepository = cartElemRepository;
        this.cartElemMapper = cartElemMapper;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO addCartToOrder(List<BigInteger> cartElemIds){
        List<CartElem> cartElems = cartElemRepository.findAllById(cartElemIds);
        Order order = new Order();
        for (CartElem cartElem:  cartElems){
            OrderElem orderElem = cartElemMapper.toOrderElem(cartElem);
            order.addOrderElem(orderElem);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        order.setUser(user);
        order = orderRepository.save(order);
        OrderDTO orderDTO = orderMapper.toDto(order);
        return orderDTO;
    }

    public void removeOrder(BigInteger orderId){
        orderRepository.deleteById(orderId);
    }
}
