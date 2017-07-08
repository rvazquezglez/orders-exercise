# Orders exercise

You can start the application running the spring boot app from gradle wrapper:
```
$./gradlew bootRun
```

To run the tests just do:
```
$./gradlew test
```

You can save a new entity sending a POST request like this:
```
$ curl -H "Content-Type: application/json" -d '{"totalAmount":167.0,"itemsPurchased":[{"name":"The hitchhiker guide to the galaxy","amount":167.0}]}' http://localhost:8080/order/
{"id":3,"customer":null,"totalAmount":167.0,"date":null,"itemsPurchased":[{"id":2,"name":"The hitchhiker guide to the galaxy","amount":167.0}],"paymentsReceived":null}%
```

If you want to list all the orders you can do this:
```
$ curl -H "Content-Type: application/json" http://localhost:8080/orders/
[{"id":1,"customer":{"id":1,"name":"John","lastName":"Lennon"},"totalAmount":100.0,"date":{"year":2017,"month":"JUNE","era":"CE","dayOfYear":157,"dayOfWeek":"TUESDAY","leapYear":false,"dayOfMonth":6,"monthValue":6,"chronology":{"id":"ISO","calendarType":"iso8601"}},"itemsPurchased":[{"id":1,"name":"book","amount":100.0}],"paymentsReceived":[{"id":1,"date":{"year":2017,"month":"JUNE","era":"CE","dayOfYear":157,"dayOfWeek":"TUESDAY","leapYear":false,"dayOfMonth":6,"monthValue":6,"chronology":{"id":"ISO","calendarType":"iso8601"}},"amount":100.0}]},{"id":3,"customer":null,"totalAmount":167.0,"date":null,"itemsPurchased":[{"id":2,"name":"The hitchhiker guide to the galaxy","amount":167.0}],"paymentsReceived":[]}]%
```

You can check the changes evolution looking at git history or if you want more detail check it [here](CHANGE_LOG.md)
