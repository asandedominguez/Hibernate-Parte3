package model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table (name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    private Date nacemento;

    @ManyToOne
    @JoinColumn(name = "PokedexEntry", nullable = false)
    private Pokedex pokedex;

    @ManyToOne
    @JoinColumn(name = "Adestrador", nullable = false)
    private Adestrador adestrador;

    public Pokemon() {
    }

    public Pokemon(String nome, Date nacemento, Pokedex pokedex, Adestrador adestrador) {
        this.nome = nome;
        this.nacemento = nacemento;
        this.pokedex = pokedex;
        this.adestrador = adestrador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNacemento() {
        return nacemento;
    }

    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public Adestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Adestrador adestrador) {
        this.adestrador = adestrador;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento=" + nacemento +
                ", pokedex=" + (pokedex != null ? pokedex.getId() : "null") +
                ", adestrador=" + (adestrador != null ? adestrador.getId() : "null") +
                '}';
    }
}
