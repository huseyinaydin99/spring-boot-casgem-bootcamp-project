package tr.com.huseyinaydin.casgem.bootcamp.entities.applications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.entities.bootcamps.Bootcamp;
import tr.com.huseyinaydin.casgem.bootcamp.entities.users.Applicant;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    @ManyToOne()
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;
    @ManyToOne()
    @JoinColumn(name = "state_id")
    private State state;
}
