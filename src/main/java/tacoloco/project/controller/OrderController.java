package tacoloco.project.controller;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tacoloco.project.objects.Order;

/**
 *
 * @author kenji
 */
@RestController
public class OrderController {
    @PostMapping("/order")
    public ResponseEntity<?> order(@Valid @RequestBody Order order) {
        Map<String, String> response = new HashMap<>();
        
        // Get the order details
        int veggie = order.getVeggie();
        int chicken = order.getChicken();
        int beef = order.getBeef();
        int chorizo = order.getChorizo();
        
        // If null, return error
        if(veggie < 0 || chicken < 0 || beef < 0 || chorizo < 0) {
            response.put("success", "false");
            response.put("reason", "negative");
            response.put("price", "error");
            response.put("discount", "error");
            ResponseEntity.badRequest();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        // Calculate the total
        double total = (veggie*2.50 + chicken*3.00 + beef*3.00 + chorizo*3.50);
        double discount = 0.0;
        
        // If we ordered more than 4, apply discount
        if(veggie+chicken+beef+chorizo >= 4){
            discount = total * 0.20;
            total -= discount;
        }
        
        // Format in USD
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
        // Return success status, price, and discount
        response.put("success", "true");
        response.put("price", n.format(total));
        response.put("discount", n.format(discount));
        return ResponseEntity.ok(response);
    }
}
