package task4.service;


import task4.thirdpartyjar.CustomerContact;

public interface CustomerContactService {

    CustomerContact findCustomerContactById(Long customerId);

    void updateCustomerContact(CustomerContact customerContact);

}
