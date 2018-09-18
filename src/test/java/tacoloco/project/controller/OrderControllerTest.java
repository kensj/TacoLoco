package tacoloco.project.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.NumberFormat;
import java.util.Locale;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tacoloco.project.Application;
import tacoloco.project.objects.Order;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    
    @Autowired
    private OrderController orderController;
    
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
      
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    private String[] calculate(Order order) {
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
        return new String[] {n.format(total), n.format(discount)};
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(orderController).isNotNull();
    }

    @Test
    public void calculateSafeOrder() throws Exception {
        Order order = new Order(1,2,3,4);
        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/order"), HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(jsonResponse.get("success"), "true");
        assertEquals(jsonResponse.get("price"), calculate(order)[0]);
        assertEquals(jsonResponse.get("discount"), calculate(order)[1]);
    }
    
    @Test
    public void calculateZeroOrder() throws Exception {
        Order order = new Order(0,0,0,0);
        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/order"), HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(jsonResponse.get("success"), "true");
        assertEquals(jsonResponse.get("price"), calculate(order)[0]);
        assertEquals(jsonResponse.get("discount"), calculate(order)[1]);
    }
    
    @Test
    public void calculateNegativeOrder() throws Exception {
        Order order = new Order(-1,1,4,-2);
        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/order"), HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(jsonResponse.get("success"), "false");
        assertEquals(jsonResponse.get("reason"), "negative");
        assertEquals(jsonResponse.get("price"), "error");
        assertEquals(jsonResponse.get("discount"), "error");
    }


    @Test
    public void createOrder() throws Exception {
       
    }
}