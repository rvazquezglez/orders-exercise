## Initializing

The first thing I did was going to [Spring Initializr](https://start.spring.io/) to bootstrap the project.

Then I created the entities for the model in `com.orders.models` and setup a `CrudRepository` with `RepositoryRestResource` annotation in `com.orders.repository`.

I had some issues with the naming because `order` is a reserved word in sql, so I renamed the tables.

I needed to configure `cascade all` in order to save some test data from `OrdersApplication`.

At this point the application works with default REST endpoints from `RepositoryRestResource`:

If I send a GET request with curl I get this:

```
$ curl -X GET "http://localhost:8080/orders"
{
 "_embedded" : {
   "orders" : [ {
     "customer" : {
       "name" : null,
       "lastName" : null
     },
     "totalAmount" : 100.0,
     "date" : null,
     "itemsPurchased" : [ {
       "name" : "book",
       "amount" : 40.0
     }, {
       "name" : "rechargeable batteries",
       "amount" : 60.0
     } ],
     "paymentsReceived" : [ ],
     "_links" : {
       "self" : {
         "href" : "http://localhost:8080/orders/1"
       },
       "order" : {
         "href" : "http://localhost:8080/orders/1"
       }
     }
   } ]
 },
 "_links" : {
   "self" : {
     "href" : "http://localhost:8080/orders"
   },
   "profile" : {
     "href" : "http://localhost:8080/profile/orders"
   }
 }
}%


$ curl -X GET "http://localhost:8080/orders/1"
{
 "customer" : {
   "name" : null,
   "lastName" : null
 },
 "totalAmount" : 100.0,
 "date" : null,
 "itemsPurchased" : [ {
   "name" : "book",
   "amount" : 40.0
 }, {
   "name" : "rechargeable batteries",
   "amount" : 60.0
 } ],
 "paymentsReceived" : [ ],
 "_links" : {
   "self" : {
     "href" : "http://localhost:8080/orders/1"
   },
   "order" : {
     "href" : "http://localhost:8080/orders/1"
   }
 }
}%
```

This works fine for a prototype but is not enough for a production application.

## DB schema and DB migrations

I generated the schema adding these properties to application.yml, I used to do this with hibernate tools but learned and liked more how spring boot allows you to do it.

```
spring.jpa.properties.javax.persistence.schema-generation:
  create-source: metadata
  scripts:
    action: create
    create-target: src/main/resources/db/migration/V1__init.sql
```

I noticed that it generated a join table that I didn't want, that was because I didn't specified a join column, so I added that and generated the schema again.

I wanted to keep track of the db changes with a migration tool, so I added the flyway dependency and configured it. Haven't used flyway before, I learned how it manages the versions naming and separates test scripts from production scripts.

I added some unit test for flyway.

## Using controller and repository for a more grained control

I got rid of `CrudRepository` and `RepositoryRestResource`, declared `OrderController` as a `RestController`, and `OrderRepository` as a `JpaRepository` and declared the two endpoints in the controller.

## Validation

I created `OrderValidator` to validate the orders in the controller and added `handleInvalidOrder` as a `ExceptionHandler` method in the controller.

## Caches
And last I enabled caches to avoid hitting the database on each call to get all orders.