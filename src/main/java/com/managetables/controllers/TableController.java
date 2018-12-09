package com.managetables.controllers;

import com.managetables.utilities.DatabaseUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

import com.managetables.models.CustomerInfo;

@RestController
@CrossOrigin(origins = "*")
public class TableController {
    private DatabaseUtils DU = new DatabaseUtils();

    @GetMapping("/getAllData/{offset}")
    public List getAllData(@PathVariable("offset") int offset) {
        String sql = MessageFormat.format("SELECT * FROM customer_info ORDER BY id ASC limit 5 offset {0}", offset);
        return DU.sqlQuery(sql);
    }

    @GetMapping("/getCount")
    public List getCount() {
        String sql = "SELECT COUNT(*) FROM customer_info";
        return DU.sqlQuery(sql);
    }

    @PostMapping("/addCustomer")
    public CustomerInfo addCustomers(@RequestBody CustomerInfo info) {
        String sql = "INSERT INTO customer_info (name, address, balance, birthday)\n" +
                "VALUES ('"+info.getName()+"', '"+info.getAddress()+"', '"+info.getBalance()+"', '"+info.getBirthday()+"')";
        DU.sqlQuery(sql);
        return info;
    }

    @PostMapping("/editCustomer")
    public CustomerInfo editCustomer(@RequestBody CustomerInfo info) {
        String sql = "UPDATE customer_info SET\n" +
                "  name= '"+info.getName()+"',\n" +
                "  address= '"+info.getAddress()+"',\n" +
                "  balance = '"+info.getBalance()+"',\n" +
                "  birthday = '"+info.getBirthday()+"'\n" +
                "WHERE id = '"+info.getId()+"'";
        DU.sqlQuery(sql);
        return info;
    }

    @GetMapping("/deleteCustomer/{id}")
    public List deleteCustomer(@PathVariable("id") int id) {
        String sql = MessageFormat.format("DELETE FROM customer_info WHERE id = {0}", id);
        return DU.sqlQuery(sql);
    }

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello world";
    }


    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
