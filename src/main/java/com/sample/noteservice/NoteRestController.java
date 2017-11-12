package noteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
class NoteRestController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity readNote(@PathVariable Long id) {
        Note note = noteRepository.findOne(id);

        if (note == null) {
            return new ResponseEntity("No note found for id " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(note, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Note> readNotes(@RequestParam(value = "query", required = false) String query) {
        return query == null || query.isEmpty() ?
            noteRepository.findAll()
            : noteRepository.findByBodyIgnoreCaseContaining(query);
    }

    @RequestMapping(method = RequestMethod.POST)
    Note add(@RequestBody Note input) {
        return noteRepository.save(new Note(input.body));
    }

	@PutMapping("/{id}")
    ResponseEntity updateNote(@PathVariable Long id, @RequestBody Note input) {
        Note note = noteRepository.findOne(id);

        if (null == note) {
            return new ResponseEntity("No note found for id " + id, HttpStatus.NOT_FOUND);
        }

        note.setBody(input.body);
		noteRepository.save(note);

		return new ResponseEntity(note, HttpStatus.OK);
	}


    @DeleteMapping("/{id}")
	ResponseEntity deleteNote(@PathVariable Long id) {
        Note note = noteRepository.findOne(id);

        if (note == null) {
			return new ResponseEntity("No note found for id " + id, HttpStatus.NOT_FOUND);
		}

        noteRepository.delete(note);

		return new ResponseEntity(id, HttpStatus.OK);

	}
}
