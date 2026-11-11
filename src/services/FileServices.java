package services;

import model.Adestrador;
import model.Pokedex;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileServices {
    public void serializarPokedex(List<Pokedex> listaPokedex, String rutaArquivo) {
        try (FileOutputStream fos = new FileOutputStream(rutaArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(listaPokedex);
            
            System.out.println("Entradas de Pokedex serializadas en: " + rutaArquivo);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void exportarAdestradores(List<Adestrador> adestradores, String ruta) {
        XMLOutputFactory fabrica = XMLOutputFactory.newInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            FileWriter fichero = new FileWriter(ruta);
            XMLStreamWriter creacion = fabrica.createXMLStreamWriter(fichero);

            creacion.writeStartDocument("UTF-8", "1.0");
            creacion.writeCharacters("\n");
            creacion.writeStartElement("adestradores");
            creacion.writeCharacters("\n");

            for (Adestrador a : adestradores) {
                creacion.writeStartElement("adestrador");
                creacion.writeAttribute("id", String.valueOf(a.getId()));
                creacion.writeCharacters("\n");

                creacion.writeStartElement("nome");
                creacion.writeCharacters(a.getNome() != null ? a.getNome() : "");
                creacion.writeEndElement();
                creacion.writeCharacters("\n");

                creacion.writeStartElement("nacemento");
                String dataStr = (a.getNacemento() != null) ? sdf.format(a.getNacemento()) : "";
                creacion.writeCharacters(dataStr);
                creacion.writeEndElement();
                creacion.writeCharacters("\n");

                creacion.writeEndElement();
                creacion.writeCharacters("\n");
            }

            creacion.writeEndElement();
            creacion.writeEndDocument();

            creacion.flush();
            creacion.close();
            fichero.close();

            System.out.println("Exportación XML en: " + ruta);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Pokedex> deserializar(String ruta) {
        File escritura = new File(ruta);
        List<Pokedex> pokemons = null;

        try (FileInputStream f_entrada = new FileInputStream(escritura);
             ObjectInputStream o_entrada = new ObjectInputStream(f_entrada)) {

            pokemons = (List<Pokedex>) o_entrada.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pokemons;
    }
}
