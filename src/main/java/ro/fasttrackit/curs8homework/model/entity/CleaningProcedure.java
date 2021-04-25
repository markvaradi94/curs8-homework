package ro.fasttrackit.curs8homework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleaningProcedure {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Integer outcome;

    @ManyToOne
    private CleanupLog cleanupLog;

    public CleaningProcedure(String name, Integer outcome, CleanupLog cleanupLog) {
        this.name = name;
        this.outcome = outcome;
        this.cleanupLog = cleanupLog;
    }
}
