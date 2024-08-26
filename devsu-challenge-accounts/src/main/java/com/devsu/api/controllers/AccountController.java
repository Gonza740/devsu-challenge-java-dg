package com.devsu.api.controllers;

import com.devsu.api.requests.CreateAccountRequest;
import com.devsu.api.requests.DeleteAccountRequest;
import com.devsu.api.requests.GetAccountRequest;
import com.devsu.api.responses.GetAccountResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${server.base-path-accounts}")
public class AccountController extends BaseController {

    public AccountController(QueryGateway queryGateway, CommandGateway commandGateway, HttpServletRequest servletRequest) {
        super(queryGateway, commandGateway);
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Long newId = commandGateway.sendAndWait(request);
        return created(newId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable Long id) {
        GetAccountResponse account = queryGateway.query(new GetAccountRequest(id), GetAccountResponse.class).join();
        return ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        commandGateway.sendAndWait(new DeleteAccountRequest(id));
        return noContent();
    }
}
