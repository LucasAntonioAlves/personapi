package one.digital.personapi.service;

import lombok.AllArgsConstructor;
import one.digital.personapi.dto.mapper.*;
import one.digital.personapi.dto.request.PersonDTO;
import one.digital.personapi.dto.response.MessageResponseDTO;
import one.digital.personapi.entity.Person;
import one.digital.personapi.exception.PersonNotFoundException;
import one.digital.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper;


    public MessageResponseDTO create(@RequestBody PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse("Person Successfully created with ID ", savedPerson.getId());

    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }


    public List<PersonDTO> litAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }


    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepository.deleteById(id);
    }


    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRepository.save(personToUpdate);

        return createMessageResponse("Update Person with ID ", updatePerson.getId());
    }


    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder().message(message + id).build();
    }
}

