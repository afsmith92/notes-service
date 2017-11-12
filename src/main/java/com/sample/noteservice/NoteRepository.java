package noteservice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAll();

    void deleteById(Long id);

    List<Note> findByBodyIgnoreCaseContaining(String query);
}
