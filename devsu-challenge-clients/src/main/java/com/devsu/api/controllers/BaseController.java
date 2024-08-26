package com.devsu.api.controllers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public abstract class BaseController {

    protected final QueryGateway queryGateway;
    protected final CommandGateway commandGateway;

    /**
     * OK 200 Response Entity
     * Get Or Update and the response must contain Data
     * @param body Response to be sent to the client
     * @return ResponseEntity with the body and the 200 status code
     * @param <T> Type of the response
     */
    protected  <T> ResponseEntity<T> ok(T body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * CREATED 201 Response Entity
     * When a resource got created successfully
     * @param resourceId Id of the resource created
     * @return ResponseEntity with the 201 status code
     */
    protected ResponseEntity<Void> created(long resourceId){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * NO CONTENT 204 Response Entity
     * When a resource got deleted or updated successfully
     * @return ResponseEntity with the 204 status code
     */
    protected ResponseEntity<Void> noContent(){
        return ResponseEntity.noContent().build();
    }

    /**
     * NOT FOUND 404 Response Entity
     * When a resource marked for delete or update does not exist
     * @return ResponseEntity with the 404 status code
     */
    protected ResponseEntity<Void> notFound(){
        return ResponseEntity.notFound().build();
    }
}
