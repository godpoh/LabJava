/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratoriodosjavaa;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LaboratorioDosJavaa {

    public static HashMap<Integer, List<BuyDetails>> BuyDetailsDict = new HashMap<>();
    public static double grossTotalAmountAcumulative = 0;
    public static double discountedAmountAcumulative = 0;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        int opc;
        while (true) {
            System.out.println("1. Inicio de sesion");
            System.out.println("2. Registro de cliente");
            System.out.println("3. Salir");

            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    LogIn();
                    break;
                case 2:
                    menuSignUp();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                default:
                    System.out.println("Error: Ingrese una opcion valida");
            }
        }
    }

    public static void LogIn() {
        Scanner scanner = new Scanner(System.in);
        int opcIdNumber;
        System.out.println("Ingrese su cedula: ");
        opcIdNumber = scanner.nextInt();

        if (BuyDetailsDict.containsKey(opcIdNumber)) {
            System.out.println("Este usuario ya ha comprado un producto. No puede volver a comprar.");
            return;
        }

        grossTotalAmountAcumulative = 0;
        discountedAmountAcumulative = 0;

        if (Registro.signUpClient.containsKey(opcIdNumber)) {
            boolean isAdmin = Registro.signUpClient.get(opcIdNumber).isAdmin();
            if (isAdmin) {
                menuLogInAdmin();
            } else {
                menuLogInClient(opcIdNumber);
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public static void menuLogInClient(int opcIdNumber) {
        Scanner scanner = new Scanner(System.in);

        int opc;
        while (true) {
            System.out.println("1. Pedir Pizza");
            System.out.println("2. Terminar Compra");
            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    orderPizza(opcIdNumber);
                    break;
                case 2:
                    if (BuyDetailsDict.containsKey(opcIdNumber)) {
                        List<BuyDetails> clientBuyDetailsList = BuyDetailsDict.get(opcIdNumber);
                        if (!clientBuyDetailsList.isEmpty()) {
                            double totalGrossAmount = 0;
                            double totalDiscountedAmount = 0;
                            for (BuyDetails details : clientBuyDetailsList) {
                                System.out.println("Detalles de la compra:");
                                System.out.println("Pizza: " + details.getPizzaName());
                                System.out.println("Pasta: " + details.getTypeOfPaste());
                                System.out.println("Tamaño: " + details.getPizzaSize());
                                System.out.println("Ingredientes: " + details.getIngredients());
                                System.out.println("Adicionales: " + details.getAdditionals());
                                System.out.println("Monto total bruto: " + details.getGrossTotalAmount());
                                System.out.println("Monto con descuento: " + details.getDiscountedAmount());
                                System.out.println("--------------------");

                                totalGrossAmount += details.getGrossTotalAmount();
                                totalDiscountedAmount += details.getDiscountedAmount();
                            }
                            System.out.println("Monto Total Bruto de todas las compras: " + totalGrossAmount);
                            System.out.println("Monto Total con Descuento de todas las compras: " + totalDiscountedAmount);
                            return;
                        } else {
                            System.out.println("No se ha realizado ninguna compra.");
                            return;
                        }
                    } else {
                        System.out.println("No se ha realizado ninguna compra.");
                        return;
                    }
                default:
                    System.out.println("Error: Ingrese una opcion valida");
            }
        }
    }

    public static void menuLogInAdmin() {
        Scanner scanner = new Scanner(System.in);

        int opc;
        while (true) {
            System.out.println("1. Reportes");
            System.out.println("2. Volver al menu anterior");

            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    reports();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Error: Ingrese una opcion valida");
            }
        }
    }

    public static void menuSignUp() {
        Scanner scanner = new Scanner(System.in);

        int opc;
        while (true) {
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Volver al menu principal");

            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    clientSignUp();
                    break;
                case 2:
                    editClientInfo();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    mainMenu();
                    break;
                default:
                    System.out.println("Error: Ingrese una opcion valida");
            }
        }
    }

    // menuLogIn funciones
    public static void reports() {
        Scanner scanner = new Scanner(System.in);

        int opc;
        System.out.println("1. Imprimir la lista de cedulas almacenadas en la estructura de datos de usuarios en forma de menu");
        System.out.println("2. Imprimir en consola la cantidad de personas que han comprado pizzas por cada provincia.");
        opc = scanner.nextInt();

        switch (opc) {
            case 1:
                report1();
                break;
            case 2:
                report2();
                break;
            default:
                System.out.println("Error: Seleccione una opcion valida.");
        }
    }

    public static void report1() {
        if (Registro.signUpClient.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        if (BuyDetailsDict.isEmpty()) {
            System.out.println("No hay clientes que hayan realizado compras.");
            return;
        }

        System.out.println("Detalles de las compras de los clientes:");

        boolean hasPurchases = false;

        for (Integer cedula : BuyDetailsDict.keySet()) {
            List<BuyDetails> clientBuyDetailsList = BuyDetailsDict.get(cedula);
            if (!clientBuyDetailsList.isEmpty()) {
                Registro client = Registro.signUpClient.get(cedula);
                System.out.println("--------------------");
                System.out.println("Cedula: " + client.getIdNumber());
                System.out.println("Nombre: " + client.getName());
                System.out.println("Genero: " + client.getGenre());
                System.out.println("Provincia: " + client.getProvince());
                System.out.println("-----------------------------------");
                double totalGrossAmount = 0;
                double totalDiscountedAmount = 0;
                for (BuyDetails details : clientBuyDetailsList) {
                    System.out.println("Nombre de la pizza: " + details.getPizzaName());
                    System.out.println("Tamaño de la pizza: " + details.getPizzaSize());
                    System.out.println("Monto de la pizza: " + details.getGrossTotalAmount());
                    System.out.println("Monto de la pizza con descuento: " + details.getDiscountedAmount());
                    totalGrossAmount += details.getGrossTotalAmount();
                    totalDiscountedAmount += details.getDiscountedAmount();
                    System.out.println("-----------------------------------");
                }
                System.out.println("Monto Total Bruto: " + totalGrossAmount);
                System.out.println("Monto Total con Descuento: " + totalDiscountedAmount);
                hasPurchases = true;
            }
        }

        if (!hasPurchases) {
            System.out.println("No hay clientes que hayan realizado compras.");
        }
    }

    public static void report2() {
        int SanJose = 0;
        int Alajuela = 0;
        int Guanacaste = 0;
        int Limon = 0;
        int Cartago = 0;
        int Heredia = 0;
        int Puntarenas = 0;

        for (Registro client : Registro.signUpClient.values()) {
            if (BuyDetailsDict.containsKey(client.getIdNumber())) {
                String province = client.getProvince();

                switch (province) {
                    case "San Jose":
                        SanJose++;
                        break;
                    case "Alajuela":
                        Alajuela++;
                        break;
                    case "Guanacaste":
                        Guanacaste++;
                        break;
                    case "Limon":
                        Limon++;
                        break;
                    case "Cartago":
                        Cartago++;
                        break;
                    case "Heredia":
                        Heredia++;
                        break;
                    case "Puntarenas":
                        Puntarenas++;
                        break;
                    default:
                        break;
                }
            }
        }

        System.out.println("Cantidad de personas que han comprado pizza por provincia: ");
        System.out.println("San Jose: " + SanJose);
        System.out.println("Alajuela: " + Alajuela);
        System.out.println("Guanacaste: " + Guanacaste);
        System.out.println("Limon: " + Limon);
        System.out.println("Cartago: " + Cartago);
        System.out.println("Heredia: " + Heredia);
        System.out.println("Puntarenas: " + Puntarenas);
    }

    public static void orderPizza(int opcIdNumber) {
        Scanner scanner = new Scanner(System.in);

        int opcPastaType;
        System.out.println("Tipo de pasta: ");
        System.out.println("1. Pasta Delgada");
        System.out.println("2. Pasta Gruesa");
        opcPastaType = scanner.nextInt();

        String pasta = "";
        if (opcPastaType == 1) {
            pasta = "Delgada";
        } else if (opcPastaType == 2) {
            pasta = "Gruesa";
        } else {
            System.out.println("Intentelo de nuevo.");
        }

        double pastaPercentage = Pizza.getPastaTypes().get(pasta);

        int opcPizzaSize;
        System.out.println("Tamaño de pizza: ");
        System.out.println("1. Mediana");
        System.out.println("2. Grande");
        System.out.println("3. Familiar");
        opcPizzaSize = scanner.nextInt();

        String type = "";
        if (opcPizzaSize == 1) {
            type = "Mediana";
        } else if (opcPizzaSize == 2) {
            type = "Grande";
        } else if (opcPizzaSize == 3) {
            type = "Familiar";
        } else {
            System.out.println("Intentelo de nuevo..");
        }

        double baseCostPizza = Pizza.getSizes().get(type);

        System.out.println("Seleccione el tipo de pizza: ");
        for (int i = 0; i < Pizza.getPizzaTypes().size(); i++) {
            System.out.println((i + 1) + ". " + Pizza.getPizzaTypes().get(i).getName());
        }

        int opcPizzaType = scanner.nextInt();
        Pizza selectedPizza = Pizza.getPizzaTypes().get(opcPizzaType - 1);

        if (hasUserAlreadyBoughtThisPizza(opcIdNumber, selectedPizza.getName())) {
            System.out.println("Ya ha comprado esta pizza anteriormente.");
            return;
        }
        ArrayList<String> ingredients = new ArrayList<>(selectedPizza.getIngredients());

        while (true) {
            System.out.println("Ingredientes de la pizza:");
            for (int i = 0; i < ingredients.size(); i++) {
                System.out.println((i + 1) + ". " + ingredients.get(i));
            }
            System.out.println((ingredients.size() + 1) + ". Terminar");

            System.out.println("Seleccione el ingrediente que quiere quitar: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == ingredients.size() + 1) {
                break;
            } else if (choice > 0 && choice <= ingredients.size()) {
                String removedIngredient = ingredients.remove(choice - 1);
                System.out.println("Ingrediente " + removedIngredient + " eliminado.");
            } else {
                System.out.println("Opción no valida. Intente de nuevo.");
            }
        }

        int opcAdditional;
        System.out.println("Productos adicionales: ");
        System.out.println("1. Refresco");
        System.out.println("2. Pan de Ajo");
        System.out.println("3. Postre");
        System.out.println("4. Ninguno");
        opcAdditional = scanner.nextInt();

        String additionals = "";
        if (opcAdditional == 1) {
            additionals = "Refresco";
        } else if (opcAdditional == 2) {
            additionals = "Pan de Ajo";
        } else if (opcAdditional == 3) {
            additionals = "Postre";
        } else if (opcAdditional == 4) {
            additionals = "Ninguno";
        } else {
            System.out.println("Intentelo de nuevo.");
        }
        double baseCostAdditional = Pizza.getAdditionalItems().get(additionals);

        //Mostrar codigo de descuento
        double grossTotalAmount = baseCostPizza + (baseCostPizza * pastaPercentage) + baseCostAdditional;
        double discountedAmount = grossTotalAmount;

        String discountCode = "";
        if (selectedPizza.getName().equals("Pepperoni") || selectedPizza.getName().equals("Suprema")) {
            if (type.equals("Familiar") && ingredients.size() == selectedPizza.getIngredients().size()) {
                discountCode = generateDiscountCode();
                discountedAmount = grossTotalAmount * 0.95;
            }
        }

        grossTotalAmountAcumulative += grossTotalAmount;
        discountedAmountAcumulative += discountedAmount;
        System.out.println("Monto Total Bruto: " + grossTotalAmountAcumulative);
        System.out.println("Monto Total con Descuento: " + discountedAmountAcumulative);
        if (!discountCode.isEmpty()) {
            System.out.println("Codigo de descuento: " + discountCode);
        }
        String idBuy = generateIdBuy();
        String Date = LocalDate.now().toString();

        double pricePepperoniBig = 6500.0 + (6500.0 * 0.10);
        double priceHawaianaMedium = 5500.0 + (5500.0 * 0.05);

        double promotionDayBrute = priceHawaianaMedium + pricePepperoniBig;

        int opcPromotionDay;
        System.out.println("----------------------------");
        System.out.println("Promocion del dia!");
        System.out.println("1. Pizza de Pepperoni (Pasta Delgada, Grande)");
        System.out.println("----------------------------");
        System.out.println("2. Pizza Hawaiana (Pasta Gruesa, Mediana)");
        System.out.println("----------------------------");
        System.out.println("3. Productos adicionales(Opcional): ");
        System.out.println("1. Refresco");
        System.out.println("2. Pan de Ajo");
        System.out.println("3. Postre");
        System.out.println("4. Ninguno");
        System.out.println("----------------------------");
        System.out.println("Precio Bruto de la promocion sin contar el producto adicional: " + promotionDayBrute);
        System.out.println("----------------------------");
        System.out.println("Desea la promocion del dia?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.println("----------------------------");
        System.out.println("Si anteriormente selecciono alguna pizza. Esta se descartara y se comprara unicamente las de la promocion!");

        opcPromotionDay = scanner.nextInt();

        if (opcPromotionDay == 1) {
            System.out.println("Productos adicionales: ");
            System.out.println("1. Refresco");
            System.out.println("2. Pan de Ajo");
            System.out.println("3. Postre");
            System.out.println("4. Ninguno");

            int opcAdditionalDayPromotion = scanner.nextInt();
            String promotionDayAditionals = "";
            double baseCostAdditionalDayPromotion = 0.0;

            switch (opcAdditionalDayPromotion) {
                case 1:
                    promotionDayAditionals = "Refresco";
                    baseCostAdditionalDayPromotion = 1500.0;
                    break;
                case 2:
                    promotionDayAditionals = "Pan de Ajo";
                    baseCostAdditionalDayPromotion = 1000.0;
                    break;
                case 3:
                    promotionDayAditionals = "Postre";
                    baseCostAdditionalDayPromotion = 1500.0;
                    break;
                case 4:
                    promotionDayAditionals = "Ninguno";
                    baseCostAdditionalDayPromotion = 0.0;
                    break;
                default:
                    System.out.println("Intentelo de nuevo.");
                    return;
            }

            Pizza pizzaPepperoni = Pizza.getPizzaTypes().get(0);
            ArrayList<String> ingredientsPizzaPepperoni = pizzaPepperoni.getIngredients();

            String idBuyPromotion = generateIdBuy();
            String promotionPizzaName1 = "Pepperoni";
            String promotionTypeOfPaste1 = "Delgada";
            String promotionPizzaSize1 = "Grande";
            String ingredientsPizzaPepperoniStr = String.join(", ", ingredientsPizzaPepperoni);
            String promotionDate = LocalDate.now().toString();
            int promotionDiscountedAmount = 0;

            Pizza pizzaHawaina = Pizza.getPizzaTypes().get(1);
            ArrayList<String> ingredientsPizzaHawaina = pizzaHawaina.getIngredients();

            String promotionPizzaName2 = "Hawaina";
            String promotionTypeOfPaste2 = "Gruesa";
            String promotionPizzaSize2 = "Mediana";
            String ingredientsPizzaHawainaStr = String.join(", ", ingredientsPizzaHawaina);

            BuyDetails promotionDetails1 = new BuyDetails(idBuyPromotion, promotionPizzaName1, promotionTypeOfPaste1, promotionPizzaSize1, ingredientsPizzaPepperoniStr,
                    "", "", promotionDate, pricePepperoniBig, promotionDiscountedAmount);

            BuyDetails promotionDetails2 = new BuyDetails(idBuyPromotion, promotionPizzaName2, promotionTypeOfPaste2, promotionPizzaSize2, ingredientsPizzaHawainaStr,
                    promotionDayAditionals, "", promotionDate, priceHawaianaMedium, promotionDiscountedAmount);

            List<BuyDetails> promotionList = new ArrayList<>();
            promotionList.add(promotionDetails1);
            promotionList.add(promotionDetails2);

            BuyDetailsDict.put(opcIdNumber, promotionList);

            System.out.println("Detalles de la compra:");
            for (BuyDetails promotionDetail : promotionList) {
                System.out.println("ID de compra: " + promotionDetail.getIdBuy());
                System.out.println("Nombre de la pizza: " + promotionDetail.getPizzaName());
                System.out.println("Tipo de pasta: " + promotionDetail.getTypeOfPaste());
                System.out.println("Tamaño de la pizza: " + promotionDetail.getPizzaSize());
                System.out.println("Ingredientes: " + promotionDetail.getIngredients());
                System.out.println("Productos adicionales: " + promotionDetail.getAdditionals());
                System.out.println("Fecha: " + promotionDetail.getDate());
                System.out.println("Monto total bruto: " + promotionDetail.getGrossTotalAmount());
                System.out.println("Monto total con descuento: " + promotionDetail.getDiscountedAmount());
                System.out.println("-----------------------------------");
            }
            double finalPromotionPrice = promotionDayBrute + baseCostAdditionalDayPromotion;
            System.out.println("Monto Total Bruto con adicional: " + finalPromotionPrice);
            System.out.println("Que disfrute!");
            mainMenu();
        } else if (opcPromotionDay == 2) {
            System.out.println("No se selecciono la promocion del dia.");
            System.out.println("Monto Total Bruto: " + grossTotalAmountAcumulative);
            System.out.println("Monto Total con Descuento: " + discountedAmountAcumulative);
        } else {
            System.out.println("Error: Ingrese una opcion valida 1. Si / 2. No");
        }

        BuyDetails clientBuyDetails = new BuyDetails(idBuy, selectedPizza.getName(), pasta, type, String.join(", ", ingredients), additionals, discountCode, Date, (int) grossTotalAmount, (int) discountedAmount);

        // Verifica si el cliente ya tiene una lista de compras asociada
        if (BuyDetailsDict.containsKey(opcIdNumber)) {
            BuyDetailsDict.get(opcIdNumber).add(clientBuyDetails);
        } else {
            List<BuyDetails> buyDetailsList = new ArrayList<>();
            buyDetailsList.add(clientBuyDetails);
            BuyDetailsDict.put(opcIdNumber, buyDetailsList);
        }
        return;
    }
    // menuSignUp funciones

    public static void clientSignUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su cedula: ");
        int opcIdNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese su nombre: ");
        String opcName = scanner.nextLine();

        String genre = "";
        while (true) {
            System.out.println("Ingrese su genero:");
            System.out.println("1. Masculino");
            System.out.println("2. Femenino");
            int opcGenre = scanner.nextInt();
            scanner.nextLine();

            if (opcGenre == 1) {
                genre = "Masculino";
                break;
            } else if (opcGenre == 2) {
                genre = "Femenino";
                break;
            } else {
                System.out.println("Error: Ingrese una opcion valida. Intentelo de nuevo");
            }
        }
        int opcProvince;
        System.out.println("Seleccione su provincia: ");
        for (int i = 0; i < Registro.provinces.size(); i++) {
            System.out.println((i + 1) + ". " + Registro.provinces.get(i));
        }
        opcProvince = scanner.nextInt();
        scanner.nextLine();
        String convertedProvince = Registro.provinces.get(opcProvince - 1);

        Registro newClient = new Registro(opcIdNumber, opcName, genre, convertedProvince, false);
        Registro.signUpClient.put(opcIdNumber, newClient);
        System.out.println("Cliente registrado exitosamente");
        printNewClientInfo(newClient);
        mainMenu();
    }

    public static void printNewClientInfo(Registro client) {
        System.out.println("Informacion del cliente:");
        System.out.println("Cedula: " + client.getIdNumber());
        System.out.println("Nombre: " + client.getName());
        System.out.println("Genero: " + client.getGenre());
        System.out.println("Provincia: " + client.getProvince());
        System.out.println("--------------------------------------");
    }

    public static void editClientInfo() {
        Scanner scanner = new Scanner(System.in);

        int opcIdNumber;
        System.out.println("Ingrese su cedula: ");
        opcIdNumber = scanner.nextInt();
        scanner.nextLine();

        if (Registro.signUpClient.containsKey(opcIdNumber)) {
            Registro clientToEditInfo = Registro.signUpClient.get(opcIdNumber);

            int opcMenu;
            System.out.println("1. Cambiar Nombre");
            System.out.println("2. Cambiar genero");
            System.out.println("3. Cambiar provincia");
            opcMenu = scanner.nextInt();

            boolean updated = false;

            switch (opcMenu) {
                case 1:
                    String newName = "";
                    System.out.println("Ingrese el nuevo nombre: ");
                    scanner.nextLine();
                    newName = scanner.nextLine();
                    clientToEditInfo.setName(newName);
                    System.out.println("Cambio realizado con exito!");
                    updated = true;
                    break;

                case 2:
                    String newGenre = "";
                    int opcGenre;
                    System.out.println("Ingrese el nuevo genero");
                    System.out.println("1. Masculino");
                    System.out.println("2. Femenino");

                    opcGenre = scanner.nextInt();
                    if (opcGenre == 1) {
                        newGenre = "Masculino";
                    } else if (opcGenre == 2) {
                        newGenre = "Femenino";
                    } else {
                        System.out.println("Error: Ingrese una opcion valida. Intentelo de nuevo");
                        break;
                    }
                    clientToEditInfo.setGenre(newGenre);
                    System.out.println("Genero actualizado correctamente!");
                    updated = true;
                    break;

                case 3:
                    int opcProvince;
                    System.out.println("Seleccione la provincia a la que desea cambiar");
                    for (int i = 0; i < Registro.provinces.size(); i++) {
                        System.out.println(i + 1 + ". " + Registro.provinces.get(i));
                    }
                    opcProvince = scanner.nextInt();

                    String province = Registro.provinces.get(opcProvince - 1);
                    clientToEditInfo.setProvince(province);
                    System.out.println("Provincia actualizada correctamente!");
                    updated = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            if (updated) {
                System.out.println("Informacion actualizada correctamente: ");
                printNewClientInfo(clientToEditInfo);
                mainMenu();
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public static void deleteClient() {
        Scanner scanner = new Scanner(System.in);

        int opcIdNumber;
        System.out.println("Ingrese la cedula: ");
        opcIdNumber = scanner.nextInt();

        if (BuyDetailsDict.containsKey(opcIdNumber)) {
            System.out.println("Este usuario ya ha comprado un producto. No puede eliminar la cuenta.");
            return;
        }
        if (Registro.signUpClient.containsKey(opcIdNumber)) {
            Registro.signUpClient.remove(opcIdNumber);
            System.out.println("El cliente fue borrado exitosamente");
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public static String generateDiscountCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public static String generateIdBuy() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public static boolean hasUserAlreadyBoughtThisPizza(int opcIdNumber, String pizzaName) {
        if (BuyDetailsDict.containsKey(opcIdNumber)) {
            List<BuyDetails> buyDetailsList = BuyDetailsDict.get(opcIdNumber);
            for (BuyDetails details : buyDetailsList) {
                if (details.getPizzaName().equals(pizzaName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
