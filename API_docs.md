# API Docs 💡

## 💬 Description 
This is an API to fetch Supply Chain data. This document is in progress and will be updated incrementally.

## 👩‍💻 Base URL 
`localhost:9000`

## 🔎 Endpoints 

`GET /suppliers` returns a list of direct suppliers 

### Parameters

#### 1. Type


`type` i.e. `?type=direct` returns a list of all direct suppliers 

- If a query parameter is incorrect then it is silently ignored (assumes direct suppliers)
- If the type specified is `indirect`, the program will return `Feature not available yet`

Example response (JSON) for a valid request:

```
{
    "companyId": "ZC789",
    "directSuppliers": [
        "ZS456",
        "ZS789",
        "ZS119"
    ]
}
```

If there are no suppliers, an empty array will be returned:
```
{
    "companyId": "ZC789",
    "directSuppliers": []
}
```

#### 2. Id

`id` i.e. `?id=ZS123` returns a list of the details of a supplier, specified by id. 

- If the id number is invalid, an Exception will be raised, and a 422 Error (Unprocessable Entity) will be returned. This indicates that the server cannot process your request, although it understands it.
- If the id number is valid, but the details are blank, an empty map `{}` will be returned.

Example response (JSON) for a valid request:

```
{
    "ZS456": {
        "name": "Appleton Farm",
        "location": "Warrington",
        "produce": "apples"
    }
}
```

Example response (JSON) for a valid request, but where supplier details are blank:

```
{
    "ZS456": {}
}
```

## ❌ Error Codes
This section is a WIP


## Future Scope for User Story 3 (WIP):
`POST /suppliers`

```
{
"name": "Ellie's pharmacy",
"...": "",
"...": ""  
}
```

Response:
HTTP 201
```
{
"id": "ZC9876",
"name": "Ellie's pharmacy",
"..":
"..":
".."
}

```