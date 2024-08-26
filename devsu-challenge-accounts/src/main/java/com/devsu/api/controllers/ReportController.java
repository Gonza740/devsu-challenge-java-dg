package com.devsu.api.controllers;

import com.devsu.api.requests.GetReportRequest;
import com.devsu.api.responses.GetReportResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${server.base-path-reports}")
public class ReportController extends BaseController {

    public ReportController(QueryGateway queryGateway, CommandGateway commandGateway, HttpServletRequest servletRequest) {
        super(queryGateway, commandGateway);
    }

    @GetMapping
    public ResponseEntity<GetReportResponse> getReport(@Valid GetReportRequest request) {
        GetReportResponse report = queryGateway.query(request, GetReportResponse.class).join();
        return ok(report);
    }
}
