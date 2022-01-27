package one.digital.personapi.service;

import one.digital.personapi.dto.request.PersonDTO;
import one.digital.personapi.dto.response.MessageResponseDTO;
import one.digital.personapi.entity.Person;
import one.digital.personapi.excpetion.PersonNotFoundExcpetion;
import one.digital.personapi.mapper.PersonMapper;
import one.digital.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTACE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

        public MessageResponseDTO createPerson(PersonDTO personDTO){
            Person personToSave = personMapper.toModel(personDTO);

            Person savedPerson = personRepository.save(personToSave);
            return MessageResponseDTO.builder().message("Created Person with ID " + savedPerson.getId()).build();

        }

    public List<PersonDTO> litAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExcpetion {
       Person person = personRepository.findById(id)
               .orElseThrow(() -> new PersonNotFoundExcpetion(id));
       return personMapper.toDTO(person);
    }
}

