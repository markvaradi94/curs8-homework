package ro.fasttrackit.curs8homework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String message;
    private Integer stars;

    @OneToOne(cascade = PERSIST)
    private Tourist tourist;

    @ManyToOne
    private Room hotelRoom;
}
