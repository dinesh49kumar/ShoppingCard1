package com.shoping.card.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.card.entity.Customer;
import com.shoping.card.pojo.CustomerPojo;
import com.shoping.card.repository.CustomerRepository;
import com.shoping.card.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	public String createCustomer(CustomerPojo customerP) {
		 Customer customer =CustomerPojo.getEntity(customerP);
		 repository.save(customer);
		 return "create record successfully";
	}
	
	public String updateCustomerDetails(CustomerPojo pojo) {

		Customer customerRequest = CustomerPojo.getEntity(pojo);
		Customer result =null;
		if(repository.getById(customerRequest.getCustomerId()) != null) {
			
			result = repository.getById(customerRequest.getCustomerId());
			
			if(!customerRequest.getCountry().isEmpty()) {
				result.setCountry(customerRequest.getCountry());
			}
			
			if(!customerRequest.getName().isEmpty()) {
				result.setName(customerRequest.getName());
			}
			
			if(customerRequest.getDOB() !=null) {
				result.setDOB(customerRequest.getDOB());
			}
			
			if(customerRequest.getGender().isEmpty()) {
				result.setGender(customerRequest.getGender());
			}
			
			if(customerRequest.getSalary() >= 0) {
				result.setSalary(customerRequest.getSalary());
			}
			
		}
		repository.save(result);
		return "Update successfully";
	}
	
	public CustomerPojo getCustomer(String customerId) {
		Customer customer = repository.getById(customerId);

		return  CustomerPojo.getPojo(customer);
	}
	
	public List<CustomerPojo> getAllCustomer(String name){
		List<Customer> oCustomer = repository.getByName(name);
		List<CustomerPojo> pojo= new ArrayList<CustomerPojo>();
		for (Customer customer : oCustomer) {
			pojo.add(CustomerPojo.getPojo(customer));
		}
		return pojo;
	}

	public String deleteCustomer(String customerId) {
		repository.deleteById(customerId);
		return "delete customer record with "+customerId;
	}
}
