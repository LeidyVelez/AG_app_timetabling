package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Core.Configuracion;
import Core.FuncionesEvaluacion;
import Core.Utilidades;
import entities.Curso;
import entities.Materia;
import entities.Profesor;
import entities.ProfesorDictaCurso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StringGene;
import sesions.CursoFacadeLocal;
import sesions.MateriaFacadeLocal;
import sesions.ProfesorDictaCursoFacadeLocal;
import sesions.ProfesorFacadeLocal;

/**
 *
 * @author milton.fernandez
 */
public class EjecucionAG extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    private MateriaFacadeLocal materiaFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private ProfesorDictaCursoFacadeLocal profesorCursoFacade;
    @EJB
    private CursoFacadeLocal cursoFacade;
    Configuracion config = Configuracion.getInstance();
    Utilidades util;
    private int cantidadProfesores = config.getCantidadProfesores();
    private int cantidadCursos = config.getCantidadCursos();
    public int cantidadEras = config.getCantidadEras();
    public double mejorFC = config.getMejorFC();
    public int especiePorEra = config.getEspeciePorEra();
    public int sizeCromosoma = config.getSizeCromosoma();
    

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Aplicacion de AG|Colegio THeodoro Hertlz</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/hojaEstilo.css' />");
            out.println("</head>");
            out.println("<body>");
           
            this.util = new Utilidades(materiaFacade.findAll(), profesorFacade.findAll(), profesorCursoFacade.findAll(), cursoFacade.findAll());
            String encabezado = this.util.imprimirEncabezado();
            out.println(encabezado);
            String horario = this.util.mostrarCromosoma(this.crearCromosoma());
            String cursosXhora = this.util.publicarResultados();
            String texto = "<table><tr><td>" + horario + "</td><td></td><td>" + cursosXhora + "</td></tr></table>";
            out.println(texto);

           out.println("<br><hr>" + this.util.imprimirDatos() + "<br>");
            out.println(this.util.imprimirPiePagina());
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public IChromosome crearCromosoma() {
        try {

            //Configuramos JGAP
            Configuration configuracion = new DefaultConfiguration();
            /*
             Se llama a la metafuncion de restricciones de dominio
            con cada uno de los botones
             */
            FitnessFunction myFunc = new FuncionesEvaluacion(this.util.listaCursos(), this.util.listaProfesores(), this.util.listaMaterias(), this.util.listaCursosProfesor());
            /*
             Le indicamos a JGAP cual sera nuestra funcion de aptitud
             */
            configuracion.setFitnessFunction(myFunc);
            /*
             crear el cromosoma
             */
            Gene[] genoma = crearGenoma(configuracion);
            /*
             Creamos un individuo a partir de la configuracion de los genes anterior
             */

            configuracion.setSelectFromPrevGen(1);


            Chromosome posibleHorarioGrupo = new Chromosome(configuracion, genoma);
            configuracion.setSampleChromosome(posibleHorarioGrupo);
            configuracion.setPopulationSize(especiePorEra); //Creamos nuestra poblacion inicial


            //Creamos el genotipo de la poblacion
            //Recordemos que el genotipo se determina del fenotipo = la configuracion de los genes para un cromosoma particular
            Genotype population = Genotype.randomInitialGenotype(configuracion);
            double valmax = 0;
            int cant = 0;
            for(int i =0 ; i < 1; i++)
            {
            for (int m = 0; m < cantidadEras; m++) //50 iteraciones, cada iteracion sera una generacion
            {
                population.evolve();
                System.out.println("Iteracion #" + m);
                IChromosome mejor_individuo = population.getFittestChromosome(); //Obtenemos el mejor individuo para esta generacion
                System.out.println("Mejor Individuo de la generacion " + m + " :");
                System.out.println(mejor_individuo);
                System.out.println("Valor de aptitud obtenido:" + mejor_individuo.getFitnessValue());

            }
            }
            IChromosome bestSolutionSoFar = population.getFittestChromosome(); //mejor individuo obtenido

            System.out.println("Este es el mejor individuo encontrado despues de las generaciones:");

            config.mejorFC = bestSolutionSoFar.getFitnessValue();
            return bestSolutionSoFar;
        } catch (InvalidConfigurationException ex) {
            System.out.println("No se pudo ejecutar el AG");

        }
        return null;
    }

    public static Gene[] crearGenoma(Configuration configuracion) {
        try {
          
//tamaño de chromosome debe ser tomado de configuracion
            Gene[] genoma = new Gene[88];

            for (int i = 0; i < 88; i++) {
                if (i % 2 == 0) {
                    genoma[i] = new IntegerGene(configuracion, 1, 25);
                } else {
                    genoma[i] = new IntegerGene(configuracion, 1, 100);
                }
            }
            return genoma;
        } catch (InvalidConfigurationException ex) {
            System.out.println("No se pudo ejecutar el AG");
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
