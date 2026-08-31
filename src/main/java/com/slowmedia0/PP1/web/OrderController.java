package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.OrderDTO;
import com.slowmedia0.PP1.database.CartElemRepository;
import com.slowmedia0.PP1.entity.CartElem;
import com.slowmedia0.PP1.services.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@Data
@RequestMapping("/cart/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(params = "goToCartFromOrder")
    public String goToCartFromOrder(@RequestParam(name = "order_id") BigInteger orderId){
        orderService.removeOrder(orderId);
        return "redirect:/cart";
    }


    @GetMapping(name = "addCartToOrder")
    public String addCartToOrder(Model model) {
        OrderDTO orderDTO = orderService.addCartToOrder((List<BigInteger>) model.getAttribute("cartElemIds"));
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("sumPriceInRubles", orderDTO.getSumPriceInRubles());
        model.addAttribute("count", orderDTO.orderELemsDTOS().size());
        return "order";
    }
}
