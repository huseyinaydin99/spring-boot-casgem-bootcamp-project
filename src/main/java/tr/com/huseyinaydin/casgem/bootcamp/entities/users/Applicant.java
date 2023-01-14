package tr.com.huseyinaydin.casgem.bootcamp.entities.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.entities.applications.Application;
import tr.com.huseyinaydin.casgem.bootcamp.entities.blacklists.Blacklist;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicants")
public class Applicant extends User {
    @Column(name = "about")
    private String about;
    @OneToMany(mappedBy = "applicant")
    private List<Application> applications;
    @OneToOne(mappedBy = "applicant")
    private Blacklist blacklist;
}
