# SINT_REST

## REST Endpoints

### GET /api
???

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

### PUT /api/car/{ID}
???

### POST /api/car/{ID}/rent

Endpoint to rent a given car.
Parameter 'date' is mandatory.
Location is optional.

#### POST Body
```javascript
{ 
    "return": date,
    "returnLon": String,
    "returnLat": String
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
    "id": int,
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
    "count": int,
    "offset": int
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
   "price": double
}
```
