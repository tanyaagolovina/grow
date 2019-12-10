package task4.thirdpartyjar;

public interface CustomerContactDAO {
    CustomerContact findById(Long customerId);

    void update(CustomerContact contact);
}
