/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tacoloco.project.controller;


import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tacoloco.project.objects.Order;

@RestController
public class FunctionController {
    @PostMapping("/order")
    public ResponseEntity<?> order(@Valid @RequestBody Order order) {
        Map<String, String> response = new HashMap<>();
        
        int veggie = order.getVeggie();
        int chicken = order.getChicken();
        int beef = order.getBeef();
        int chorizo = order.getChorizo();
        
        double total = (veggie*2.50 + chicken*3.00 + beef*3.00 + chorizo*3.50);
        double discount = 0.0;
        
        if(veggie+chicken+beef+chorizo >= 4){
            discount = total * 0.20;
            total -= discount;
        }
        
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
        response.put("success", "true");
        response.put("price", n.format(total));
        response.put("discount", n.format(discount));
        return ResponseEntity.ok(response);
    }
}
