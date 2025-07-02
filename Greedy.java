import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*Estrategia Greedy:
    1-¿Cuáles son los candidatos?
    Los candidatos són las máquinas disponibles.

    2-¿Cuál es la estrategia de selección de cadidatos?
    Seleccionar la máquina que más piezas produzca, pero que no se pase de las piezas objetivo a producir.
    Esto prioriza usar primero las máquinas más productivas, con la idea de minimizar la cantidad total de puestas en funcionamiento.

    Para optimizar se deben ordenar las máquinas antes según la cantidad de piezas que producen (de mayor a menor).
    También se debe recorre la lista de máquinas ordenadas y, para cada una, se debe calcular cuántas veces puede usarse 
    sin superar el número de piezas restantes (división entera), para así evitar que entre más de una vez al ciclo por la misma máquina.
    Se usan esas veces consecutivamente, antes de pasar a la siguiente máquina.
 

    3- Consideraciones respecto a encontrar o no solución:
    Con la estrategia greedy no siempre se llegan a soluciones totalmente óptimas, ya que se obtiene la solución más eficiente (costo computacional)
    en base a una decisión estratégica, por ende, no explora todas las combinaciones y, probablemente, posibles soluciones pero se puede usar para 
    obtener aproximaciones muy buenas, sin embargo hay una serie de problemas para los que se ha demostrado matemáticamente que la solución que se
    da siguiendo la estrategia greedy, siempre es la mejor solución.
    En el algoritmo greedy, es este caso,  si no se logra alcanzar exactamente el número de piezas requerido, se retorna `null` como indicación de que la 
    estrategia no tuvo éxito, y un cartel por consola que indica que "greedy no encontró solución".

    4-Costo de la solución:
    La cantidad de candidatos considerados (máquinas examinadas) se cuenta en la variable `estados`.
 */

public class Greedy {

    private List<Maquina> mejorSolucion;
    private int estados;

    public Greedy() {
        this.mejorSolucion = new ArrayList<>();
        this.estados = 0;
    }

    public Solucion greedy(int objetivo, List<Maquina> maquinas) {
        mejorSolucion.clear();
        estados=0;
        int piezasRestantes = objetivo;
        int piezasAcumuladas = 0;

        // Ordena de mayor a menor
        maquinas.sort(Comparator.comparingInt(Maquina::getPiezas).reversed());

        int i = 0;
        while (piezasRestantes > 0 && i < maquinas.size()) {
            Maquina maquina = maquinas.get(i);
            estados++;

            int piezas = maquina.getPiezas();
            int cantidadUsos = piezasRestantes / piezas;//Eso calcula cuántas veces podés usar la máquina actual sin pasarte,
                                                        //en una sola cuenta. Esto reduce la cantidad de iteraciones innecesarias (estados generados)

            for (int j = 0; j < cantidadUsos; j++) { //Con este for agregamos x veces la máquina, segun la cantidad de usos
                mejorSolucion.add(maquina);
                piezasAcumuladas += piezas;
                piezasRestantes -= piezas;
            }

            i++;
        }


        if (piezasAcumuladas == objetivo) { //Si sale porque se llegó a las piezas objetivo, retornar solución
            return new Solucion(new ArrayList<>(mejorSolucion), piezasAcumuladas, estados);
        } else {
            return null; // Greedy no encontró solución
        }

    }
}
