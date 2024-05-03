package Cliente;

import java.rmi.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente {

    private static int PUERTO_SC;
    private static String IP;
    
public static void main(String[] args) {
    
    String configFilePath = "config_cliente.txt";
    BufferedReader configReader;
    Scanner scanner = new Scanner(System.in);
    boolean flag=true;

    try {
        configReader = new BufferedReader(new FileReader(configFilePath));
        PUERTO_SC = Integer.parseInt(configReader.readLine());
        IP = configReader.readLine();
        configReader.close();

        
        do {  
            System.out.println("Por favor, ingresa su consulta del Horoscopo y Clima de la forma: 'signo fecha' --- Si desea salir, ingrese 'Exit' ");
            System.out.println("Ejemplo: cancer 07/05/2024");
        
            String consulta = scanner.nextLine();

            if (!consulta.equalsIgnoreCase("exit")) {
            SCServiciosAbstracto server= (SCServiciosAbstracto) Naming.lookup("//"+IP+":"+PUERTO_SC+"/SCServicios");

            String resConsulta= server.consultarHoroscopoClima(consulta);
    
            System.out.println(resConsulta);} 
            else{
            flag=!flag;}
        } while (flag);

    } catch (Exception e) {
        e.printStackTrace();
    }

}


}