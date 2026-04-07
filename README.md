<div align="LEFT">

# MoodMap
**Keeping track of YOUR mood.**

[![Visit MoodMap](https://img.shields.io/badge/Visit%20MoodMap-ffffff?style=for-the-badge&color=392580)](http://moodmap.guacamoleboy.dk)
[![Visit Devlog](https://img.shields.io/badge/Visit%20Devlog-ffffff?style=for-the-badge&color=392580)](http://moodmap.guacamoleboy.dk/dev-log)

</div>

---

### MoodMap's Mission
**Data tracking for Clinicians and Clients.**

Providing data directly to your clinician and yourself to show **real-time progress**.

> **The Problem:**
>
> If you've been to a clinician — whether for physical or mental health — you're often left alone for long periods between checkups. After months, you're asked: *"How do you feel?"* or *"Any improvements?"*
> This is an **"on the spot"** question. Patients describe how they *think* they've progressed, which is often inaccurate. This lack of objective data can lead to incorrect medication, inefficient follow-ups, and long-term consequences.

---

### Links

**Devlog / Portfolio Website**
> moodmap.guacamoleboy.dk/dev-log

**Project overview video**
> youtube.com/watch?v=jj4iZp51XEA

**Deployed application**
> api.guacamoleboy.dk/v1

**Source code repository:**
> github.com/Guacamoleboy/3-Semester-Friday

**ER-Diagram**
> dbdocs.io/cph-jl325/MoodMap-ER-Diagram?view=relationships

---

### Use Case

1. **Diagnosis:** A client is diagnosed with depression.
2. **Prescription:** The clinician prescribes anti-depressants for 3 months.
3. **The Problem:** Client takes the medication but has no system for logging daily moods or side effects.
4. **The Review:** After 3 months, the doctor asks *"How has it been?" and static questions as such*.
5. **The Error:** The client, relying on memory, says *"It's been alright"*, forgetting side effects or "lows" that occurred weeks ago.
6. **Result:** The clinician extends the prescription based on incomplete information, even though the client might still be clinically depressed or suffering side effects.

_

**Link to User Stories**
> github.com/Guacamoleboy/3-Semester-Friday/wiki/Backend-User-Stories

---

### Architecture

**MoodMap REST API** has been built with a high architecture point of view. I've tries to reduce spaghetti, boiler plate, DRY and split things into folders in order to keep SoC as good as possible.

#### Flow (full):
Client → Controller -> DTO/Validation → Service → DAO → Entity → Service → ClassResponseDTO → Client

#### Flow (Inbound):
Client → Controller → DTO/Validation → Service → DAO → Entity

#### Flow (Outbound):
Entity → Service → ClassResponseDTO → Controller → Client

---

### Key Design Decisions

I've tried to add as many things as I possibly could in the timeframe I had. This resulted in API access levels, JWT checks and deep learning using .md files with learning features and assets for myself to evolve.

If I had to showcase something I would probably showcase the way I handle CRUD operations by using:

1. IDAO
2. EntityManagerDAO<T>
3. EntityManagerService<T>
4. CRUDController<T>
5. IRoute
6. CRUDRouting<T>
7. ATest

Trying to reduce as much boilerplate as I possibly could while keeping it generic and working as intended at all times.
There are probably better ways of doing these things, but I feel like this is an efficient way of coding.

_

I've included TryCatchHelper and the use of JSON formatting in order to keep the style and output the same for all Controller JSON outputs.
This helps reduce the guessing and keeps an overall architecture of the JSON layout for the entire application.

The first point of adding this was to handle exceptions in one util class.. But as the project evolved I learned that you could simply handle the exception
in the Server.java file with Global Exception Handle. So the TryCatchHelper is basically a JSON output formatter at this point.

_

I wanted to add better and more efficient ways of handeling access to specific endpoints but I ran out of time.
For example the get/{id} endpoint should not allow anyone to simply call any ID. Specificly. If a JWT token holder calls the endpoint on an ID that isn't theirs..

That is breach of security and personal data, but - I ran out of time. I am fully aware of the issue.

---

# Important Entities

The most important entites in my appliation are probably | (ranked by weight - high to low)

1. User
2. Role
3. Client
4. API
5. SideEffectMedication

The first 3 I'm not gonna talk much about. It's obvious why they are important. There's no application without and no JWT Tokens.

The API though.. That's another security layer I've added instead of only having JWT. It allows companies (clinicians) to buy access to specific endpoints and keep track of their usage.
I've not added the usage part but could probably be handeled with Redis in order to rate-limit the usage.

SideEffectMedication is important as it is the entity being used for Scraping from pro.medicin.dk in order to download, insert and show side effects for a specific medication. For example Panodil.

Overall I feel like the Entities have a rather close relationship - meaning it's hard really sorting them from important to least important as the application is nothing without either.

---

# API Documentation

Throughout the development of my application I've worked with .http files and the use of curl commands from terminal in order to test endpoints. 
I've also added a logger and a way of seeing it an application is being ran on the correct environment and what the specific and final endpoint is.

Here are some endpoints I'd like to point out though:

```http
### POST | Should create a new API Key
POST {{baseUrl}}/access/create
Authorization: Bearer {{ACCESS_TOKEN}}
Content-Type: {{contentType}}

{
    "api_name": "name"
}
```

```http
### GET | Should get meta data for API
GET {{baseUrl}}/access/{{API_KEY_ID}}
Content-Type: {{contentType}}
Authorization: Bearer {{ACCESS_TOKEN}}
```

```http
### POST - Should log in
POST {{baseUrl}}/auth/login
Content-Type: {{contentType}}

{
    "username": "username",
    "password": "password"
}
```

```http
### POST | Should load side effects for a drug from pro.medicin.dk
POST {{baseUrl}}/scrape/medicin/{{MEDICATION_ID}}
Content-Type: {{contentType}}
```

_

Variables have not been included.

---

# User Stories

**Visit this link for User Stories or go to "Wiki" under this repo**
> github.com/Guacamoleboy/3-Semester-Friday/wiki/Backend-User-Stories

---

### What problem MoodMap solves

* **Data:** No more guessing during reviews.
* **Actions:** Automated insights based on trends.
* **Schedules:** Exercises specified by your clinician.
* **Better help:** Providing the right solution to the client. 

---

# Development Notes

I ran out of time so there are things I have not been able to complete and or optimize the way I wanted it to be optimized. It can be done later but for now it is as it is. Unfortunately.
I do feel like I managed to create a solid REST API with useful features and implementations along with CI/CD pipeline, Unit Tests, Integration Tests, .http files and a lot of other stuff.

Still trying to evolve and learn as a developer so I do tend to waste a lot of time on "useless" things such as .md files for learning or trying to optimize things where it's probably not needed in the stage of the education.
Either way I do feel like I'm satisfied with what I'm handing over.

What I've learned during this Project that I didn't know as much as before:

1) Scraping using Java. I've worked with scraping before, but not in Java.
2) CI/CD deployment pipeline. I've only worked with CI for Tests on Pull Requests on Github Workflow. Nice feature. Saves a lot of time.
3) Deep Learning of JWT Tokens. I knew of JWT but the deep learning made me understand exatly what and how it works.
4) JPA & JPQL Queries and the efficient of Hibernation compared to normal SQL.
5) SoC and DRY on another level than what I've previously done.
6) Deep learning of DTO and why they are important.
7) Threads, Cache & Efficiency.

---

### Technology

| Component | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Javalin 7.0.1 |
| **ORM** | Hibernate 7.1.0 |
| **Database** | PostgreSQL 42.7.7 |
| **Auth** | JJWT 0.12.5 |
| **Password Hashing** | jBCrypt 0.4 |
| **JSON** | Jackson 2.13.0 |
| **HTML Parsing** | Jsoup 1.22.1 |
| **Logging** | SLF4J + Logback 1.5.18 |
| **Environment** | dotenv-java 3.0.0 |
| **Build Tool** | Maven 3.13.0 |
| **Testing** | JUnit Jupiter 5.13.4 |
| **Integration Testing** | REST Assured 5.3.0 |
| **Test Containers** | Testcontainers 1.19.1 |
| **Boilerplate** | Lombok 1.18.38 |
| **Dev Log** | [![Visit Devlog](https://img.shields.io/badge/Visit%20Devlog-ffffff?style=for-the-badge&color=392580)](http://moodmap.guacamoleboy.dk/dev-log) |

---

<div align="center">
<sub>MoodMap - Keeping track of YOUR mood.</sub>
</div>
