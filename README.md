# SINT_REST

## REST Endpoints
Every endpoint accepts the URL parameter 'currency' with values like 'GBP', 'EUR', 'USD', etc. The parameter is used to calculate the prices.

### POST /api/login

#### POST Body
```javascript
{
    "user": String,
    "password": String
}
```

#### Return Value
```javascript
{
    "success": true|false,
    "token": String
}
```

### GET /api/car

Endpoint to retrieve a complete list of all cars registered.

#### Return Value
```javascript
{
    "success": true|false,
    "result": [
      {Car Object},
      {Car Object},
      ...
    ]
}
```

### POST /api/car/

Endpoint used to create a new car.
Parameters are mandatory.
The URL parameter 'currency' can be used to supply the price in a different currency. The price will be converted to the base currency USD with the exchange rate at the time of registering the car.
The car is available instantly.

#### POST Body
```javascript
{
    "name": String,
    "manufacturer": String,
    "type": String,
    "seats": int,
    "kw": double,
    "lat": String,
    "lon": String,
    "price": double
}
```
#### Return Value
```javascript
{
    "success": true|false,
    "id": int
}
```


### GET /api/car/{ID}

Endpoint used to query a specific car.

#### Return Value
```javascript
{ 
    "success": true|false,
    "result": {Car Object}
}
```

### DELETE /api/car/{ID}
Endpoint used to delete a specific car.
#### Return Value
```javascript
{
    "success": true|false
}
```


### PUT /api/car/{ID}
???

### POST /api/car/{ID}/rent

Endpoint to rent a given car.
Parameter 'date' is mandatory.
Format for parameter 'date': dd-MM-yyyy

#### POST Body
```javascript
{ 
    "return": date
}
```

#### Return Value
```javascript
{
    "success": true|false
}
```

### POST /api/car/{ID}/return

Endpoint to return a car.
Return location is necessary.

#### POST Body
```javascript
{
    "returnLon": String,
    "returnLat": String
}
```

#### Return Value
```javascript
{
    "success": true|false,
}
```

### POST /api/car/query

Used to query available cars for given parameters. 
Parameters are optional.
The respective null values will be used when the parameter is not included.

#### POST Body
```javascript
{
    "type": String,
    "seats": int,
    "priceMax": double,
    "priceMin": double,
    "date": date,
    "kw": double,
    "name": String,
    "manufacturer": String
}
```

#### Return Value
```javascript
{
    "success": true|false,
    "result": [
        {Car Object},
        {Car Object},
        ...
    ]
}
```

## Car object
```javascript
{
   "name": String,
   "manufacturer": String,
   "type": String,
   "seats": int,
   "kw": double,
   "available": boolean,
   "lat": String,
   "lon": String,
   "price": double,
   "availableOn": date
}
```
