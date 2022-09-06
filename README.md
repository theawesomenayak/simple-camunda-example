# Simple Camunda Example

This is an evolving project that uses Camunda (BPMN Engine) and Spring Boot to showcase 
a simple workflow automation project with multiple use cases.

## Resources
1. Camunda - https://camunda.com/
2. Camunda Modeler - https://camunda.com/download/modeler/
3. Spring Boot - https://spring.io/projects/spring-boot
4. Quick Start Tutorial - https://docs.camunda.org/get-started/quick-start

## Usage
### Docker
1. Run `docker-compose up -d` to start the camunda instance.
2. Confirm that camunda has started by going to http://localhost:8080/camunda-welcome/index.html

OR

### Spring Boot
1. Run `CamundaService` application in the `camunda-service` module.
2. Confirm that camunda has started by going to http://localhost:8080/

### Payment Retrieval Flow
#### Manual
1. Open the sample BPMN file (resources/templates/payment.bpmn) and sample DMN file (resources
/templates/approve-payment.dmn) in camunda modeler and deploy them to camunda.
2. Go to *Tasklist* (http://localhost:8080/camunda/app/tasklist) and log in with the credentials “demo / demo”
3. Click on the *Start Process* button to start a process instance. 
4. Select *Payment Retrieval* from the list. 
5. Add the following variables for the process instance using the generic form.
    1. amount
    2. item
    3. receiver
    4. message
6. Click *Start*
7. If the amount is less than 1000, a task will be created for user approval in *Tasklist*.
8. If the amount is more than or equal to 1000, the workflow will complete successfully.

#### Programmatic
1. Adjust the values in `PaymentRetrieval`
2. Run `Application` as SpringBoot application.