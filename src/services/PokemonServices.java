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

    public void InsertarPokemonQuery(String nome, Date nacemento, Pokedex pokedex, Adestrador adestrador) {
        String sql = "INSERT INTO Pokemon (nome, nacemento, PokedexEntry, Adestrador) VALUES (:nome, :nacemento, :pokedex, :adestrador)";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter("nome", nome)
                    .setParameter("nacemento", nacemento)
                    .setParameter("pokedex", pokedex.getId())
                    .setParameter("adestrador", adestrador.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pokemon lerPokemonQuery(Long id) {
        String hql = "FROM Pokemon WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(hql, Pokemon.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarPokemonQuery(Long id, String nome, Date nacemento, Pokedex pokedex, Adestrador adestrador) {
        String hql = "UPDATE Pokemon SET nome = :nome, nacemento = :nacemento, pokedex = :pokedex, adestrador = :adestrador WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql)
                    .setParameter("nome", nome)
                    .setParameter("nacemento", nacemento)
                    .setParameter("pokedex", pokedex)
                    .setParameter("adestrador", adestrador)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPokemonQuery(Long id) {
        String hql = "DELETE FROM Pokemon WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
