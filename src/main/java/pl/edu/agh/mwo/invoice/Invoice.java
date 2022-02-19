package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private final Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
    }


    private final HashMap<Product, Integer> quantity = new HashMap<>();

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Incorrect data. Quantity is negative or zero. Change value of quantity please");
        this.quantity.put(product, quantity);
    }



    public BigDecimal getNetPrice2() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products){
            sum = sum.add(product.getPrice()) ;
        }
        return sum;
    }


    public BigDecimal getTax() {
        BigDecimal sumTax = BigDecimal.ZERO;
        for (Product product : this.products){

            sumTax = sumTax.add((product.getTaxPercent()).multiply(product.getPrice())) ;

        }
        return sumTax;
    }


    public BigDecimal getTotal1() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products){
            sum = sum.add(product.getPrice()) ;
        }
        BigDecimal sumTax = BigDecimal.ZERO;
        for (Product product : this.products){
            sumTax = sumTax.add((product.getTaxPercent()).multiply(product.getPrice())) ;
        }
        return sum.add(sumTax);
    }


    public BigDecimal getTotal2() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.quantity.keySet()){
            BigDecimal quantityAsBigDecimal  = BigDecimal.valueOf(quantity.get(product));
            sum = sum.add((product.getPrice()).multiply(quantityAsBigDecimal)) ;
        }
        BigDecimal sumTax = BigDecimal.ZERO;
        for (Product product : this.quantity.keySet()){
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity.get(product));
            sumTax = sumTax.add((product.getTaxPercent()).multiply(product.getPrice()).multiply(quantityAsBigDecimal)) ;
        }
        return sum.add(sumTax);
    }

    public BigDecimal getNetPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.quantity.keySet()){
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity.get(product));
            sum = sum.add((product.getPrice()).multiply(quantityAsBigDecimal)) ;
        }
        return sum;
    }}


