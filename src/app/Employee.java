package app;

/**
 * Created by eduardol on 17/10/2015.
 */
public class Employee {

    private String name;
    private String address;
    private double salary;

    public Employee(String name, String address, double salary) {
        setName(name);
        setAddress(address);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        String temp = String.valueOf(salary);
        temp = temp.replace(',', '.');
        salary = Double.parseDouble(temp);
        this.salary = salary;
    }

}
