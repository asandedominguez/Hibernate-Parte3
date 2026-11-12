import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import services.AdestradorServices;
import services.FileServices;
import services.PokedexServices;
import services.PokemonServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) {
        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();
        PokemonServices pokemonServices = new PokemonServices();
        FileServices fileServices = new FileServices();

        Date dataNacemento = new Date();

        System.out.println("Secuencia executada con obxectos e métodos hibernate:");

        System.out.println("Creando entradas na pokedex:");
        pokedexServices.crearPokemon("Bulbasaur", new BigDecimal("6.9"), "Planta/Veneno");
        pokedexServices.crearPokemon("Charmander", new BigDecimal("8.5"), "Fogo");
        pokedexServices.crearPokemon("Squirtle", new BigDecimal("9.0"), "Aga");
        pokedexServices.crearPokemon("Pikachu", new BigDecimal("6.0"), "Eléctrico");
        pokedexServices.crearPokemon("Jigglypuff", new BigDecimal("5.5"), "Normal/Fada");
        pokedexServices.crearPokemon("Meowth", new BigDecimal("4.2"), "Normal");
        pokedexServices.crearPokemon("Psyduck", new BigDecimal("19.6"), "Aga");
        pokedexServices.crearPokemon("Snorlax", new BigDecimal("460.0"), "Normal");
        pokedexServices.crearPokemon("Gengar", new BigDecimal("40.5"), "Pantasma/Veneno");
        pokedexServices.crearPokemon("Mewtwo", new BigDecimal("122.0"), "Psíquico");

        System.out.println("Creando adestradores:");
        adestradorServices.crearAdestrador("Ash Ketchum", new Date());
        adestradorServices.crearAdestrador("Misty", new Date());

        System.out.println("Lendo adestradores por nome:");
        Adestrador ash = adestradorServices.lerAdestradorPorNome("Ash Ketchum");
        Adestrador misty = adestradorServices.lerAdestradorPorNome("Misty");

        System.out.println("Lendo entradas da pokedex por nome:");
        Pokedex p1 = pokedexServices.lerPokemonPorNome("Bulbasaur");
        Pokedex p2 = pokedexServices.lerPokemonPorNome("Charmander");
        Pokedex p3 = pokedexServices.lerPokemonPorNome("Squirtle");
        Pokedex p4 = pokedexServices.lerPokemonPorNome("Pikachu");
        Pokedex p5 = pokedexServices.lerPokemonPorNome("Jigglypuff");
        Pokedex p6 = pokedexServices.lerPokemonPorNome("Meowth");
        Pokedex p7 = pokedexServices.lerPokemonPorNome("Psyduck");
        Pokedex p8 = pokedexServices.lerPokemonPorNome("Snorlax");
        Pokedex p9 = pokedexServices.lerPokemonPorNome("Gengar");
        Pokedex p10 = pokedexServices.lerPokemonPorNome("Mewtwo");

        System.out.println("Insertando pokemons:");
        pokemonServices.InsertarPokemon("Pikachu de Ash", dataNacemento, p4, ash);
        pokemonServices.InsertarPokemon("Charizard", dataNacemento, p2, ash);
        pokemonServices.InsertarPokemon("Bulbasaur", dataNacemento, p1, ash);
        pokemonServices.InsertarPokemon("Squirtle", dataNacemento, p3, ash);
        pokemonServices.InsertarPokemon("Snorlax", dataNacemento, p8, ash);
        pokemonServices.InsertarPokemon("Gengar", dataNacemento, p9, ash);

        pokemonServices.InsertarPokemon("Psyduck de Misty", dataNacemento, p7, misty);
        pokemonServices.InsertarPokemon("Jigglypuff", dataNacemento, p5, misty);
        pokemonServices.InsertarPokemon("Meowth", dataNacemento, p6, misty);
        pokemonServices.InsertarPokemon("Mewtwo", dataNacemento, p10, misty);
        pokemonServices.InsertarPokemon("Pikachu II", dataNacemento, p4, misty);
        pokemonServices.InsertarPokemon("Squirtle II", dataNacemento, p3, misty);

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println(a.toString());
        }

        System.out.println("Listado Pokemon:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Nacemento: " + pk.getNacemento() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Serializando entradas da pokedex:");
        List<Pokedex> pokedexSerializar = new ArrayList<>();
        pokedexSerializar.add(p1);
        pokedexSerializar.add(p2);
        fileServices.serializarPokedex(pokedexSerializar, "pokedex");

        System.out.println("Exportando adestradores a XML:");
        List<Adestrador> adestradoresExportar = new ArrayList<>();
        adestradoresExportar.add(ash);
        adestradoresExportar.add(misty);
        fileServices.exportarAdestradores(adestradoresExportar, "adestradores.xml");

        System.out.println("Actualizando entradas da pokedex:");
        p1.setNome("Bulbasaur Editado");
        p1.setPeso(new BigDecimal("7.5"));
        pokedexServices.actualizarPokemon(p1);

        p2.setNome("Charmander Editado");
        p2.setPeso(new BigDecimal("9.2"));
        pokedexServices.actualizarPokemon(p2);

        System.out.println("Actualizando adestradores:");
        ash.setNome("Ash Ketchum (Campión)");
        adestradorServices.actualizarAdestrador(ash);

        misty.setNome("Misty (Líder de Ximnasio)");
        adestradorServices.actualizarAdestrador(misty);

        System.out.println("Actualizando pokemons:");
        List<Pokemon> listaPokemons = pokemonServices.listarPokemon();
        Pokemon pk1 = listaPokemons.get(0);
        Pokemon pk2 = listaPokemons.get(1);
        Pokemon pk3 = listaPokemons.get(2);
        Pokemon pk4 = listaPokemons.get(3);

        pokemonServices.actualizarPokemon(pk1.getId(), "Pikachu Alfa", pk1.getNacemento(), pk1.getPokedex(), pk1.getAdestrador());
        pokemonServices.actualizarPokemon(pk2.getId(), "Charizard Peligroso", pk2.getNacemento(), pk2.getPokedex(), pk2.getAdestrador());
        pokemonServices.actualizarPokemon(pk3.getId(), "Bulbasaur Mega", pk3.getNacemento(), pk3.getPokedex(), pk3.getAdestrador());
        pokemonServices.actualizarPokemon(pk4.getId(), "Squirtle Alola", pk4.getNacemento(), pk4.getPokedex(), pk4.getAdestrador());

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
        }

        System.out.println("Listado Pokemons:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Deserializando e restaurando entradas da pokedex:");
        List<Pokedex> pokemonsDeserializados = fileServices.deserializar("pokedex");
        System.out.println("Entradas deserializadas:");
        for (Pokedex pDeserializado : pokemonsDeserializados) {
            Pokedex pEnBD = pokedexServices.lerPokemonPorId(pDeserializado.getId());
            if (pEnBD != null) {
                pEnBD.setNome(pDeserializado.getNome());
                pEnBD.setPeso(pDeserializado.getPeso());
                pEnBD.setMisc(pDeserializado.getMisc());
                pokedexServices.actualizarPokemon(pEnBD);
                System.out.println(pEnBD.getId() + " - " + pEnBD.getNome());
            }
        }

        System.out.println("Importando e restaurando adestradores desde XML:");
        List<Adestrador> adestradoresImportados = fileServices.importarAdestrador("adestradores.xml");
        for (Adestrador aImportado : adestradoresImportados) {
            Adestrador aEnBD = adestradorServices.lerAdestradorPorId(aImportado.getId());
            if (aEnBD != null) {
                aEnBD.setNome(aImportado.getNome());
                aEnBD.setNacemento(aImportado.getNacemento());
                adestradorServices.actualizarAdestrador(aEnBD);
                System.out.println(aEnBD.getId() + " - " + aEnBD.getNome());
            }
        }

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
        }

        System.out.println("Listado Pokemons:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Eliminando todos os rexistros das táboas:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            pokemonServices.eliminarPokemon(pk.getId());
        }
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            adestradorServices.eliminarAdestrador(a);
        }
        for (Pokedex p : pokedexServices.listarPokemons()) {
            pokedexServices.eliminarPokemon(p);
        }
        System.out.println("Vaziáronse as 3 táboas.");

        System.out.println("Secuencia executada coas queries especificadas:");

        System.out.println("Creando entradas na pokedex con query:");
        pokedexServices.crearPokemonQuery("Bulbasaur", new BigDecimal("6.9"), "Planta/Veneno");
        pokedexServices.crearPokemonQuery("Charmander", new BigDecimal("8.5"), "Fogo");
        pokedexServices.crearPokemonQuery("Squirtle", new BigDecimal("9.0"), "Aga");
        pokedexServices.crearPokemonQuery("Pikachu", new BigDecimal("6.0"), "Eléctrico");
        pokedexServices.crearPokemonQuery("Jigglypuff", new BigDecimal("5.5"), "Normal/Fada");
        pokedexServices.crearPokemonQuery("Meowth", new BigDecimal("4.2"), "Normal");
        pokedexServices.crearPokemonQuery("Psyduck", new BigDecimal("19.6"), "Aga");
        pokedexServices.crearPokemonQuery("Snorlax", new BigDecimal("460.0"), "Normal");
        pokedexServices.crearPokemonQuery("Gengar", new BigDecimal("40.5"), "Pantasma/Veneno");
        pokedexServices.crearPokemonQuery("Mewtwo", new BigDecimal("122.0"), "Psíquico");

        System.out.println("Creando adestradores con query:");
        adestradorServices.crearAdestradorQuery("Ash Ketchum", new Date());
        adestradorServices.crearAdestradorQuery("Misty", new Date());

        System.out.println("Lendo adestradores por nome:");
        Adestrador ashQ = adestradorServices.lerAdestradorPorNome("Ash Ketchum");
        Adestrador mistyQ = adestradorServices.lerAdestradorPorNome("Misty");

        System.out.println("Lendo entradas da pokedex por nome:");
        Pokedex p1Q = pokedexServices.lerPokemonPorNome("Bulbasaur");
        Pokedex p2Q = pokedexServices.lerPokemonPorNome("Charmander");
        Pokedex p3Q = pokedexServices.lerPokemonPorNome("Squirtle");
        Pokedex p4Q = pokedexServices.lerPokemonPorNome("Pikachu");
        Pokedex p5Q = pokedexServices.lerPokemonPorNome("Jigglypuff");
        Pokedex p6Q = pokedexServices.lerPokemonPorNome("Meowth");
        Pokedex p7Q = pokedexServices.lerPokemonPorNome("Psyduck");
        Pokedex p8Q = pokedexServices.lerPokemonPorNome("Snorlax");
        Pokedex p9Q = pokedexServices.lerPokemonPorNome("Gengar");
        Pokedex p10Q = pokedexServices.lerPokemonPorNome("Mewtwo");

        System.out.println("Insertando pokemons con query:");
        pokemonServices.InsertarPokemonQuery("Pikachu de Ash", dataNacemento, p4Q, ashQ);
        pokemonServices.InsertarPokemonQuery("Charizard", dataNacemento, p2Q, ashQ);
        pokemonServices.InsertarPokemonQuery("Bulbasaur", dataNacemento, p1Q, ashQ);
        pokemonServices.InsertarPokemonQuery("Squirtle", dataNacemento, p3Q, ashQ);
        pokemonServices.InsertarPokemonQuery("Snorlax", dataNacemento, p8Q, ashQ);
        pokemonServices.InsertarPokemonQuery("Gengar", dataNacemento, p9Q, ashQ);

        pokemonServices.InsertarPokemonQuery("Psyduck de Misty", dataNacemento, p7Q, mistyQ);
        pokemonServices.InsertarPokemonQuery("Jigglypuff", dataNacemento, p5Q, mistyQ);
        pokemonServices.InsertarPokemonQuery("Meowth", dataNacemento, p6Q, mistyQ);
        pokemonServices.InsertarPokemonQuery("Mewtwo", dataNacemento, p10Q, mistyQ);
        pokemonServices.InsertarPokemonQuery("Pikachu II", dataNacemento, p4Q, mistyQ);
        pokemonServices.InsertarPokemonQuery("Squirtle II", dataNacemento, p3Q, mistyQ);

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println(a.toString());
        }

        System.out.println("Listado Pokemon:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Nacemento: " + pk.getNacemento() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Serializando entradas da pokedex:");
        List<Pokedex> pokedexSerializarQ = new ArrayList<>();
        pokedexSerializarQ.add(p1Q);
        pokedexSerializarQ.add(p2Q);
        fileServices.serializarPokedex(pokedexSerializarQ, "pokedex");

        System.out.println("Exportando adestradores a XML:");
        List<Adestrador> adestradoresExportarQ = new ArrayList<>();
        adestradoresExportarQ.add(ashQ);
        adestradoresExportarQ.add(mistyQ);
        fileServices.exportarAdestradores(adestradoresExportarQ, "adestradores.xml");

        System.out.println("Actualizando entradas da pokedex con query:");
        p1Q.setNome("Bulbasaur Editado Query");
        p1Q.setPeso(new BigDecimal("7.5"));
        pokedexServices.actualizarPokemonQuery(p1Q);

        p2Q.setNome("Charmander Editado Query");
        p2Q.setPeso(new BigDecimal("9.2"));
        pokedexServices.actualizarPokemonQuery(p2Q);

        System.out.println("Actualizando adestradores con query:");
        ashQ.setNome("Ash Ketchum (Campión Query)");
        adestradorServices.actualizarAdestradorQuery(ashQ);

        mistyQ.setNome("Misty (Líder Query)");
        adestradorServices.actualizarAdestradorQuery(mistyQ);

        System.out.println("Actualizando pokemons con query:");
        List<Pokemon> listaPokemonsQ = pokemonServices.listarPokemon();
        Pokemon pk1Q = listaPokemonsQ.get(0);
        Pokemon pk2Q = listaPokemonsQ.get(1);
        Pokemon pk3Q = listaPokemonsQ.get(2);
        Pokemon pk4Q = listaPokemonsQ.get(3);

        pokemonServices.actualizarPokemonQuery(pk1Q.getId(), "Pikachu Alfa", pk1Q.getNacemento(), pk1Q.getPokedex(), pk1Q.getAdestrador());
        pokemonServices.actualizarPokemonQuery(pk2Q.getId(), "Charizard Peligroso", pk2Q.getNacemento(), pk2Q.getPokedex(), pk2Q.getAdestrador());
        pokemonServices.actualizarPokemonQuery(pk3Q.getId(), "Bulbasaur Mega", pk3Q.getNacemento(), pk3Q.getPokedex(), pk3Q.getAdestrador());
        pokemonServices.actualizarPokemonQuery(pk4Q.getId(), "Squirtle Alola", pk4Q.getNacemento(), pk4Q.getPokedex(), pk4Q.getAdestrador());

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
        }

        System.out.println("Listado Pokemons:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Deserializando e restaurando entradas da pokedex con query:");
        List<Pokedex> pokemonsDeserializadosQ = fileServices.deserializar("pokedex");
        System.out.println("Entradas deserializadas:");
        for (Pokedex pDeserializado : pokemonsDeserializadosQ) {
            Pokedex pEnBD = pokedexServices.lerPokemonPorIdQuery(pDeserializado.getId());
            if (pEnBD != null) {
                pEnBD.setNome(pDeserializado.getNome());
                pEnBD.setPeso(pDeserializado.getPeso());
                pEnBD.setMisc(pDeserializado.getMisc());
                pokedexServices.actualizarPokemonQuery(pEnBD);
                System.out.println(pEnBD.getId() + " - " + pEnBD.getNome());
            }
        }

        System.out.println("Importando e restaurando adestradores desde XML con query:");
        List<Adestrador> adestradoresImportadosQ = fileServices.importarAdestrador("adestradores.xml");
        for (Adestrador aImportado : adestradoresImportadosQ) {
            Adestrador aEnBD = adestradorServices.lerAdestradorPorIdQuery(aImportado.getId());
            if (aEnBD != null) {
                aEnBD.setNome(aImportado.getNome());
                aEnBD.setNacemento(aImportado.getNacemento());
                adestradorServices.actualizarAdestradorQuery(aEnBD);
                System.out.println(aEnBD.getId() + " - " + aEnBD.getNome());
            }
        }

        System.out.println("Listado pokedex:");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("Listado adestradores:");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
        }

        System.out.println("Listado Pokemons:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

        System.out.println("Eliminando todos os rexistros das táboas con query:");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            pokemonServices.eliminarPokemonQuery(pk.getId());
        }

        adestradorServices.eliminarTodosOsAdestradoresQuery();
        pokedexServices.eliminarTodosOsPokemonsQuery();

        System.out.println("Vaziáronse as 3 táboas.");
    }
}