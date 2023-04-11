import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import daos.CustomerDao;
import entities.Customer;
import entities.Database;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        List<Customer>customerList;
        try(Connection connection = Database.getDatabaseConnection();
        Statement statement = connection.createStatement();)
        {
            /**
             * Creating customer Dao object
             */
            CustomerDao customerDao = new CustomerDao(connection);
            customerList = customerDao.findAll();

            /**
             * Writing customer entity data
             */
            System.out.println("Customer printes:");
            for(Customer customer:customerList)
            {
                System.out.println(customer);
            }

            /**
             * Insert new data in to the customer table
             */

             /*Customer insertCustomer = new Customer();
             insertCustomer.setAddress("King Street");
             insertCustomer.setCity("Kingston");
             insertCustomer.setName("Joe Brown");
             insertCustomer.setState("CA");
             insertCustomer.setZip("Xy12");
             customerDao.insert(insertCustomer);*/

             /**
              * Update an existing one
              */

              /*Customer updateCustomer = new Customer();
              updateCustomer = customerDao.findById(4);
              updateCustomer.setAddress("Johnson Street");
              Boolean success = customerDao.update(updateCustomer);*/


              /**
               * Delete an existing data
               */
             // Boolean success2= customerDao.delete(4);
        }
        catch(Exception ex)
        {
            System.out.println("Exception" + ex.getMessage());
        }
       
    }
}
