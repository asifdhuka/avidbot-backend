# Chorus back-end engineer assessment submission

This project contains the rest api which processes map and robot statistics.

## Building and installing code:

Here are the steps to building and installation of the project.

1. Use following command for maven build.
```
mvn clean install
```
2. To run code, use following command.
```
mvn spring-boot:run
```

This will start the application on http://localhost:5000


## API Calls:

1. Use following endpoint process the map. 

```
http://localhost:5000/avidbotsRobot/process
```
Request body:
```
{
    "inputMap": "####################\n#             ## # #\n# # ## #####   # # #\n# # ## #####  ## # #\n# #            #   #\n# # ########  #### #\n# #              # #\n# # ########  ## # #\n#                # #"
}
```

2. Use following to get all updates on statistics.

```
http://localhost:5000/avidbotsRobot/statistics
```
The response should look like:
```
{
    "currentPosition": "1,18",
    "totalEmptySpaces": 90,
    "totalWalls": 90,
    "cleanedSpace": 90,
    "coveragePercentage": 100.0,
    "cleaningDone": true,
    "newCleanedSpacePerSecond": 0,
    "doneMessage": "I am done with cleaning, its time to go back to home"
}
```
