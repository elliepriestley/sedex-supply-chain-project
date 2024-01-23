# Sedex Supply Chain Project 🧩

This is a guide on the features of this program.

## 💬 Description

This project is part of a learning path, following [these](https://github.com/aceakash/programming-exercises/blob/main/supply_chain.md) instructions.

## 📚 Table of Contents
- [Dependencies](#-dependencies)
- [User Stories](#-user-stories)
  - [User story 1](#user-story-1)
  - [User story 2](#user-story-2)
  - [User story 3](#user-story-3)
- [Technical Notes](#-technical-notes)
- [Assumptions](#-assumptions-)
## 💡 Dependencies
- Java version 17 or above
- Gradle?
- Jackson

## 👥 User Stories

### User story 1 ✅

As an admin at a top-level customer org
I want to view a list of my direct suppliers
 
### User story 2 📍

As an admin at a top-level customer org
I want to get the details of a direct supplier that I specify by ID.

### User story 3

As an admin at a top-level customer org
I want to add a direct supplier to my supply chain

## 📝 Technical notes

- The interface to this functionality will be a RESTful web API.
- The API endpoints will be developed in a separate markdown file as the program grows.
- TODO(): Create a GitHub build pipeline to run your tests and build the code on every commit to trunk.


## 🧠 Assumptions 

This section details any notable assumptions or questions made during the design process.

- Assumption: when no optional query parameter provided, the suppliers endpoint will return direct suppliers
- Assumption: when an optional query parameter is provided and is invalid, the suppliers endpoint will return direct suppliers
- Assumption: when user requests details for specific supplier by ID, they have permission to view this information, i.e. the program currently does not have rules for visibility. 