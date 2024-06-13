/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriodosjavaa;

import java.util.HashMap;
import java.util.ArrayList;

public class Registro {

    private int idNumber;
    private String name;
    private String genre;
    private String province;
    private boolean isAdmin;

    public static HashMap<Integer, Registro> signUpClient = new HashMap<>();
    public static ArrayList<String> provinces = new ArrayList<>();

    static {
        Registro USERADMIN = new Registro(208790566, "Jeremy", "Masculino", "Alajuela", true);
        signUpClient.put(USERADMIN.getIdNumber(), USERADMIN);
    }

    static {
        provinces.add("San Jose");
        provinces.add("Alajuela");
        provinces.add("Cartago");
        provinces.add("Heredia");
        provinces.add("Guanacaste");
        provinces.add("Puntarenas");
        provinces.add("Limon");
    }
    
    public Registro(int idNumber, String name, String genre, String province, boolean isAdmin) {
        this.idNumber = idNumber;
        this.name = name;
        this.genre = genre;
        this.province = province;
        this.isAdmin = isAdmin;
    }

    // Getters y setters para todas las propiedades
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }
}
