package com.devsu.api.controllers;

import com.devsu.api.requests.CreateMovementRequest;
import com.devsu.api.requests.GetMovementRequest;
import com.devsu.api.responses.GetMovementResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${server.base-path-movements}")
public class MovementController extends BaseController {

    public MovementController(QueryGateway queryGateway, CommandGateway commandGateway, HttpServletRequest servletRequest) {
        super(queryGateway, commandGateway);
    }

    @PostMapping
    public ResponseEntity<Void> createMovement(@Valid @RequestBody CreateMovementRequest request) {
        Long newId = commandGateway.sendAndWait(request);
        return created(newId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovementResponse> getMovement(@PathVariable Long id) {
        GetMovementResponse movement = queryGateway.query(new GetMovementRequest(id), GetMovementResponse.class).join();
        return ok(movement);
    }
}
