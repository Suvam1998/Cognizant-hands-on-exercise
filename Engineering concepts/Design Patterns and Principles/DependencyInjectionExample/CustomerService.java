package DependencyInjectionExample;

/**
 * Service that DEPENDS ON CustomerRepository. The dependency is supplied from
 * outside via the constructor (constructor injection) rather than created here
 * with `new`, so the service is decoupled from any concrete repository and is
 * easy to test with a mock.
 */
public class CustomerService {
    private final CustomerRepository repository;

    // Constructor injection.
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomer(int id) {
        Customer c = repository.findCustomerById(id);
        if (c == null) {
            System.out.println("No customer found with id=" + id);
        }
        return c;
    }
}
