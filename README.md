## Recruiting Service REST API 

Demonstrates a simple RESTful web service using Spring Boot. This web service provides an in-memory offer and application management service, with the capability to get a single offer, get all offers, create an offer, create an application, get an application, progress the status of an application and notify when there is a status change.


### Starting the Application
To start this web service, install [Maven](https://maven.apache.org/install.html) and execute the following command. Need Java 8 or above

    mvn spring-boot:run
    
Once the web service is started, it can be reached at

    http://localhost:8080/offer

### REST Endpoints
The following REST endpoints are available upon deployment of the application:

1. list all offers.
	```bash
	curl --request GET \
	--header 'content-type: application/json' \
	--url http://localhost:8080/offer
	```

2. read a single offer
	```bash
	curl --request GET \
	--header 'content-type: application/json' \
	--url http://localhost:8080/offer/{offerId}
	```

	* `200 OK` if offer exists
	* `404 Not Found` if offer does not exist
 

3. create a job offer
	```bash
	curl --request POST \
	--url http://localhost:8080/offer \
	--header 'content-type: application/json' \
	--data '{ "jobTitle":"Python Developer", "startDate":"30/12/2017" }'
	```
	* `201 Created` if offer successfully created


4. get all applications per offer
	```bash
	curl --request GET \
	--header 'content-type: application/json' \
	--url http://localhost:8080/offer/{offerId}/application
	```

5. read one application per offer
	```bash
	curl --request GET \
	--header 'content-type: application/json' \
	--url http://localhost:8080/offer/{offerId}/application/{applicationId}
	```

6. apply for an offer
	```bash
	curl --request POST \
	--url http://localhost:8080/application \
	--header 'content-type: application/json' \
	--data '{
		"emailId": "vinaya.nayak88@gmail.com",
		"resumeText": "Python Opening",
		"offerId": "1"
	}'
	```
	* `201 Created` if application successfully created with application id


7. progress the status of an application
	```bash
	curl --request PATCH \
	--url http://localhost:8080/application/3 \
	--header 'content-type: application/json' \
	--data '{
		
		"applcationStatus": "INVITED"

	}'
	```
	HTTP PATCH is used here in-order to update the single attribute of the Object
	This status change triggers the notification and the notification message is logged in the command line.

postman collection link - https://www.getpostman.com/collections/e031d5586b779dab0f59

### Test cases

	mvn test


### Design Decisions and assumptions made
+ Used in-memory storage for storing the offers and application objects
- Used Spring AOP(Aspect) for Logging the notification. `LoggingAspect.logAfterStatusUpdate` method will be triggered automatically whenever there is a change in the application status.
- Integration/acceptance test cases are demonstrated for GET offer

### Improvements 
- test coverage
+ exception handling

