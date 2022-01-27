package one.digital.personapi.dto.mapper;

import one.digital.personapi.dto.request.PersonDTO;
import one.digital.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    default Person toModel(PersonDTO dto) {
        return null;
    }

    PersonDTO toDTO(Person dto);
}