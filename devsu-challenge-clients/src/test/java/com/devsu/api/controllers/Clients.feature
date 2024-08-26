Feature: Get test for bankproducts by id
  Background:
    * url 'http://localhost:9090/api/v1'

  Scenario: Post new client
    Given path '/clientes'
    And request {"name": "John Doe", "gender": "Male", "age": 30, "personId": "123456789", "address": "123 Main St", "phone": "555-1234", "clientId": "C123", "password": "Password" }
    And header Content-Type = 'application/json'
    When method POST
    Then status 201

  Scenario: Update the created client
    Given path '/clientes/1'
    And request {"name": "New John Doe"}
    And header Content-Type = 'application/json'
    When method PUT
    Then status 200

  Scenario: Get the created and updated client
    Given path '/clientes/1'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    And match response == {"id":1,"name":"New John Doe","gender":"Male","age":30,"personId":"123456789","address":"123 Main St","phone":"555-1234","clientId":"C123","status":"ACTIVE"}

  Scenario: Get non-existent client
    Given path 'clientes/9999'
    And header Accept = 'application/json'
    When method GET
    Then status 404
    And match response.code == "CLIENT_NOT_FOUND"
    And match response.entityId == "9999"

  Scenario: Delete the created client
    Given path 'clientes/1'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204
