package com.disney.disneyPersonajes.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pelicula")
@Getter
@Setter
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private int calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;

   @ManyToMany(
           cascade = {
                   CascadeType.PERSIST,
                   CascadeType.MERGE
           })
   @JoinTable(
          name = "personaje_pelicula",
          joinColumns = @JoinColumn(name ="pelicula_id"),
          inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();

    @Override
    public boolean equals(Object obj){
        if(obj ==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        final PeliculaEntity other = (PeliculaEntity) obj;
        return other.id==this.id;
    }

}
