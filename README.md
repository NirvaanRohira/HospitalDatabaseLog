# My Personal Project - Out Patient Directory

## Nirvaan Rohira

Questions needed to be answered
- What will the application do?
- Who will use it?
- Why is this project of interest to you?

This is going to be a OUT Patient Directory with Bill application
This means that anyone who uses it will be able to:-
- Fill in Patient details such as Name, Age, Condition, Care received, Days Stayed, Bill Due
- Search For a Patient using the Search option using LastName, FirstName
- Be able to see a list of treatment received along with cost per night of stay and special treatment costs of a
- Patient with the search function

Primarily this would be for people working in medical clinics and hospitals where there are services of care used along
with place of stay. However, once this project would be complete it can even potentially be edited to be able to work
with other services such as hotels and motels in order to calculate the amount a person should be charged based on
their specific criteria.

This Project is of Interest to me as Ive always wanted to understand how databases work and how they can be used to
search and believe that designing one will help me gain a better understanding of them.

##User Story
- As a user, I want to be able to fill In Patient Details in terms of a form
- As a user, I want to be able to Manually add, and remove Patient Details (functions implimented)
- As a user, I want to be able to Add in Name
- As a user, I want to be able to Add in Age
- As a user, I want to be able to Add in Duration of Stay
- As a user, I want to be able to Add in Treatment Recieved
- As a user, I want to be able to Add in Condition
- As a user, I want to be able to Add in Cost
- As a user, I want to be able to input and view these in the console.

##Phase 2 User Stories
- As a user, I want to be able to view the Names and Cost entered into the list
- As a user, I want to be able to clear the list completely
- As a user, I want to be able to clear the first patient from the list and view the rest
- As a user, I want to be able to save an entered list of patient data
- As a user, I want to be able to load an entered list of patient data

##Phase 4 Task 2:
The log is shown below and is working as intended, when any action regarding save, load or add is called, the 
appropriate response is called and when added, it says "added" followed by the name of the added person.

Loaded Patient List from ./data/patients.json
Tue Nov 23 14:29:49 PST 2021
added 1

Tue Nov 23 14:29:49 PST 2021
added Nirvaan

Tue Nov 23 14:29:49 PST 2021
added Vali

Tue Nov 23 14:29:49 PST 2021
added BT

Tue Nov 23 14:29:49 PST 2021
added Nirvaan

Tue Nov 23 14:29:49 PST 2021
loaded


Process finished with exit code 0


##Phase 4 Task 3:
The design presented in my UML diagram very frankly could have been made more streamlined.
It is currently in a way, quite symmetric in nature as it is not too complicated to see the nature of the relationship
as essentially the base class which is then called in the UI classes which each call these classes along with 
classes presented in the persistence package.

After careful re-inspection of my code with the new concepts learned in class, given more time I would most probably 
the code to make it more readable and streamlined. In terms of specifics I would probably implement the observable 
interface to reduce coupling between classes. I would also perhapes decompose some methods to make it more organised 
in my GUISwingClass. Lastly I would perhaps make an abstract Package which I would use to save abstract methods which
would greatly reduce the issue of making changes.
