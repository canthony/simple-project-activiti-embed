# Simple Project

This project is to provide a starting point to integrate Activiti into some existing code. We intend to show how we can
embed Activiti into an existing Spring Boot application, and demonstrate that Activiti can invoke logic on Spring Beans
and vice versa (i.e. Activiti -> Custom Logic and Custom Logic -> Activiti)

> Realistically, in a production system,  we probably wouldn't include everything here in a single webapp - 
> I imagine that any UI outside of Activiti, and our own REST Api might be hosted elsewhere. 
> Still, I want to know how to control it all in one place - so that we have that option open to us.

## Building and running

We have used gradle + Spring Boot to allow us to create a simple single war deployment. This git repo contains all that
you need to build and run the application - all the necessary dependencies, including gradle itself, will be downloaded 
as necessary. All that is needed is java 7 (or later) jdk, and access to the internet to download the dependencies.
(If internet access is behind a proxy, and it doesn't work out of the box, see the [Gradle Docs](https://docs.gradle.org/current/userguide/build_environment.html#sec:accessing_the_web_via_a_proxy)
for more details.

To run the application
 ```
 ./gradlew bootRun
 ```
 
To build an executable WAR:
```
./gradlew bootRePackage
```

The resulting war (build/libs/simple-project.war) can either be deployed into a Web Application Server (e.g. Tomcat) or
executed directly 
```
java -jar build/libs/simple-project.war
```


## Entry Point

com.example.embedsample.Application is the main Spring Boot entry point. It registers two separate Spring Dispatcher servlets,
one at /main-ui (the user interface) and one at /special-api (a REST Api)

There is a simple front page at the root of the application, providing acesss

## Business Logic 

com.example.embedsample.CompanyService provides access to the domain class. It represents business logic that may be called 
either from the REST Api or an Activiti process. The bean name is **companyService** 
 
com.example.embedsample.AccountsUploadService will deal with the business logic of a file upload. It is intended that it will
notify an Activity process instance of the presence of a file, via a message. Hopefuly, we will also be able to add the file
to the process instance too. The bean name is **accountsUploadService **

## Web Controllers

There are two separate dispatcher servlets for two separate Web Contexts within the application.
com.example.embedsample.web.rest contains the configuration and controller for a simple REST Api to access the business logic 
provided by the CompanyService

### REST Api
The com.example.embedsample.web.rest context is mapped to /special-api 

`/special-api/companies` provides access to a list of companies 
`/special-api/companies/xxxx` provides data for a specic company, where xxx is the company registration number  

### Main UI
The com.example.embedsample.web.mainui context is mapped to /main-ui. It uses the REST Api to provide it's data and
access to the business logic.
The front end is built from HTML + Bootstrap + JQuery; by using Thymeleaf templates, we've made it a little easier to
prototype whilst developing, and also access embedded resources using webjars  
 
`/main-ui` is a simple table of companies. By selecting a company, further information is displayed.
`/main-ui/upload` presents a form with an execution ID and a file-selector. When the form is posted, the CompaniesAPIController 
will pass the information on to the AccountsUploadService
