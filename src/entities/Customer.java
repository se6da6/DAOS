package entities;

public class Customer {
    Integer ID;
    String Name;
    String Address;
    String City;
    String State;
    String Zip;

    public Integer getID()
    {
        return ID;
    }

    public void setID(Integer id)
    {
        this.ID = id;
    }

    public String getName()
    {
         return Name;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        this.Address = address;
    }

    public String getCity()
    {
        return City;
    }

    public void setCity(String city)
    {
        this.City = city;
    }

    public String getState()
    {
        return State;
    }

    public void setState(String state)
    {
        this.State = state;
    }

    public String getZip()
    {
        return Zip;
    }

    public void setZip(String zip)
    {
        this.Zip = zip;
    }


    @Override
    public String toString()
    {
        return "Customer [ID=" + ID + "Name= " + Name + "Address = " + Address +
                "City= " + City + "State = " + State + "Zip = " + Zip + "]";
    }
    
}
