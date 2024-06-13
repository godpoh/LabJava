/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriodosjava;

public class BuyDetails {

    private int idNumberPizzaDict;
    private String pizzaName;
    private String typeOfPaste;
    private String pizzaSize;
    private String ingredients;
    private String additionals;
    private int descountCode;
    private String promotionDay;
    private int grossTotalAmount;
    private int discountedAmount;

    public BuyDetails(int idNumberPizzaDict, String pizzaName, String typeOfPaste, String pizzaSize, String ingredients, String additionals, int descountCode, String promotionDay, int grossTotalAmount, int discountedAmount) {
        this.idNumberPizzaDict = idNumberPizzaDict;
        this.pizzaName = pizzaName;
        this.typeOfPaste = typeOfPaste;
        this.pizzaSize = pizzaSize;
        this.ingredients = ingredients;
        this.additionals = additionals;
        this.descountCode = descountCode;
        this.promotionDay = promotionDay;
        this.grossTotalAmount = grossTotalAmount;
        this.discountedAmount = discountedAmount;
    }

    public int getIdNumberPizzaDict() {
        return idNumberPizzaDict;
    }

    public void setIdNumberPizzaDict(int idNumberPizzaDict) {
        this.idNumberPizzaDict = idNumberPizzaDict;
    }

    public String getTypeOfPaste() {
        return typeOfPaste;
    }

    public void setTypeOfPaste(String typeOfPaste) {
        this.typeOfPaste = typeOfPaste;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAdditionals() {
        return additionals;
    }

    public void setAdditionals(String additionals) {
        this.additionals = additionals;
    }

    public int getDescountCode() {
        return descountCode;
    }

    public void setDescountCode(int descountCode) {
        this.descountCode = descountCode;
    }

    public String getPromotionDay() {
        return promotionDay;
    }

    public void setPromotionDay(String promotionDay) {
        this.promotionDay = promotionDay;
    }

    public int getGrossTotalAmount() {
        return grossTotalAmount;
    }

    public void setGrossTotalAmount(int grossTotalAmount) {
        this.grossTotalAmount = grossTotalAmount;
    }

    public int getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(int discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
}
