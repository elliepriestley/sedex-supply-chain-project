# API Docs

## Description 
This is an API to fetch Supply Chain data. This document is in progress and will be updated incrementally.

## Base URL 

## Endpoints 

`GET /suppliers` returns a list of suppliers 

### Parameters

`type` i.e. `?type=direct` returns a list of all direct suppliers 

- If a query parameter is incorrect then it is silently ignored (assumes direct suppliers)


### Response 

Example response (JSON):

```
{
    "suppliers": [
        {
            "id": "ZC1234"
        },
        {
            "id": "ZC5678"
        }
    ]
}
```

If there are no suppliers, an empty array will be returned.


## Error Codes