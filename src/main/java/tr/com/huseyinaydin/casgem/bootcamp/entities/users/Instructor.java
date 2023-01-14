package tr.com.huseyinaydin.casgem.bootcamp.entities.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.entities.bootcamps.Bootcamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructors")
public class Instructor extends User {
    @Column(name = "company_name")
    private String companyName;
    @OneToMany(mappedBy = "instructor")
    private List<Bootcamp> bootcamps;
}
