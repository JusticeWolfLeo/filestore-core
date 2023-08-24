package maxima.ru.filemanager.model;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Counteragent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nickname;
    private String tel;
    private String email;
    private String password;
    private String repeatPass;
    private String typeCounteragent;
}
