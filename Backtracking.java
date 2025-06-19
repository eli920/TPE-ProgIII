import java.util.ArrayList;
import java.util.List;

/*
   Estrategia de resolución con Backtracking:

   1- ¿Cómo se genera el árbol de exploración?
   El árbol de exploración se genera probando, en cada nivel de profundidad, la puestas en funcionamiento de una nueva máquina.
   Debido a  que las máquinas pueden repetirse, se permite reutilizar la misma máquina varias veces.
   
   Cada nodo representa un estado intermedio con cierta cantidad de piezas acumuladas y una secuencia parcial
   de máquinas utilizadas.
   
   2- ¿Cuáles son los estados finales y los estados solución?
    Los estados finales (o de corte) son aquellos donde:
    Las piezas producidas llegan al objetivo (estado solución válido).

    Los estados solución son aquellos en los que la cantidad de piezas acumuladas es igual al objetivo.
    Entre esos, se busca la solución con la menor cantidad de máquinas usadas (Mejor candidato/Mejor solución).

    3- ¿Cuales son las posibles podas?
    Se poda si la solución actual es peor que la mejor encontrada.

    Se utiliza una estrategia de exploración por combinación con repetición: se permite volver a utilizar una máquina 
    ya usada, pero se evita generar permutaciones equivalentes al usar un índice "pos" que evita reiniciar la exploración 
    desde cero cada vez (evita explorar [M1, M2] y [M2, M1] si son equivalentes).

    4-
    Se registra la cantidad de estados generados como métrica de costo computacional.
*/

public class Backtracking {

    private List<Maquina> mejorSolucion;
    private int mejorCantidadMaquinas;
    private int estados;

    public Backtracking() {
        this.mejorSolucion = new ArrayList<>();
        this.mejorCantidadMaquinas = Integer.MAX_VALUE;
        this.estados = 0;
    }

    public Solucion backtracking(int objetivo, List<Maquina> maquinas) {

        back(maquinas, objetivo, 0, new ArrayList<>(), 0);
        return new Solucion(new ArrayList<>(mejorSolucion), objetivo, estados);
    }

    private void back(List<Maquina> maquinas, int objetivo, int piezasAcumuladas, List<Maquina> actualSolucion, int pos) {
        estados++;
        //Condición de corte
        if  (piezasAcumuladas == objetivo) {
            if (actualSolucion.size() < mejorCantidadMaquinas) {
                mejorCantidadMaquinas = actualSolucion.size();
                mejorSolucion.clear();                    
                mejorSolucion.addAll(actualSolucion);   
            }

        }else{
            
            for (int i = pos; i < maquinas.size(); i++) { // Usamos pos para explorar todas las combinaciones únicas posibles de máquinas (con repeticiones), sin repetir combinaciones que son iguales pero en distinto orden.
                actualSolucion.add(maquinas.get(i));

                if (piezasAcumuladas <= objetivo && actualSolucion.size() < mejorCantidadMaquinas) {
                    back(maquinas, objetivo, piezasAcumuladas + maquinas.get(i).getPiezas(), actualSolucion, i);
                }
                
                actualSolucion.remove(actualSolucion.size() - 1); 
            }
        }
       
    }
}
