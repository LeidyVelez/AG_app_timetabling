/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios.AsignarCursoProfesor;

import entities.Curso;
import entities.Grado;
import entities.Materia;
import entities.Profesor;
import entities.ProfesorDictaCurso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesions.CursoFacade;
import sesions.CursoFacadeLocal;
import sesions.GradoFacade;
import sesions.GradoFacadeLocal;
import sesions.MateriaFacadeLocal;
import sesions.ProfesorDictaCursoFacadeLocal;
import sesions.ProfesorFacadeLocal;

/**
 *
 * @author milton.fernandez
 */
public class formAsignarCursoProfesor extends HttpServlet {

    @EJB
    private CursoFacadeLocal cursoFacade;
    @EJB
    private ProfesorDictaCursoFacadeLocal profesorCursoFacade;
    @EJB
    private MateriaFacadeLocal materiaFacade;
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private GradoFacadeLocal gradoFacade;

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
            out.println(this.formularioAsignaCurso());
            out.println(this.visualizarProfesoresAsignados());
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private String formularioAsignaCurso() {
        String texto = "";
        texto += "<h1><center>Asignar Curso a cada Profesor</center></h1>";
        texto += "<div class=datagrid><table><tr class=alt><td><form method='post'  ACTION=registrarProfesorCurso  >"
                + "Seleccionar Profesor:"
                + ""
                + this.imprimirListaProfesores()
                + ""
                + "Seleccionar Materia:"
                + ""
                + this.imprimirListaMaterias()
                + ""
                + "Seleccionar Grado:"
                + ""
                + this.imprimirListaGrados() + "<input type=\"submit\" value=AsignarCurso /></form></td></tr></table></div>"
                + "";


        return texto;
    }

    private String imprimirListaProfesores() {
        String texto = "<select id='listaProfesor' name='listaProfesor'>";
        int cantidadProfesores = this.profesorFacade.count();

        List<Profesor> listaProfesor = this.profesorFacade.findAll();
        for (int i = 0; i < cantidadProfesores; i++) {
            Profesor p = listaProfesor.get(i);
            texto += "<option value=" + p.getIdProfesor() + ">" + p.getNombres() + "</option>";
        }
        texto += "</select>";
        return texto;
    }

    private String imprimirListaMaterias() {
        String texto = "<select id='listaMateria' name='listaMateria'>";
        int cantidadMaterias = this.materiaFacade.count();

        List<Materia> listaMateria = this.materiaFacade.findAll();
        for (int i = 0; i < cantidadMaterias; i++) {
            Materia m = listaMateria.get(i);
            texto += "<option value=" + m.getMateriaPK().toString() + ">" + m.getNombreMateria() + "</option>";
        }
        texto += "</select>";
        return texto;
    }

    private String imprimirListaGrados() {
        String texto = "<select id='listaGrado' name='listaGrado'>";
        int cantidadGrados = this.gradoFacade.count();

        List<Grado> listaGrados = this.gradoFacade.findAll();
        for (int i = 0; i < cantidadGrados; i++) {
            Grado g = listaGrados.get(i);
            texto += "<option value=" + g.getIdGrado()+ ">" + g.getNombre() + "</option>";
        }
        texto += "</select>";
        return texto;
    }

    private String visualizarProfesoresAsignados() {
        int cantidadProfesoresAsignados = this.profesorCursoFacade.count();
        List<ProfesorDictaCurso> listaProfesorCurso = this.profesorCursoFacade.findAll();
        String texto = "<TR><div class=datagrid><table>"
                + "<thead>"
                + "<tr>"
                + "<th>Profesor</th>"
                + "<th>Curso</th>"
                + "</tr></thead><tbody>";
        
        for(int i = 0 ; i < cantidadProfesoresAsignados;i++)
        {
            ProfesorDictaCurso pc = listaProfesorCurso.get(i);
            texto += "<tr><td>"+this.imprimirNombresProfesor(pc.getProfesoridProfesor()) +"</td>"
                    + "<td>"+this.imprimirNombresCurso(pc.getCursoId()) +"</td></tr>";
        }
        texto += "</tbody></table></div>";
                return texto;
    }

    private String imprimirNombresProfesor(int c) {
        String texto = "";
        int cantidadProfesores = this.profesorFacade.count();
        List<Profesor> listaProfesor = this.profesorFacade.findAll();
        for (int i = 0; i < cantidadProfesores; i++) {
            Profesor p = listaProfesor.get(i);
            if (p.getIdProfesor() == c) {
                return p.getNombres();
            }
        }
        return "NINGUNO";
    }

    private String hallarNombreMateria(int idMateria) {
        int cantidadMateria = this.materiaFacade.count();
        List<Materia> listaMaterias = this.materiaFacade.findAll();
        for (int i = 0; i < cantidadMateria; i++) {
            Materia m = listaMaterias.get(i);
            if (m.getMateriaPK().getIdMateria() == idMateria) {
                return m.getNombreMateria();
            }
        }
        return "Non";
    }

    private String imprimirNombresCurso(int idCurso) {
        String texto = "";
        int cantidadCursos = this.cursoFacade.count();
        List<Curso> listaCursos = this.cursoFacade.findAll();
        for (int i = 0; i < cantidadCursos; i++) {
            Curso c = listaCursos.get(i);
            if (c.getId() == idCurso) {
                int grado = c.getId();
                return this.hallarNombreMateria(c.getId()) + "" + grado;
            }
        }
        return "NC";
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
