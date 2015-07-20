package Core;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milton.fernandez
 */
public class Configuracion {

     private Configuracion()
     {
         
     };
/*Se establecen los parámetros de configuración del AG*/
     
     private static class configuracionMaker{
          public static Configuracion config = new Configuracion();
     }

     public static Configuracion getInstance(){
          return configuracionMaker.config;
     }

    public int getCantidadProfesores() {
        return cantidadProfesores;
    }

    public int getCantidadCursos() {
        return cantidadCursos;
    }

    public  int getCantidadEras() {
        return cantidadEras;
    }
//Fitness puntaje por estancia
    public double getMejorFC() {
        return mejorFC;
    }

    public  int getEspeciePorEra() {
        return especiePorEra;
    }

    public  int getSizeCromosoma() {
        return sizeCromosoma;
    }
    
    public   int cantidadEras = 2000;
    public  double mejorFC = 0;
    public   int especiePorEra = 400;
    public   int  sizeCromosoma = 88;
    private  int cantidadProfesores = 25;
    private  int cantidadCursos = 100;
}
