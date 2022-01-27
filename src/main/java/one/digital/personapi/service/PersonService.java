package one.digital.personapi.service;

import one.digital.personapi.dto.MessageResponseDTO;
import one.digital.personapi.entity.Person;
import one.digital.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

        public MessageResponseDTO createPerson(Person person){
            Person savedPerson = personRepository.save(person);
            return MessageResponseDTO
                    .builder()
                    .message("Create person with ID " + savedPerson.getId())
                    .build();
        }
    }

