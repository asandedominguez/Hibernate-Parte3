package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class AdestradorServices {

    public void crearAdestrador(String nome, Date nacemento) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = new Adestrador(nome, nacemento);
            session.save(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Adestrador lerAdestradorPorId(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Adestrador.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Adestrador lerAdestradorPorNome(String nome) {
        Adestrador adestrador = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Adestrador> lista = session.createQuery("FROM Adestrador WHERE nome = :nome", Adestrador.class)
                    .setParameter("nome", nome)
                    .getResultList();

            if (!lista.isEmpty()) {
                adestrador = lista.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return adestrador;
    }

    public List<Adestrador> listarAdestradores() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Adestrador", Adestrador.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void eliminarTodosOsAdestradoresQuery() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int count = session.createQuery("DELETE FROM Adestrador").executeUpdate();
            transaction.commit();
            System.out.println("Se eliminaron:  " + count + " adestradores");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}