package Controller;

import java.util.Scanner;

public class Credentials {
    private String email;
    private String password;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void obtenerLogin() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el email: ");
        this.email=teclado.nextLine();
        System.out.println("Ingrese el password: ");
        this.password=teclado.nextLine();

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public void autoLogin() {
        this.email="lnecked@gmail.com";
        this.password="Ca03101989";
    }
}

