# Orders exercise

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