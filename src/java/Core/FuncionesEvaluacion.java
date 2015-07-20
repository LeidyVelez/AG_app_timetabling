package Core;


import entities.Curso;
import entities.Materia;
import entities.Profesor;
import entities.ProfesorDictaCurso;
import java.util.Vector;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author milton.fernandez
 */
public class FuncionesEvaluacion extends FitnessFunction {

    Vector listaCursos;
    Vector listaProfesor;
    Vector cursosGenerados;
    Vector horasCurso;
    Vector listamaterias;
    Vector listaCursosProfesor;
    Vector cursosLunes;
    Vector bloquesXcurso;
    int cantDuras = 0;
    int cantFijas = 0;
    int cantBlandas = 0;
    private double fitness = 0;
    int horascorrrecta = 0;

    public FuncionesEvaluacion(Vector cursos, Vector profesor, Vector materias, Vector cursosProfesor) {
        this.listaCursos = cursos;
        this.listaProfesor = profesor;
        this.listamaterias = materias;
        this.listaCursosProfesor = cursosProfesor;
    }

    @Override
    protected double evaluate(IChromosome ic) {
        fitness = 0;
        cantDuras = 0;
        cantFijas = 0;
        cantBlandas = 0;
        horascorrrecta = 0;
        cursosGenerados = new Vector();
        horasCurso = new Vector();
        bloquesXcurso = new Vector();

        for (int i = 0; i < 88; i++) {

            if (i % 2 == 0) {
                Integer codProfesor = (Integer) ic.getGene(i).getAllele();
                evaluarProfesorExista(codProfesor);
            } else {
                Integer codCurso = (Integer) ic.getGene(i).getAllele();
                this.guardarCurso(3, codCurso);
            }
        }
        this.evaluarLaExistenciaDeTodosLosCursos();
        evaluarIntensidadHorariaDeCadaCurso();
        if (this.horascorrrecta == 20) {
            /*
             * otro ciclo
             */

            int aux = 0;
            int a = 0;
            for (int i = 0; i < 88; i = i + 2) {
                aux = i + 1;
                Integer codProfesor = (Integer) ic.getGene(i).getAllele();
                Integer codCurso = (Integer) ic.getGene(aux).getAllele();
                a += this.evaluarCursoEsDictadoPorProfesor(codProfesor, codCurso);
            }
            this.cantFijas += a;
            evaluarcursosDeUnaHoraNoLunes(ic);
//            evaluarCursosConIntensidadParSeanBloques(ic);
           evaluarEducacionFIsicaManana(ic);
        }

        cursosGenerados = new Vector();
        horasCurso = new Vector();
         bloquesXcurso = new Vector();
        this.fitness += this.cantFijas;
        this.fitness = this.fitness/100;
        return fitness;
    }
    /*
     * funciones duras
     */

    
    private void evaluarCursosConIntensidadParSeanBloques(IChromosome ic)
    {
      
        for (int i = 0; i < this.horasCurso.size(); i++) {
            if ((Integer) this.horasCurso.get(i) %2==0) {
                int cantidadHoras = (Integer)this.horasCurso.get(i);
                int codCurso = (Integer) this.cursosGenerados.get(i);
                for (int k = 1; k < 88; k = k + 2) {
                    int c = (Integer) ic.getGene(k).getAllele();
                    if(c==codCurso)
                    {
                         int c1 = (Integer) ic.getGene(k+2).getAllele();
                        if(c1 == codCurso)
                        {
                            this.cantFijas += 20;
                        }else
                        {
                            this.cantFijas += -20;
                        }
                    }
                }

            }
        }
    }
    
    private void evaluarEducacionFIsicaManana(IChromosome ic)
    {
        for (int i = 0; i < this.horasCurso.size(); i++) {
            if ((Integer) this.horasCurso.get(i) == 2) {
                int codCurso = (Integer) this.cursosGenerados.get(i);
                if(codCurso==41)
                {
                for (int k = 1; k < 84; k = k + 2) {
                    int c = (Integer) ic.getGene(k).getAllele();
                    if(c == codCurso)
                    {
                        int c1 = (Integer) ic.getGene(k+2).getAllele();
                        if(c==c1)
                        {
                            this.cantFijas += 1000;
                        }else
                        {
                            this.cantFijas += -1000;
                        }
                        
                    }
                }
                break;
                }

            }
    }
    }
    private void evaluarcursosDeUnaHoraNoLunes(IChromosome ic) {
        for (int i = 0; i < this.horasCurso.size(); i++) {
            if ((Integer) this.horasCurso.get(i) == 1) {
                int codCurso = (Integer) this.cursosGenerados.get(i);

                for (int k = 1; k < 18; k = k + 2) {
                    int c = (Integer) ic.getGene(k).getAllele();
                    if(c == codCurso)
                    {
                    this.cantFijas += -300;
                    }
                }

            }
        }

    }

    private int evaluarCursoEsDictadoPorProfesor(int codProfesor, int codCurso) {

        for (int i = 0; i < this.listaCursosProfesor.size(); i++) {
            ProfesorDictaCurso pc = (ProfesorDictaCurso) this.listaCursosProfesor.get(i);
            if (pc.getProfesoridProfesor()== codProfesor) {
                if (pc.getCursoId()== codCurso) {
                    return 100;
                }
            }
        }
        return 0;

    }

    private void guardarCurso(int grado, int codCurso) {

        if (evaluarCursosGrado(grado, codCurso) == 1) {
            boolean bandera = false;
            /*
             * verifica que el curso no se encuentre dentro del vector cursos generados
             */
            for (int i = 0; i < cursosGenerados.size(); i++) {
                if ((Integer) cursosGenerados.get(i) == codCurso) {
                    int hCurso = (Integer) this.horasCurso.get(i);
                    /*
                     * incrementamos cantidad de horas del curso
                     */

                    this.horasCurso.set(i, hCurso + 1);
                    bandera = true;
                    break;
                }
            }
            if (bandera == false) {
                this.cursosGenerados.add(codCurso);
                this.horasCurso.add(1);
            }
        }
    }

    private int evaluarCursosGrado(int grado, int codCurso) {
        int cantidadCursos = this.listaCursos.size();
        for (int i = 0; i < cantidadCursos; i++) {
            Curso c = (Curso) this.listaCursos.get(i);
            if (c.getCodGrado() == grado) {
                if (c.getId() == codCurso) {
                    this.cantFijas += 20;
                    return 1;
                }
            }
        }
        return -1;
    }

    private void evaluarProfesorExista(int codProfesor) {
        int cantidadProfesores = this.listaProfesor.size();

        for (int i = 0; i < cantidadProfesores; i++) {
            Profesor p = (Profesor) this.listaProfesor.get(i);
            if (p.getIdProfesor() == codProfesor) {
                this.cantFijas += 10;
                break;
            }

        }
    }

    private int evaluarLaExistenciaDeTodosLosCursos() {
        if (this.cursosGenerados.size() == 20) {
            this.cantFijas += 1000;
            return 1;
        }
        return 0;
    }

    private void evaluarIntensidadHorariaDeCadaCurso() {
        int a = 0;
        if (this.evaluarLaExistenciaDeTodosLosCursos() > 0) {
            for (int i = 0; i < cursosGenerados.size(); i++) {
                int codCurso = (Integer) this.cursosGenerados.get(i);
                for (int j = 0; j < this.listaCursos.size(); j++) {
                    Materia c = (Materia) this.listaCursos.get(j);
                    if (c.getMateriaPK().getIdMateria() == codCurso) {
                        int codMateria = c.getMateriaPK().getIdMateria();
                        for (int k = 0; k < this.listamaterias.size(); k++) {
                            Curso m = (Curso) this.listamaterias.get(k);
                            if (m.getMateriaidMateria() == codMateria) {
                                int intesidadHoraria = m.getIntensidad();
                                if ((Integer) this.horasCurso.get(i) == intesidadHoraria) {
                                    a += 100;
                                    this.horascorrrecta++;

                                    break;
                                }
                            }
                        }

                    }

                }
            }
        }
        this.cantFijas += a;
    }
}
