package services;

import config.HibernateConfig;
import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class PokemonServices {
    public void InsertarPokemon(String nome, Date nacemento, Pokedex pokedex, Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon novoPokemon = new Pokemon();
            novoPokemon.setNome(nome);
            novoPokemon.setNacemento(nacemento);
            novoPokemon.setPokedex(pokedex);
            novoPokemon.setAdestrador(adestrador);
            session.save(novoPokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pokemon lerPokemon(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarPokemon(Long id, String nome, Date nacemento, Pokedex pokedex, Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                pokemon.setNome(nome);
                pokemon.setNacemento(nacemento);
                pokemon.setPokedex(pokedex);
                pokemon.setAdestrador(adestrador);
                session.update(pokemon);
                transaction.commit();
            } else {
                System.out.println("No se encontró el pokemon con el id " + id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPokemon(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                session.delete(pokemon);
                transaction.commit();
            } else {
                System.out.println("No se encontró el pokemon con el id " + id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pokemon> listarPokemon() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokemon").list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
