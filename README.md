IRCTC Console Booking App
=========================

A simple Java console-based train booking system inspired by IRCTC.
This project demonstrates Java, Gradle, and basic OOP concepts while simulating train search, seat booking, and ticket generation.

Features
--------
- Search trains between two stations
- Book seats on available trains
- Generate tickets with unique ticket IDs
- User system – Signup & Login
- Booking history stored with each user
- Error handling (invalid input, seat already booked, etc.)

Requirements
------------
- Java 17 or later
- Gradle (comes with the wrapper gradlew)
- Git (to clone the repo)

Getting Started
---------------
1. Clone the Repository:
   git clone https://github.com/<your-username>/IRCTC.git
   cd IRCTC

2. Build the Project:
   gradle build

3. Run the Application:
   gradle run

------------------
## Supported Cities

Currently, the dataset is small, so the train search feature only supports these locations:

- Mumbai
- Bangalore
- Delhi
- Kolkata
- Guwahati

⚠️ Please make sure to enter the city names exactly as listed above when searching for trains.
