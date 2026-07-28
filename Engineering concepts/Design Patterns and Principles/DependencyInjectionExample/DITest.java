package DependencyInjectionExample;

/**
 * Composition root — this is where dependencies are wired together. The
 * concrete repository is created here and INJECTED into the service.
 */
public class DITest {
    public static void main(String[] args) {
        // Create the dependency...
        CustomerRepository repository = new CustomerRepositoryImpl();
        // ...and inject it into the service via the constructor.
        CustomerService service = new CustomerService(repository);

        System.out.println("Lookup id=1 -> " + service.getCustomer(1));
        System.out.println("Lookup id=3 -> " + service.getCustomer(3));
        System.out.println("Lookup id=9 -> " + service.getCustomer(9));
    }
}
