package DependencyInjectionExample;

/** Abstraction the service depends on (not a concrete implementation). */
public interface CustomerRepository {
    Customer findCustomerById(int id);
}
