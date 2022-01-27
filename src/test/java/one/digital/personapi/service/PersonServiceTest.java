package one.digital.personapi.service;

import one.digital.personapi.dto.request.PersonDTO;
import one.digital.personapi.dto.response.MessageResponseDTO;
import one.digital.personapi.entity.Person;
import one.digital.personapi.dto.mapper.PersonMapper;
import one.digital.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digital.personapi.utils.PersonUtils.createFakeDTO;
import static one.digital.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO sucessMessage = personService.create(personDTO);

        assertEquals(expectedSuccessMessage, sucessMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Create person with ID " + id)
                .build();
    }
}
