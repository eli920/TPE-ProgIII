import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeerArchivo {
     public static int leerArchivo(String path, List<Maquina> maquinas) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(path));
        int piezasTotales = Integer.parseInt(lineas.get(0).trim());

        for (int i = 1; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(",");
            maquinas.add(new Maquina(partes[0].trim(), Integer.parseInt(partes[1].trim())));
        }

        return piezasTotales;
    }
}
