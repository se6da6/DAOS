package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;

public class CustomerDao implements Dao<Customer,Integer>{

    Connection connection;
    //*Creating constructor */
    public CustomerDao(Connection connection)
    {
        this.connection = connection;
    }
    /*In order to show all the data inside the table we create findAll method. 
    It creates an array list and put all the data inside this arraylist and so we can reacj all the data at once. */
    public List<Customer>findAll()
    {
        List<Customer>customers = new ArrayList<>();
        try(Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM customer");
            while(result.next())
            {
                Customer customer = new Customer();
                customer.setAddress(result.getString("Address"));
                customer.setCity(result.getString("City"));
                customer.setID(result.getInt("ID"));
                customer.setName(result.getString("Name"));
                customer.setState(result.getString("State"));
                customer.setZip(result.getString("Zip"));
                customers.add(customer);
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return customers;
    }
    
    
    /**Insert method uses Customes class object as a parameter and 
     * it enables us to insert new data in to the table
     * 
     */
    public void insert(Customer customer)
    {
        try(Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,null);
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getAddress());
            ps.setString(4,customer.getCity());
            ps.setString(5,customer.getState());
            ps.setString(6,customer.getZip());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                customer.setID(keys.getInt(1));
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }
    /**Update mehtod uses Customer class object as a parameter and then
     * it enables us to change existing data.
     * It returns true when we updated data successfully
     */
    public Boolean update(Customer customer)
    {
        Boolean success = true;
        String update = "UPDATE customer SET address=? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(update))
        {
            ps.setString(1,customer.getAddress());
            ps.setInt(2,customer.getID());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return success;
    }
    /**
     * FindById method uses entity's primary key as a parameter.
     * and It enables us to find a data from the table with the primary key.
     * It returns customer class object.
     */
    public Customer findById(Integer pk)
    {
        Customer customer = new Customer();
        String select = "SELECT * FROM customer WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(select);)
        {
            ps.setInt(1, pk);
            ResultSet result = ps.executeQuery();
            if(result.next())
            {
                customer.setAddress(result.getString("Address"));
                customer.setCity(result.getString("City"));
                customer.setID(result.getInt("ID"));
                customer.setName(result.getString("Name"));
                customer.setState(result.getString("State"));
                customer.setZip(result.getString("Zip"));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return customer;
    }
    /**
     * Delete method uses table's primary key as a variable and 
     * it enables us to delete existing data. It return true if the data successfully deleted.
     */
    public Boolean delete (Integer pk)
    {
        Boolean success = false;
        String delete = "DELETE FROM customer WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(delete))
        {
            ps.setInt(1,pk);
            if(ps.executeUpdate() != 0)
            {
                success = true;
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return success;
    }

    
}
