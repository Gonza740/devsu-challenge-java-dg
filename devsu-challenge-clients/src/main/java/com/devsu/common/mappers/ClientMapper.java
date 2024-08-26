package com.devsu.common.mappers;

import com.devsu.api.requests.CreateClientRequest;
import com.devsu.api.requests.UpdateClientRequest;
import com.devsu.api.responses.GetClientResponse;
import com.devsu.persistence.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.apache.commons.codec.digest.DigestUtils;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    @Mapping(target = "updatedDate", expression = "java(new java.util.Date())")
    @Mapping(target = "userWhoUpdates", constant = "Handle user for authentication")
    Client toClient(CreateClientRequest request);

    GetClientResponse toClientDTO(Client client);

    @Mapping(target = "passwordHash", source = "client.passwordHash")
    @Mapping(target = "creationDate", source = "client.creationDate")
    @Mapping(target = "status", source = "client.status")
    @Mapping(target = "id", expression = "java(getNotNull(client.getId(), request.getId()))")
    @Mapping(target = "name", expression = "java(getNotNull(client.getName(), request.getName()))")
    @Mapping(target = "gender", expression = "java(getNotNull(client.getGender(), request.getGender()))")
    @Mapping(target = "age", expression = "java(getNotNull(client.getAge(), request.getAge()))")
    @Mapping(target = "personId", expression = "java(getNotNull(client.getPersonId(), request.getPersonId()))")
    @Mapping(target = "address", expression = "java(getNotNull(client.getAddress(), request.getAddress()))")
    @Mapping(target = "phone", expression = "java(getNotNull(client.getPhone(), request.getPhone()))")
    @Mapping(target = "clientId", expression = "java(getNotNull(client.getClientId(), request.getClientId()))")
    @Mapping(target = "updatedDate", expression = "java(new java.util.Date())")
    @Mapping(target = "userWhoUpdates", constant = "Handle user for authentication")
    Client toClient(Client client, UpdateClientRequest request);

    default String getNotNull(String actualValue, String newValue) {
        if (newValue == null) {
            return actualValue;
        }
        return newValue;
    }

    default Integer getNotNull(Integer actualValue, Integer newValue) {
        if (newValue == null) {
            return actualValue;
        }
        return newValue;
    }

    default Long getNotNull(Long actualValue, Long newValue) {
        if (newValue == null) {
            return actualValue;
        }
        return newValue;
    }
}
