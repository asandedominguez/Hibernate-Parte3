import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import services.AdestradorServices;
import services.FileServices;
import services.PokedexServices;
import services.PokemonServices;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) {
        PokedexServices pokedexServices = new PokedexServices();
        AdestradorServices adestradorServices = new AdestradorServices();
        PokemonServices pokemonServices = new PokemonServices();

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

        adestradorServices.crearAdestrador("Ash Ketchum", new Date());
        adestradorServices.crearAdestrador("Misty", new Date());

        Adestrador ash = adestradorServices.lerAdestradorPorNome("Ash Ketchum");
        Adestrador misty = adestradorServices.lerAdestradorPorNome("Misty");

        Pokedex p1 = pokedexServices.lerPokemonPorId(1L);
        Pokedex p2 = pokedexServices.lerPokemonPorId(2L);
        Pokedex p3 = pokedexServices.lerPokemonPorId(3L);
        Pokedex p4 = pokedexServices.lerPokemonPorId(4L);
        Pokedex p5 = pokedexServices.lerPokemonPorId(5L);
        Pokedex p6 = pokedexServices.lerPokemonPorId(6L);
        Pokedex p7 = pokedexServices.lerPokemonPorId(7L);
        Pokedex p8 = pokedexServices.lerPokemonPorId(8L);
        Pokedex p9 = pokedexServices.lerPokemonPorId(9L);
        Pokedex p10 = pokedexServices.lerPokemonPorId(10L);

        Date dataNacemento = new Date();

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

        System.out.println("Listado pokedex");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println();

        System.out.println("Listado adestradores");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println(a.toString());
        }

        System.out.println("Listado Pokemon");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() + " Nacemento: " + pk.getNacemento() +  " Tipo: " + pk.getPokedex().getNome() +  " Adestrador: " + pk.getAdestrador().getNome());
        }

        List<Pokedex> Pokemons = new ArrayList<>();
        Pokemons.add(p1);
        Pokemons.add(p2);

        FileServices fileServices = new FileServices();
        fileServices.serializarPokedex(Pokemons, "pokedex");

        List<Adestrador> adestradores = new ArrayList<>();
        adestradores.add(ash);
        adestradores.add(misty);

        fileServices.exportarAdestradores(adestradores, "adestradores.xml");

        p1.setNome("Bulbasaur Editado");
        p1.setPeso(new BigDecimal("7.5"));
        pokedexServices.actualizarPokemon(p1);

        p2.setNome("Charmander Editado");
        p2.setPeso(new BigDecimal("9.2"));
        pokedexServices.actualizarPokemon(p2);

        ash.setNome("Ash Ketchum (Campión)");
        adestradorServices.actualizarAdestrador(ash);

        misty.setNome("Misty (Líder de Ximnasio)");
        adestradorServices.actualizarAdestrador(misty);

        List<Pokemon> listaPokemons = pokemonServices.listarPokemon();

        Pokemon pk1 = listaPokemons.get(0);
        Pokemon pk2 = listaPokemons.get(1);
        Pokemon pk3 = listaPokemons.get(2);
        Pokemon pk4 = listaPokemons.get(3);

        pokemonServices.actualizarPokemon(pk1.getId(), "Pikachu Alfa", pk1.getNacemento(), pk1.getPokedex(), pk1.getAdestrador());
        pokemonServices.actualizarPokemon(pk2.getId(), "Charizard Peligroso", pk2.getNacemento(), pk2.getPokedex(), pk2.getAdestrador());
        pokemonServices.actualizarPokemon(pk3.getId(), "Bulbasaur Mega", pk3.getNacemento(), pk3.getPokedex(), pk3.getAdestrador());
        pokemonServices.actualizarPokemon(pk4.getId(), "Squirtle Alola", pk4.getNacemento(), pk4.getPokedex(), pk4.getAdestrador());

        List<Pokedex> pokemonsDeserializados = fileServices.deserializar("pokedex");
        System.out.println("Entradas deserializadas");

        for (Pokedex pDeserializado : pokemonsDeserializados) {
            Pokedex pEnBD = pokedexServices.lerPokemonPorId(pDeserializado.getId());
            pEnBD.setNome(pDeserializado.getNome());
            pEnBD.setPeso(pDeserializado.getPeso());
            pEnBD.setMisc(pDeserializado.getMisc());
            pokedexServices.actualizarPokemon(pEnBD);
            System.out.println(pEnBD.getId() + " - " + pEnBD.getNome());
        }

        System.out.println("listado pokedex");
        for (Pokedex p : pokedexServices.listarPokemons()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Peso: " + p.getPeso() + "kg | Misc: " + p.getMisc());
        }

        System.out.println("listado adestradores");
        for (Adestrador a : adestradorServices.listarAdestradores()) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
        }

        System.out.println("listado Pokemons");
        for (Pokemon pk : pokemonServices.listarPokemon()) {
            System.out.println("id: " + pk.getId() + " Nome: " + pk.getNome() +  " Tipo: " + pk.getPokedex().getNome() + " Adestrador: " + pk.getAdestrador().getNome());
        }

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
    }
}
