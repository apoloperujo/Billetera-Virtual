package Java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;

public class ExportarComoCSV {

    public static boolean exportarActivosAArchivoCSV( 
            List<CriptoUsuario> criptoUsuarios, 
            List<FiduciariaUsuario> fiduciariaUsuarios, 
            String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.append("Tipo,Nombre,Monto\n");

            // Escribir los datos de CriptoUsuarios
            for (CriptoUsuario cripto : criptoUsuarios) {
                writer.append("Cripto")
                      .append(",")
                      .append(cripto.getNomenclatura()) // Nombre de la criptomoneda
                      .append(",")
                      .append(String.valueOf(cripto.getMoneda().getValorDolar()*cripto.getCantidad())) // Monto en criptomoneda
                      .append("\n"); // Salto de línea
            }

            // Escribir los datos de FiduciariaUsuarios
            for (FiduciariaUsuario fiduciaria : fiduciariaUsuarios) {
                writer.append("Fiduciaria")
                      .append(",")
                      .append(fiduciaria.getNomenclatura()) // Nombre de la moneda fiduciaria
                      .append(",")
                      .append(String.valueOf(fiduciaria.getMoneda().getValorDolar()*fiduciaria.getCantidad())) // Monto en fiduciaria
                      .append("\n"); // Salto de línea
            }

            System.out.println("Archivo CSV generado exitosamente: " + nombreArchivo);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}