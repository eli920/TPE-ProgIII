import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
     public static void main(String[] args) {
        List<Maquina> maquinas = new ArrayList<>();
        int objetivo = 0;

        try {
            objetivo = LeerArchivo.leerArchivo("Configuracion.txt", maquinas);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Creamos instancia de Backtracking
        Backtracking b = new Backtracking();
        Solucion solBack = b.backtracking(objetivo, maquinas);

        // Creamos instancia de Greedy
        Greedy g = new Greedy();
        Solucion solGreedy = g.greedy(objetivo, maquinas);

        //Imprimimos soluciones
        solBack.imprimir("Backtracking");
        System.out.println();
        //Se corrige código para que muestre mensaje en caso de que no pueda encontrar solución.
        if (solGreedy != null) {
            solGreedy.imprimir("Greedy");
        } else {
            System.out.println("Greedy no encontró solución.");
        }
        
    }
}
