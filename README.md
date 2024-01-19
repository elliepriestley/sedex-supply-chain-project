# Supply Chain Project

## Dependencies
Java version 17 or above
Gradle?
Jackson

## User Stories

### User story 1

As an admin at a top-level customer org

I want to view a list of my direct suppliers

#### API docs

/suppliers?type=direct

Returns a list of all direct suppliers.

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

### User story 2

As an admin at a top-level customer org

I want to get the details of a direct supplier that I specify by ID.

#### API docs

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

### User story 3

As an admin at a top-level customer org

I want to add a direct supplier to my supply chain



POST /suppliers
{
"name": "Ellie's pharmacy",
"...": "",
"...": ""  
}

Response:
HTTP 201

{
"id": "ZC9876",
"name": "Ellie's pharmacy",
"..":
"..":
".."
}

### User story 4

As an admin at a top-level customer org

I want to view a list of my first-level intermediaries to suppliers

### User story 5

As an admin at a top-level customer org

I want to view a list of all my suppliers through a given first-level intermediary

### User story 6

As an admin at a top-level customer org

I want to view a list of all my suppliers

### User story 7

As an admin at a top level customer org

I want a visual representation of my supply chain

## Technical notes

The interface to this functionality will be a RESTful web API.

Document the API endpoints in a separate markdown file as you develop them.

Create a Github build pipeline to run your tests and build the code on every commit to trunk.

Initially, keep your data in a JSON file.

## Assumptions 

This section details any notable assumptions or questions made during the design process.

- Assumption: when no optional query parameter provided, the suppliers endpoint will return ^direct^ suppliers