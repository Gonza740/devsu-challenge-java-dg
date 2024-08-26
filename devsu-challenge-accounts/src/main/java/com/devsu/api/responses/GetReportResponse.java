package com.devsu.api.responses;


import com.devsu.api.responses.models.AccountReportDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetReportResponse {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String personId;
    private String address;
    private String phone;
    private String clientId;
    private String status;
    private List<AccountReportDTO> accounts;
}
