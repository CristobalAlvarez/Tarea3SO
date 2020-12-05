import java.io.*;
import java.io.FileReader;
import java.lang.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*; 

public class Main {

    public static void main(String[] args) {
        //  Contador para no leer la primera linea
        int i = 0;

        //  Hash donde se guardaran las funciones
        //  Ej:  f(x) -> 2*x+1
        Hashtable<String, String> funcion_dict = new Hashtable<String, String>();

        //  Abrir archivo con funciones
        File file = new File("funciones.txt");
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            //  Buffer lineas
            String line;

            // Leemos linea por linea
            while ((line = br.readLine()) != null) {

                if (i == 0) {
                    i = 1;
                } else {

                    //  Obtenemos nombre funcion y expresion
                    String[] funcion = line.replace("\n", "").replace(" ", "").split("=");
                    
                    //  Guardamos en tabla hash
                    funcion_dict.put(funcion[0], funcion[1]);

                }

            }

        } catch (IOException e) {
            System.out.print(e);
        }

        //  Inicio while programa
        System.out.println("Para salir ingrese SALIR.");
        while (true) {

            // Leemos input
            Scanner myObj = new Scanner(System.in);
            System.out.print("Ingresa una funcion: ");
            String func = myObj.nextLine();

            if (!func.equals("SALIR")) {

                try {

                    //  Numero a evaluar
                    String num = func.substring(2, func.length() - 1);
                    
                    //  Creamos hilo  
                    Funciones thread = new Funciones(func.substring(0, 1) + "(x)" , Double.parseDouble(num) , funcion_dict);

                    //  Iniciamos thread
                    thread.start();

                    //  Esperamos a que termine el hilo
                    thread.join();

                    //  Printeamos resultado
                    System.out.print(">");
                    System.out.println(thread.getResult());

                } catch (InterruptedException ie) {
                    System.out.println("Funcion invalida.");
                } catch (NumberFormatException e){
                    System.out.println("Funcion invalida.");
                }

            } else {
                //  Terminamos programa
                break;
            }

        }

    }

}