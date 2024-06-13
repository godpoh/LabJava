/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriodosjavaa;
import java.util.HashMap;
public class BuyDetails {

    private String idBuy;
    private String pizzaName;
    private String typeOfPaste;
    private String pizzaSize;
    private String ingredients;
    private String additionals;
    private String discountCode;
    private String date;
    private int grossTotalAmount;
    private int discountedAmount;

    public static HashMap<Integer, BuyDetails> BuyDetailsDict = new HashMap<>();
    
    public BuyDetails(String idBuy, String pizzaName, String typeOfPaste, String pizzaSize, String ingredients,
            String additionals, String discountCode, String date, int grossTotalAmount, int discountedAmount) {
        this.idBuy = idBuy;
        this.pizzaName = pizzaName;
        this.typeOfPaste = typeOfPaste;
        this.pizzaSize = pizzaSize;
        this.ingredients = ingredients;
        this.additionals = additionals;
        this.discountCode = discountCode;
        this.date = date;
        this.grossTotalAmount = grossTotalAmount;
        this.discountedAmount = discountedAmount;
    }

    // Getters
    public String getIdBuy() {
        return idBuy;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getTypeOfPaste() {
        return typeOfPaste;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getAdditionals() {
        return additionals;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public String getDate() {
        return date;
    }

    public int getGrossTotalAmount() {
        return grossTotalAmount;
    }

    public int getDiscountedAmount() {
        return discountedAmount;
    }
}
