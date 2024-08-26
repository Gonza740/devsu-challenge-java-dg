package com.devsu.api.controllers;

import com.devsu.api.requests.CreateClientRequest;
import com.devsu.api.requests.DeleteClientRequest;
import com.devsu.api.requests.GetClientRequest;
import com.devsu.api.requests.UpdateClientRequest;
import com.devsu.api.responses.GetClientResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${server.base-path}")
public class ClientController extends BaseController {

    public ClientController(QueryGateway queryGateway, CommandGateway commandGateway, HttpServletRequest servletRequest) {
        super(queryGateway, commandGateway);
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody CreateClientRequest request) {
        Long newId = commandGateway.sendAndWait(request);
        return created(newId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetClientResponse> getClient(@PathVariable Long id) {
        GetClientResponse client = queryGateway.query(new GetClientRequest(id), GetClientResponse.class).join();
        return ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        commandGateway.sendAndWait(new DeleteClientRequest(id));
        return noContent();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientRequest request) {
        request.setId(id);
        commandGateway.sendAndWait(request);
        return ResponseEntity.ok().build();
    }
}
