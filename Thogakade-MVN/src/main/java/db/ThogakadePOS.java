package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class ThogakadePOS {

    private static ThogakadePOS instance;

    private List<Customer> connection;

    private ThogakadePOS(){
        connection = new ArrayList<>();
    }

    public List<Customer> getConnection(){
        return connection;
    }

    public static ThogakadePOS getInstance(){

        return null==instance?instance=new ThogakadePOS():instance;
    }
}
