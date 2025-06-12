@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", allocationSize = 1)
    @Column(name = "personID")
    private int personID;

    @Column(name = "personLastName", nullable = false)
    private String personLastName;

    @Column(name = "personName", nullable = false)
    private String personName;

    @Column(name = "personSDT", nullable = false)
    private String personSDT;

    @Column(name = "personEmail", nullable = false, unique = true)
    private String personEmail;

    @Column(name = "personPassword", nullable = false)
    private String personPassword;

    public Person() {}

    public Person(String personLastName, String personName, String personSDT, String personEmail, String personPassword) {
        this.personLastName = personLastName;
        this.personName = personName;
        this.personSDT = personSDT;
        this.personEmail = personEmail;
        this.personPassword = personPassword;
    }

    public int getPersonID() {
        return personID;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSDT() {
        return personSDT;
    }

    public void setPersonSDT(String personSDT) {
        this.personSDT = personSDT;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }
}
