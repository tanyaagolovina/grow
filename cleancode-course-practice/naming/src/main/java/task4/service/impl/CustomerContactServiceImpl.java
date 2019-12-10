package task4.service.impl;


import task4.service.CustomerContactService;
import task4.thirdpartyjar.CustomerContact;
import task4.thirdpartyjar.CustomerContactDAO;

public class CustomerContactServiceImpl implements CustomerContactService {

    private CustomerContactDAO customerContactDAO;

    public CustomerContact findCustomerContactById(Long customerId) {
        return customerContactDAO.findById(customerId);
    }

    public void updateCustomerContact(CustomerContact customerContact) {
        customerContactDAO.update(customerContact);
    }
}
