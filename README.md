# ProjectManager Returns
# This time with more features and database use

Recap: this is an "Project managing" application that utilizes object oriented programming and databases.

The program is run from "Main.java"

Scenario: "You have been asked to create a project management system for a small
structural engineering firm called “Poised”. Poised does the engineering needed to
ensure the structural integrity of various buildings. They want you to create a Java
program that they can use to keep track of the many projects on which they work"

The application is able to:
- Capture information about new projects. If a project name is not provided
when the information is captured, the project is named using the surname of
the customer.
- Updates information about existing projects.
- Finalise existing projects:
    -An invoice is generated for the client. If the full amount hasn't
    been paid. 
    -The project is be marked as “finalised” with the completion date,
    and then added to a text file called “Completed projects”.
- Show a list of projects that are not completed.
- Show a list of projects past their due date.
- Search and find a project by either the project number or project name.

The screenshots & diagrams folder contains the dependency diagrams for the databases
and well as the statements required to set up the sql database.

The program has this new feature:
- Each project is assigned to one Project Manager, Architect, Customer, Contractor
  & Structural Engineer. The user can create new people for this or select from 
  existing people.

and also demonstrates:
- Exception handling.
- Concise refactoring.
- Documentation (Javadoc)
