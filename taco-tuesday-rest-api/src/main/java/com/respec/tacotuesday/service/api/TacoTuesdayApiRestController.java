package com.respec.tacotuesday.service.api;

import com.respec.tacotuesday.bl.TacoTuesdayDAO;
import com.respec.tacotuesday.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/taco-tuesday/v1")
public class TacoTuesdayApiRestController {
    private Logger logger = LoggerFactory.getLogger(TacoTuesdayApiRestController.class);
    private TacoPriceList tacoPriceList;
    private TacoTuesdayDAO tacoTuesdayDAO;

    @Autowired
    public TacoTuesdayApiRestController(TacoPriceList tacoPriceList, TacoTuesdayDAO tacoTuesdayDAO) {
        this.tacoPriceList = tacoPriceList;
        this.tacoTuesdayDAO = tacoTuesdayDAO;
    }

    @RequestMapping(value = "/orders/full/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<FullOrder> getFullOrderByOrderId(@PathVariable Integer orderId) {
        return new ResponseEntity<>(tacoTuesdayDAO.retrieveFullOrder(orderId), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/individual/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<IndividualOrder> getIndividualOrderByOrderId(@PathVariable Integer orderId) {
        return new ResponseEntity<>(tacoTuesdayDAO.retrieveIndividualOrder(orderId), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/full", method = RequestMethod.GET)
    public ResponseEntity<List<FullOrder>> getAllFullOrders() {
        return new ResponseEntity<>(tacoTuesdayDAO.retrieveAllFullOrders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/individual", method = RequestMethod.GET)
    public ResponseEntity<List<IndividualOrder>> getAllIndividualOrders() {
        return new ResponseEntity<>(tacoTuesdayDAO.retrieveAllIndividualOrders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/individual/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<List<IndividualOrder>> getAllIndiviudalOrdersWithEmployeeId(@PathVariable Integer employeeId) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/orders/full/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<List<FullOrder>> getAllFullOrdersWithEmployeeId(@PathVariable Integer employeeId) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/orders/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrdersByEmployeeId(@PathVariable Integer employeeId) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/tacos", method = RequestMethod.GET)
    public ResponseEntity<TacoPriceList> getTacoPrices() {
        return new ResponseEntity<>(tacoPriceList, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/full", method = RequestMethod.POST)
    public ResponseEntity<FullOrder> createFullOrder(@RequestParam(name = "apiKey") String apiKey, @RequestBody FullOrder order) {
        logger.info("Validating request, provided API Key: {}", apiKey);
        return new ResponseEntity<>(tacoTuesdayDAO.createFullOrder(order), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestParam(name = "apiKey") String apiKey, @RequestBody Employee employee) {
        logger.info("Creating employee, provided API Key: {}", apiKey);
        return new ResponseEntity<>(tacoTuesdayDAO.createEmployee(employee.getFirstName(), employee.getLastName(), employee.getNickName()), HttpStatus.CREATED);
    }

}
