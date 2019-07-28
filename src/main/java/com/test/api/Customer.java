package com.test.api;

public class Customer {
	private Integer CustomerId;
    private String CustomerName;
    private String Country;
     
    public Customer(){
    }
     
    public Customer(Integer customerId, String customerName, String country) {
        super();
        this.CustomerId = customerId;
        this.CustomerName = customerName;
        this.Country = country;
    }
     
    //Getters and setters
 
    @Override
    public String toString() {
        return "Customer [CustomerId=" + CustomerId + ", CustomerName="
                + CustomerName + ", Country=" + Country + "]";
    }

	public Integer getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Integer customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}
}
