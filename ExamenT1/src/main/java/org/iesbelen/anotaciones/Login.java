package org.iesbelen.anotaciones;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// usuario: usuario1
// passwd: admin <-> hashPasswd: 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
@Credencial(usuario = "usuario1",
        hashPasswd = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
// usuario: usuario2
// passwd: admin1234 <-> hashPasswd: ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270
@Credencial(usuario = "usuario2",
        hashPasswd = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270")
public class Login {
        List<Credencial> listaCredencial;

        public Login() throws NoSuchAlgorithmException {
            listaCredencial = new ArrayList<Credencial>();
            login();
            cargadorContexto();
        }

        public void login() throws NoSuchAlgorithmException {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese su usuario: ");
            String usuario = sc.nextLine();
            System.out.println("Ingrese su contraseña: ");
            String contrasena = sc.nextLine();

            for (Credencial credencial : listaCredencial) {
                if (usuario.equals(credencial.usuario()) && clavehas.hashPassword(contrasena).equals(credencial.hashPasswd())) {
                    System.out.println("ACCESO CONCEDIDO");
                } else {
                    System.out.println("ACCESO DENEGADO");
                }
            }
        }

        public void cargadorContexto() {
            Credencial[] credencial = Login.class.getAnnotationsByType(Credencial.class);
            Collections.addAll(listaCredencial, credencial);
        }

}