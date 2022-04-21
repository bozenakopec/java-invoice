package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        if (products.keySet().contains(product)) {
            Integer initialQuantity = products.get(product);
            products.put(product, initialQuantity + quantity);
        } else {

            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public String listaProduktow1() {

        int i = 0;

        String listaProduktow = "Numer Faktury: " + getNumber() + "\n"
                + "ProductName: \t" + "Quantity: \t" + "Unit price:\n";

        for (Product product : products.keySet()) {
            listaProduktow = listaProduktow + product.getName() + "nazwa produktu: \t"
                    + products.get(product) + "sztuk:\t" + product.getPrice() + "cena razem pln: \n";

            i ++;
        }
        return listaProduktow;
    }
}

