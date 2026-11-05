package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class PokedexServices {

    public void crearPokemon(String nome, BigDecimal peso, String misc) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokedex pokemon = new Pokedex(nome, peso, misc);
            session.save(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pokedex lerPokemonPorId(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokedex.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarPokemon(Pokedex pokemon) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPokemon(Pokedex pokemon) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pokedex lerPokemonPorNome(String nome) {
        Pokedex pokemon = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Pokedex> lista = session.createQuery("FROM Pokedex WHERE nome = :nome", Pokedex.class)
                    .setParameter("nome", nome)
                    .getResultList();

            if (!lista.isEmpty()) {
                pokemon = lista.get(0);
            } else {
                System.out.println(nome);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pokemon;
    }

    public List<Pokedex> listarPokemons() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokedex", Pokedex.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void eliminarTodosOsPokemonsQuery() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int deletedCount = session.createQuery("DELETE FROM Pokedex").executeUpdate();
            transaction.commit();
            System.out.println("Se eliminaron " + deletedCount + " Pokemons");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}