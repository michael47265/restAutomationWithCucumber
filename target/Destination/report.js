$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/rest.feature");
formatter.feature({
  "line": 1,
  "name": "Testing a REST API",
  "description": "Users should be able to submit GET and POST requests to a web service, \r\nrepresented by WireMock",
  "id": "testing-a-rest-api",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Data Upload to a web service",
  "description": "",
  "id": "testing-a-rest-api;data-upload-to-a-web-service",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "users upload data on a project",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "the server should handle it and return a success status",
  "keyword": "Then "
});
formatter.match({
  "location": "RestRunning2.users_upload_data_on_a_project()"
});
formatter.result({
  "duration": 5532959295,
  "status": "passed"
});
formatter.match({
  "location": "RestRunning2.the_server_should_handle_it_and_return_a_success_status()"
});
formatter.result({
  "duration": 479533935,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Data retrieval from a web service",
  "description": "",
  "id": "testing-a-rest-api;data-retrieval-from-a-web-service",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "users want to get information on the Cucumber project",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "the requested data is returned",
  "keyword": "Then "
});
formatter.match({
  "location": "RestRunning2.users_want_to_get_information_on_the_Cucumber_project()"
});
formatter.result({
  "duration": 302019580,
  "status": "passed"
});
formatter.match({
  "location": "RestRunning2.the_requested_data_is_returned()"
});
formatter.result({
  "duration": 160909552,
  "status": "passed"
});
});