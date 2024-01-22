# API Docs 💡

## 💬 Description 
This is an API to fetch Supply Chain data. This document is in progress and will be updated incrementally.

## 👩‍💻 Base URL 
`localhost:9000`

## 🔎 Endpoints 

`GET /suppliers` returns a list of direct suppliers 

### Parameters

`type` i.e. `?type=direct` returns a list of all direct suppliers 

- If a query parameter is incorrect then it is silently ignored (assumes direct suppliers)
- If the type specified is `indirect`, the program will return `Feature not available yet`

### Response 

Example response (JSON):

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

## ❌ Error Codes
This section is a WIP

## Future Scope for User Story 2:

/suppliers/:id
Returns a specific supplier by ID,
Example response (JSON):

```
{
            "id": "ZC1234",
            "name": "Leah's sister's farm",
            "..":
            "..":
            ".."
}
```

## Future Scope for User Story 3:
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