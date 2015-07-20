/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios.AsignarCursoProfesor;

import entities.Curso;
import entities.ProfesorDictaCurso;
import entities.Profesor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesions.CursoFacade;
import sesions.ProfesorDictaCursoFacade;

/**
 *
 * @author milton.fernandez
 */
public class registrarProfesorCurso extends HttpServlet {
  @EJB
    private CursoFacade cursoFacade;
    @EJB
    private ProfesorDictaCursoFacade profesorCursoFacade;
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
            out.println("<title>Servlet registrarProfesorCurso</title>");            
            out.println("</head>");
            out.println("<body>");
            int codProfesor = Integer.parseInt(request.getParameter("listaProfesor"));
            int codMateria = Integer.parseInt(request.getParameter("listaMateria"));
            int codGrado = Integer.parseInt(request.getParameter("listaGrado"));
            int codCurso = this.getCodigoCurso(codMateria, codGrado);
            ProfesorDictaCurso pck = new ProfesorDictaCurso();
            pck.setCursoId(codCurso);
            pck.setProfesoridProfesor(codProfesor);
          //Profesor pc = new Profesor(pck);
           // this.profesorCursoFacade.create(pc);
            out.println("Por corregir!!!!!!!!!!!!se ha asignado curso al profesor<a href=\"http://localhost:8080/Algoritmo_Genetico/formAsignarCursoProfesor\">Click para volver</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
 private int getCodigoCurso(int codMateria,int codGrado)
    {

        List<Curso> listaCursos = this.cursoFacade.findAll();
        for(int i = 0 ; i <listaCursos.size() ; i++)
        {
            Curso c = listaCursos.get(i);
            if(c.getCodGrado()== codGrado)
            {
                if(c.getMateriaidMateria()== codMateria)
                {
                    return c.getId();
                }
            }
        }
        
        
        return -1;
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
