## Endpoints

* Run application

```bash
$ mvn spring-boot:run
```

**PATCH**: /ads/calculateScore

curl --request PATCH 'http://localhost:8080/ads/calculateScore'

**GET**: /ads/public

curl --request GET 'http://localhost:8080/ads/public'

**GET** /ads/quality

curl --request GET 'http://localhost:8080/ads/quality'

## Points of interest

* I decided to take an approach based on Hexagonal Architecture. Following the dependency rule, I stablish three layers (infrastructure, application, domain) which are maven modules to be able to manage the dependency relationship.
* I included unit tests, acceptance tests (using stubs to avoid including Mockito in order to simulate implementation details) and e2e tests with the initial data from the InMemoryPersistence class.
* I have tried to follow Open-Closed principle in **ScoreHandler** implementation.

## Improvements

* Scoring system could change based on new business rules. To be able to change the points allocated to the scoring process, a property resource could be used, which allows working with values from properties files through the @Value annotation of Spring Framework.

 

