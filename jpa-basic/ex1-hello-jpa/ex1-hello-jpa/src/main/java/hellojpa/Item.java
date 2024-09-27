package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
