package shaaf.dev.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class OrderRequests {

    private static GenerateFullNames fullNames = null;

    private static final Logger log = LoggerFactory.getLogger(OrderRequests.class);

    // Set this in app.props to true if you are hitting the actual incidents/responders end points.
    // False would only show the data and not hit the end point.
    @ConfigProperty(name = "sendRestToBackend")
    boolean send;

    // RestClient for the backend
    @RestClient
    BackendService backendService;
    private String fNameFile= "/FNames.txt";
    private String lNameFile= "/LNames.txt";


    public OrderRequests(){
        fullNames = new GenerateFullNames(fNameFile,lNameFile);
    }

    public Order generateSingleOrder(){
        return  generateSingleOrder(GenerateNumbers.getNextOrder());
    }

    public Order generateSingleOrder(String cartId){

        String name = fullNames.getNextFullName();
        String orderId = cartId;

        Order order = new Order.Builder().orderId(cartId)
                .name(name)
                .billingAddress("Boston, MA")
                .number(GenerateNumbers.getNextCreditCardNumber())
                .expiration(String.valueOf(biasedRandom(24, 29,0.5)))
                .nameOnCard(name)
                .build();

        if(send){
            backendService.createCart(cartId, "329299", "1");
            backendService.checkOutCart(cartId, order);
        }

        log.info(order.toString());
        return order;

    }

    public List<Order> generateCarts(int number, boolean send){
        List<Order> orders = new ArrayList<>(number);
        for(int i=0; i<number; i++)
            orders.add(generateSingleOrder());
        return orders;
    }

    protected int biasedRandom(int min, int max, double bias) {
        double d = ThreadLocalRandom.current().nextDouble();
        double biased = Math.pow(d, bias);
        return (int) Math.round(min + (max-min)*biased);
    }
 }
