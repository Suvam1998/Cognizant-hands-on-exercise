package DependencyInjectionExample;

import java.util.HashMap;
import java.util.Map;

/** Concrete repository — here backed by an in-memory map. */
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<Integer, Customer> data = new HashMap<>();

    public CustomerRepositoryImpl() {
        data.put(1, new Customer(1, "Aarav Sharma"));
        data.put(2, new Customer(2, "Bhavna Rao"));
        data.put(3, new Customer(3, "Chetan Iyer"));
    }

    @Override
    public Customer findCustomerById(int id) {
        return data.get(id);
    }
}
