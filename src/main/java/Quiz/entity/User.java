package Quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "countryapp", name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private int points;

    @Column
    private int numberOfGames;
    @Column
    double correctAnswersPercent;
    public User() {
        points = 0;
        numberOfGames = 0;
    }
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public double getCorrectAnswersPercent() {
        return correctAnswersPercent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
