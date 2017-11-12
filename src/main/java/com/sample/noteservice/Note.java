package noteservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    Note() { // jpa only
    }

    public Note(String body) {
        this.body = body;
    }

    public String body;

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
