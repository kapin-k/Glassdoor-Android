# Glassdoor-Android

Make an Android app following the specifications below:
Rules
 This exercise has been designed to have a minimum implementation in at least 3
hours. 
 Use any 3rd party library / framework. Explain in Readme.MD as to why you are
using these libraries.
 Code should compile and run in an emulator.
 It is ok to target minSdk = 24
Expectations
 Clean, modular and organized project structure.
 Adoption of good Android patterns and best practices.
 It is enough if the above expectations are met to pass the evaluation. If you want to
show an area of expertise for bonus points, you can:
 Kotlin
 Write tests.
 Appropriate screen transitions.
Scenario:
User is interested in keeping a tab on latest reviews, salaries and interviews from three
or more companies that they follow on Glassdoor. User opens the app.
Screen 1:
List of latest 20+ reviews and salaries as fetched from the API:
https://raw.githubusercontent.com/vikrama/feed-json-sample/master/feed.json

Tasks:
 Consume the API and use Review, Salary and Interview objects to populate a list.
 Design your own UI and populate them on screen in any way you think is
appropriate.
 Clicking each item will open Screen 2 with details of each entity.

2
Screen 2:
Details screen for the review or salary

Tasks:
 No need to make a new API call.
 Pass bundle data from the previous screen.
 Populate this UI in any way you see fit.

| Screen 1      | Screen 2      | Screen 3   |
|------------|-------------|-------------|
| <img src="https://i.postimg.cc/nLZf7258/Main-Activity-Review.png" width="250"> | <img src="https://i.postimg.cc/d1VMN1w4/Detail-Activity-Review.png" width="250"> |<img src="https://i.postimg.cc/0QWTTsNx/Detail-Activity-Interview.png" width="250"> |
