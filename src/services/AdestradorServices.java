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

    public void crearAdestradorQuery(String nome, Date nacemento) {
        String sql = "INSERT INTO adestrador (nome, nacemento) VALUES (:nome, :nacemento)";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter("nome", nome)
                    .setParameter("nacemento", nacemento)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Adestrador lerAdestradorPorIdQuery(Long id) {
        String hql = "FROM Adestrador WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(hql, Adestrador.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarAdestradorQuery(Adestrador adestrador) {
        String hql = "UPDATE Adestrador SET nome = :nome, nacemento = :nacemento WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql)
                    .setParameter("nome", adestrador.getNome())
                    .setParameter("nacemento", adestrador.getNacemento())
                    .setParameter("id", adestrador.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarAdestradorQuery(Adestrador adestrador) {
        String hql = "DELETE FROM Adestrador WHERE id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql)
                    .setParameter("id", adestrador.getId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}