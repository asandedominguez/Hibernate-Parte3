package config;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    static {
        try {
            standardServiceRegistry = new StandardServiceRegistryBuilder().configure("resources/properties.xml").build();
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            Metadata metadata = metadataSources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            if (standardServiceRegistry != null)
            {
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
        }

    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /*CREATE TABLE Coche (
    id BIGSERIAL PRIMARY KEY,
    Matricula VARCHAR(255) NOT NULL,
    Marca VARCHAR(255) NOT NULL
);

CREATE TABLE Multas (
    id BIGSERIAL PRIMARY KEY,
    IdCoche BIGINT NOT NULL,
    Importe DECIMAL(10,2) NOT NULL,
    PorcentaxeReduccion INT NOT NULL,
    CONSTRAINT fk_multa_coche FOREIGN KEY (IdCoche) REFERENCES Coche(id)
);
*/

}