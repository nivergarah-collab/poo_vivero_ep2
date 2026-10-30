package cl.duoc.vivero.repository;
import cl.duoc.vivero.model.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.Writer;




public class PlantaRepository {
    private static final Path ARCHIVO_INTERIORES = Path.of("interiores.json");
    private static final Path ARCHIVO_EXTERIORES = Path.of("exteriores.json");
    private final List<Planta> plantas = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Planta> listar(){
        return new ArrayList<>(plantas);
    }

    private <T extends Planta> List<T> leerArchivo(Path ruta, Class<T> clase) throws IOException {
        if (!Files.exists(ruta)) {                                                                              // part 01
            return new ArrayList<>();
        }
        try (Reader lector = Files.newBufferedReader(ruta)) {                                                   // part 02.1
            Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
            List<T> leidas = gson.fromJson(lector, tipoLista);
            if (leidas == null) {                                                                               // part 02.1
                return new ArrayList<>();
            }
            for (T planta : leidas) {                                                                           // part 04 , espera el validador
                validar(planta);
            }
            return leidas;                                                                                      //part 02.1
        } catch (JsonParseException e) {
            throw new IOException("El archivo " + ruta.getFileName() + " no tiene un formato válido.", e);      // part 03
        }
    }

    private void validar(Planta planta) throws IOException {
        try {
            planta.setNombre(planta.getNombre());
            planta.setAlturaCm(planta.getAlturaCm());
            planta.setCantidad(planta.getCantidad());
        } catch (IllegalArgumentException e) {
            throw new IOException("Dato no válido en el archivo: " + e.getMessage(), e);
        }
        if (planta instanceof PlantaInterior interior && interior.getVolumenMacetaLitros() <= 0) {
            throw new IOException("Dato no válido en el archivo: el volumen de la maceta debe ser mayor que cero.");
        }
        if (planta instanceof PlantaExterior exterior
                && (exterior.getEspecie() == null || exterior.getEspecie().isBlank())) {
            throw new IOException("Dato no válido en el archivo: la especie no puede estar vacía.");
        }
    }

    public void cargar() throws IOException {
        List<PlantaInterior> interiores = leerArchivo(ARCHIVO_INTERIORES, PlantaInterior.class);
        List<PlantaExterior> exteriores = leerArchivo(ARCHIVO_EXTERIORES, PlantaExterior.class);
        plantas.clear();
        plantas.addAll(interiores);
        plantas.addAll(exteriores);
    }

    private void escribirArchivo(Path ruta, List<? extends Planta> lista) throws IOException {
        try (Writer escritor = Files.newBufferedWriter(ruta)) {
            gson.toJson(lista, escritor);
        } catch (IOException | JsonIOException e) {
            throw new IOException("No se pudo guardar el archivo " + ruta.getFileName() + ".", e);
        }
    }

    private void guardar(List<Planta> lista) throws IOException {
        List<PlantaInterior> interiores = new ArrayList<>();                  // Creo 2 listas vacias
        List<PlantaExterior> exteriores = new ArrayList<>();
        for (Planta planta : lista) {                                         // Recorro la lista de plantas y las separo segun tipo
            if (planta instanceof PlantaInterior interior) {
                interiores.add(interior);
            } else if (planta instanceof PlantaExterior exterior) {
                exteriores.add(exterior);
            }
        }
        escribirArchivo(ARCHIVO_INTERIORES, interiores);                       // Agrego a sus respectivos lugares
        escribirArchivo(ARCHIVO_EXTERIORES, exteriores);
    }

    public void agregar(Planta planta) throws IOException {
    List<Planta> copia = new ArrayList<>(plantas);
    copia.add(planta);
    guardar(copia);
    plantas.add(planta);
    }

    public void eliminar(Planta planta) throws IOException {
        List<Planta> copia =  new ArrayList<>(plantas);                         // primero creo la copia
        copia.remove(planta);
        guardar(copia);
        plantas.remove(planta);
    }





}
