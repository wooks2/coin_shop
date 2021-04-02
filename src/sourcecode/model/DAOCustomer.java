package sourcecode.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.Query;

import sourcecode.controller.DBConnection;
import sourcecode.model.Customer;

public class DAOCustomer {
    
    private static DAOCustomer instance;
    private Customer mine;
    
    private Connection conn = DBConnection.getConnection();   
    
    public static DAOCustomer getInstance(){
        if (instance == null){
            instance = new DAOCustomer();
        }
        
        return instance;
    }
    
    private DAOCustomer(){
    	mine = new Customer();
    }

    public Customer getCustomer() {
    	if(mine != null)
    		return this.mine;
    	else {
    		mine = new Customer();
    		return this.mine;
    	}
    }
    
    public void setCustomer(Customer customer) {
    	mine = customer;
    }
    
    
}
