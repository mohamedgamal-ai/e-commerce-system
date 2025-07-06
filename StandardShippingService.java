
import java.util.List;

public class StandardShippingService implements ShippingService {
    private static final double FEE_PER_KG = 2.0;

    @Override
    public double calculateShippingFee(List<Shippable> shippableProducts) {
        double totalWeight = 0;
        for (Shippable product : shippableProducts) {
            totalWeight += product.getWeight();
        }
        return totalWeight * FEE_PER_KG;
    }
}

