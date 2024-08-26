package com.devsu.common.mappers;

import com.devsu.api.requests.CreateAccountRequest;
import com.devsu.api.responses.GetAccountResponse;
import com.devsu.persistence.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "currentBalance", source = "request.initialBalance")
    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    @Mapping(target = "updatedDate", expression = "java(new java.util.Date())")
    @Mapping(target = "userWhoUpdates", constant = "Handle user for authentication")
    Account toAccount(CreateAccountRequest request);

    GetAccountResponse toAccountDTO(Account account);
}
