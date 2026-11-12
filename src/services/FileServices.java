package services;

import model.Adestrador;
import model.Pokedex;

import javax.xml.stream.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public List<Adestrador> importarAdestrador(String ruta) {
        List<Adestrador> lista = new ArrayList<>();
        XMLInputFactory xif = XMLInputFactory.newInstance();

        try (FileReader fr = new FileReader(ruta)) {
            XMLStreamReader xsr = xif.createXMLStreamReader(fr);
            Adestrador actual = null;
            StringBuilder texto = new StringBuilder();

            while (xsr.hasNext()) {
                int evento = xsr.next();

                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:
                        texto.setLength(0);
                        if ("Adestrador".equals(xsr.getLocalName())) {
                            String idAttr = xsr.getAttributeValue(null, "id");
                            if (idAttr != null) {
                                actual = new Adestrador();
                                actual.setId(Long.parseLong(idAttr));
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        texto.append(xsr.getText());
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        String etiqueta = xsr.getLocalName();

                        if (actual != null) {
                            if ("Nome".equals(etiqueta)) {
                                actual.setNome(texto.toString().trim());
                            } else if ("Data".equals(etiqueta)) {
                                String fechaStr = texto.toString().trim();
                                if (!fechaStr.isEmpty() && !"null".equals(fechaStr)) {
                                    actual.setNacemento(parsearFecha(fechaStr));
                                }
                            } else if ("Adestrador".equals(etiqueta)) {
                                lista.add(actual);
                                actual = null;
                            }
                        }
                        break;
                }
            }
            xsr.close();
        } catch (Exception e) {
            System.err.println("Erro ao importar XML: " + e.getMessage());
        }

        return lista;
    }

    public Date parsearFecha(String fechaStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (Exception e) {
            try {
                return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(fechaStr);
            } catch (Exception ex) {
                return new Date();
            }
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
