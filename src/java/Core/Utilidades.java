package Core;


import entities.Curso;
import entities.Materia;
import entities.Profesor;
import entities.ProfesorDictaCurso;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;

import org.jgap.Gene;
import org.jgap.IChromosome;
import sesions.CursoFacade;
import sesions.MateriaFacade;
import sesions.ProfesorDictaCursoFacade;
import sesions.ProfesorFacade;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milton.fernandez
 */
public class Utilidades {
    /*
     * uso del patron singleton
     */
    Configuracion config = Configuracion.getInstance();
    public  int cantidadEras = config.getCantidadEras();
    public  double mejorFC = config.getMejorFC();
    public  int especiePorEra = config.getEspeciePorEra();
    public  int  sizeCromosoma = config.getSizeCromosoma();
    Vector cursosGenerados = new Vector();
    Vector horasCurso = new Vector();

    private List<Materia> materiaFacade;

    private List<Profesor> profesorFacade;

    private List<ProfesorDictaCurso> profesorCursoFacade;

    private List<Curso> cursoFacade;
    
    public Utilidades(List<Materia> mf ,List<Profesor> pf ,List<ProfesorDictaCurso>  pcf ,List<Curso> cf)
    {
       if(mf==null)
       {
           System.out.println("eeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
       }
        this.materiaFacade = mf;
        this.profesorFacade = pf;
        this.profesorCursoFacade = pcf;
        this.cursoFacade = cf;
    }
     public void guardarCurso(int codCurso) {
        boolean bandera = false;
        /*
         * verifica que el curso no se encuentre dentro del vector cursos generados
         */
        for (int i = 0; i < cursosGenerados.size(); i++) {
            if ((Integer) cursosGenerados.get(i) == codCurso) {
                int horasCurso = (Integer) this.horasCurso.get(i);
                /*
                 * incrementamos cantidad de horas del curso
                 */
                this.horasCurso.set(i, horasCurso + 1);
                bandera = true;
                break;
            }
        }
        if (bandera == false) {
            this.cursosGenerados.add(codCurso);
            this.horasCurso.add(1);
        }
    }
     
     
//         private void generarCursos()
//    {
//        int cantidadMaterias = this.materiaFacade.size();
//        List<Materia> listaMaterias = this.materiaFacade.findAll();
//        int aux = 1;
//        for(int i=1; i < 6; i++)
//        {
//            
//            for(int j = 0; j < cantidadMaterias ; j++)
//            {
//                Curso c = new Curso();
//                Materia m = listaMaterias.get(j);
//                c.setIdGrado(i);
//                c.setIdMateria(m.getIdMateria());
//                c.setId(aux);
//                this.cursoFacade.create(c);
//                aux++;
//            }
//            
//        }
//    }

     
     public String publicarResultados() {
        int cantidadHoras = 0;
        String texto = ""
                + "<div class=datagrid>cantidad total de cursos:" + cursosGenerados.size()
                + "<table>"
                + "<thead>"
                + "<tr>"
                + "<th>Curso</th>"
                + "<th>Intensidad  Cromosoma</th>"
                + "<th>Intensidad Real</th>"
                + "</tr></thead>"
                + "<tbody>";
        for (int i = 0; i < cursosGenerados.size(); i++) {
            if (i % 2 == 0) {
                texto += "<tr >";
            } else {
                texto += "<tr>";
            }
            int horasCromosomaCurso = (Integer) this.horasCurso.get(i);
            int horasRealCurso = this.hallarIntensidadMateria(hallarIdMateria((Integer) cursosGenerados.get(i)));
            if (horasCromosomaCurso != horasRealCurso) {
                texto += "<td bgcolor= #5F9EA0>" + this.imprimirNombresCurso((Integer) cursosGenerados.get(i)) + "</td><td bgcolor= #5F9EA0>" + horasCromosomaCurso + "</td><td bgcolor= #5F9EA0>"
                        + horasRealCurso + "</td></tr>";
            } else {
                texto += "<td>" + this.imprimirNombresCurso((Integer) cursosGenerados.get(i)) + "</td><td>" + horasCromosomaCurso + "</td><td>"
                        + horasRealCurso + "</td></tr>";
            }
            cantidadHoras += (Integer) this.horasCurso.get(i);

        }
        texto += "<tr class=alt><td>Total Horas</td><td>" + cantidadHoras + "</td></tr>";
        texto += "</tbody></table></div>";
        return texto;
    }
     
     
     private String imprimirNombresProfesor(int c) {
        String texto = "";
        int cantidadProfesores = this.profesorFacade.size();
        List<Profesor> listaProfesor = this.profesorFacade;
        for (int i = 0; i < cantidadProfesores; i++) {
            Profesor p = listaProfesor.get(i);
            if (p.getIdProfesor() == c) {
                return p.getNombres();
            }
        }
        return "NINGUNO";
    }

     
     /*Se modifica el método dado que la intensidad es de cada curso de cada materia, pues
     matemáticas de primero no es igual para el caso de onceavo grado */
    private String hallarNombreMateria(int idMateria) {
        int cantidadMateria = this.materiaFacade.size();
        List<Materia> listaMaterias = this.materiaFacade;
        for (int i = 0; i < cantidadMateria; i++) {
            Materia m = listaMaterias.get(i);
            if (m.getMateriaPK().getIdMateria()== idMateria) {
                return m.getNombreMateria();
            }
        }
        return "Non";
    }

    private int hallarIntensidadMateria(int idMateria) {
        int cantidadMateria = this.cursoFacade.size();
        List<Curso> listaCursos = this.cursoFacade;
        for (int i = 0; i < cantidadMateria; i++) {
            Curso c = listaCursos.get(i);
            if (c.getMateriaidMateria()== idMateria) {
                return c.getIntensidad();
            }
        }
        return -1;
    }

    private String imprimirNombresCurso(int idCurso) {
        String texto = "";
        if(this.cursoFacade == null)
        {
            System.out.println("malo");
        }else
        {
            
        
        int cantidadCursos = this.cursoFacade.size();
        List<Curso> listaCursos = this.cursoFacade;
        for (int i = 0; i < cantidadCursos; i++) {
            Curso c = listaCursos.get(i);
            if (c.getId() == idCurso) {
                int grado = c.getCodGrado();
                return this.hallarNombreMateria(c.getMateriaidMateria()) + "" + grado;
            }
        }
        }
        return "NC";
    }

    private int hallarIdMateria(int idCurso) {
        String texto = "";
        int cantidadCursos = this.cursoFacade.size();
        List<Curso> listaCursos = this.cursoFacade;
        for (int i = 0; i < cantidadCursos; i++) {
            Curso c = listaCursos.get(i);
            if (c.getId() == idCurso) {
                return c.getMateriaidMateria();

            }
        }
        return -1;
    }

    public Vector listaCursos() {
        Vector v = new Vector();

        int cantidadCursos = this.cursoFacade.size();
        List<Curso> listaCursos = this.cursoFacade;
        for (int i = 0; i < cantidadCursos; i++) {
            Curso c = listaCursos.get(i);
            v.add(c);
        }
        
        return v;
    }

    public Vector listaCursosProfesor() {
        Vector v = new Vector();
        int cantidadCursos = this.profesorCursoFacade.size();
        List<ProfesorDictaCurso> listaCursos = this.profesorCursoFacade;
        for (int i = 0; i < cantidadCursos; i++) {
            ProfesorDictaCurso c = listaCursos.get(i);
            v.add(c);
        }
        return v;
    }

    public Vector listaProfesores() {
        Vector v = new Vector();
        int cantidadProfesores = this.profesorFacade.size();
        List<Profesor> listaProfesor = this.profesorFacade;
        for (int i = 0; i < cantidadProfesores; i++) {
            Profesor p = listaProfesor.get(i);
            v.add(p);
        }
        return v;
    }

    public Vector listaMaterias() {
        Vector v = new Vector();
        int cantidadMaterias = this.materiaFacade.size();
        List<Materia> listaMaterias = this.materiaFacade;
        for (int i = 0; i < cantidadMaterias; i++) {
            Materia m = listaMaterias.get(i);
            v.add(m);
        }
        return v;
    }
    
    
    public String imprimirEncabezado() {
        String text = "<div class=datagrid><h1>Colegio THeodoro Hertlz</h1></div>";
        return text;
    }

    public String imprimirPiePagina() {
        String text = "<div class=datagrid>Colegio THeodoro Hetlz|Universidad de Antioquia|Fac.de Ingeniería|Departamento de Sistemas</div>";
        return text;
    }

    public String imprimirDatos() {
        this.mejorFC = this.config.mejorFC;
        String text = "<div class=datagrid>"
                + "<table>"
                + "<thead>"
                + "<tr>"
                + "<th>Número de Eras</th>"
                + "<th>Especies por Era</th>"
                + "<th>tamaño del genoma</th>"
                + "<th>Valor de mejor Puntuación(Función de calidad)</th>"
                + "</tr></thead>"
                + "<tbody>";

        text += "<tr class=alt>"
                + "<td>" + this.cantidadEras + "</td>"
                + "<td>" + this.especiePorEra + "</td>"
                + "<td>" + this.sizeCromosoma + "</td>"
                + "<td>" + this.mejorFC + "</td>"
                + "</tr></table></div>";
        return text;
    }
    
    
    
    /*TTPsolucion retorna la mejor solución del AG*/
    
     public String mostrarCromosoma(IChromosome TTPsolucion) {
        String texto = ""
                + "<div class=datagrid>"
                + "<table>"
                + "<thead>"
                + "<tr>"
                + "<th>Lunes</th>"
                + "<th>Martes</th>"
                + "<th>Miercoles</th>"
                + "<th>Jueves</th>"
                + "<th>Viernes</th>"
                + "</tr></thead>"
                + "<tbody>";
/*Un cromosoma está compuesto por genes, el gen es un allelo
        */
        Gene[] genoma = TTPsolucion.getGenes();

        int codProfesor = 0;
        int l = 0;
        int aux = 0;

        String textoaux = "";
        Vector lunes=new Vector();
        Vector martes=new Vector();
        Vector miercoles=new Vector();
        Vector jueves=new Vector();
        Vector viernes=new Vector();
        textoaux += "<tr>";
        int aux2 = 1;
        int codCurso = 0;
        //Este for debe ser hasta el tamaño del chromosome
        for (int i = 1; i < 89; i = i + 2) {
              int c= (Integer) TTPsolucion.getGene(i - 1).getAllele();
           int c1 = (Integer) TTPsolucion.getGene(i).getAllele();
            if (aux2 < 5) {
                c = (Integer) TTPsolucion.getGene(i).getAllele();
                aux2++;
            } else {
                aux2 = 1;
                    textoaux += "</tr><tr>";
            }
            c = (Integer) TTPsolucion.getGene(i).getAllele();
            c1 = (Integer) TTPsolucion.getGene(i - 1).getAllele();
            this.guardarCurso(c);
//            textoaux += this.imprimirInfoClase(c,c1);
        }
        /*
         * para imprimir segun la estructura del cromosoma
         */
        int nroDia=1;
        int k = 1;
        for(int  i = 1 ; i < 89;i = i+2)
        {
           codCurso = (Integer) TTPsolucion.getGene(i - 1).getAllele();
           codProfesor = (Integer) TTPsolucion.getGene(i).getAllele();
           //this.guardarCurso(codProfesor);
           switch(nroDia)
           {
               case 1:
                    //profesor
                   lunes.add(codProfesor);
                   //curso
                   lunes.add(codCurso);
                   break;
               case 2:
                   //profesor
                   martes.add(codProfesor);
                   //curso
                   martes.add(codCurso);
                   
                   break;
               case 3:
                   //profesor
                   miercoles.add(codProfesor);
                   //curso
                   miercoles.add(codCurso);
                   
                   break;
               case 4:
                   //profesor
                   jueves.add(codProfesor);
                   //curso
                   jueves.add(codCurso);
                   
                   break;
               case 5:
                   //profesor
                   viernes.add(codProfesor);
                   //curso
                   viernes.add(codCurso);
                   break;
           }
           k++;
           if(k==10)
           {
               nroDia++;
               k = 1;
           }
        }
        viernes.add(-1);
        viernes.add(-1);
         System.out.println(viernes.get(0)+"|"+martes.get(4));
        /*
         * mostrar los vectores
         */
String linea ="";
        for(int i = 1 ; i < 18;i= i+2)
        {
            if((Integer)viernes.get(i)!=-1)
            {
            linea = "<tr>"+this.imprimirInfoClase((Integer)lunes.get(i-1),(Integer)lunes.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)martes.get(i-1),(Integer)martes.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)miercoles.get(i-1),(Integer)miercoles.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)jueves.get(i-1),(Integer)jueves.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)viernes.get(i-1),(Integer)viernes.get(i))+""
                    + "</tr>";
            }else
            {
                linea = "<tr>"+this.imprimirInfoClase((Integer)lunes.get(i-1),(Integer)lunes.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)martes.get(i-1),(Integer)martes.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)miercoles.get(i-1),(Integer)miercoles.get(i))+""
                    + ""+this.imprimirInfoClase((Integer)jueves.get(i-1),(Integer)jueves.get(i))+""
                    + "</tr>";
            }
            textoaux += linea;
        }

        
        texto += textoaux;


        texto += "</tbody></table></div>";

        return texto;
    }


    private String imprimirInfoClase(int c, int c1) {
       String text = "";
       Integer codProfesor = c;
       Integer codCurso = c1;
       if(codProfesor.equals(null) && codCurso.equals(null))
       {
           
       }else
       {
        if (this.imprimirNombresProfesor(c1) == this.imprimirNombreRealProfesor(c)) {
             text = "<td>" + this.imprimirNombresCurso(c) + "<br>"
                    + this.imprimirNombresProfesor(c1) + "</td>";
        }else
        {
             text = "<td bgcolor= #5F9EA0 border=1>" + this.imprimirNombresCurso(c) + "<br>"
                    + this.imprimirNombresProfesor(c1) + "<br><h4>"
                    + this.imprimirNombreRealProfesor(c)+"</h4></td>";
        }
       }
        return text;

    }

    private String imprimirNombreRealProfesor(int codCurso) {
        int cantidad = this.profesorCursoFacade.size();
        List<ProfesorDictaCurso> listaProfesorCurso = this.profesorCursoFacade;
        
        int codProfesor = 0;
        for (int i = 0; i < cantidad; i++) {
            ProfesorDictaCurso pc = listaProfesorCurso.get(i);
            if (pc.getCursoId() == codCurso) {
                codProfesor = pc.getCursoId();
                break;
            }
        }

        return this.imprimirNombresProfesor(codProfesor);
    }
     
     
     
        
}
