# Employee Hierarchy & Assessments Generator

This project generates **business hierarchies** (employees under supervisors up to the CEO) and creates **performance assessments** automatically.  
It has been designed following **SOLID principles** and is optimized to perform the **minimum number of iterations**, ensuring efficiency even for large hierarchies.

---

## ⭐ Features

- **Employee hierarchy generation**  
  - Level `0` is always the CEO (not counted).  
  - Each supervisor (except in the last level) has **3 subordinates**.  
  - Supervisors at the last level have **6 employees**.  
  - The user can define how many levels to generate (excluding CEO).  

- **Assessments generation**  
  - Each employee receives assessments from their **direct supervisor**.  
  - Assessments propagate upwards, so the employee is also evaluated by every supervisor up to the CEO.  
  - Example: if *Eva* reports to *Iris*, who reports to *Joan*, who reports to *CEO*, Eva will have **3 assessments**.  

- **Interactive menu**  
  - `1.` Generate employee hierarchy  
  - `1.1` More info about hierarchy  
  - `2.` Generate performance assessments  
  - `2.1` More info about assessments  
  - `3.` Exit  
  - Includes input validation and colored console messages.  

---

## 🧪 Testing

The project includes **unit tests** to ensure correctness and maintainability of the core logic:

- **JUnit 5** is used as the main testing framework.
- **Mockito** is used for mocking dependencies and isolating unit tests from external logic.
- Tests cover **all classes of the project code**. 

---

## 🧪 Project test structure

```
test/
├── Controller/
│ ├── EmployeeHierarchyTest.java # Tests recursive logic for hierarchy & assessments
│ └── MenuControllerTest.java # Tests menu interaction and input validation
│
├── Manager/
│ └── EmployeeManagerTest.java # Tests business logic: employees & assessments
│
├── Models/
│ ├── AssessmentTest.java # Tests Assessment entity behavior
│ └── EmployeeTest.java # Tests Employee entity behavior
│
├── Persistence/
│ └── EmployeeDataTest.java # Tests employee and hierarchy data generation
│
└── View/
└── MenuTest.java # Tests console-based UI
```

---

## 🏗️ Project structure

```
src/
├── Controller/
│ ├── EmployeeHierarchy.java # Recursive logic for hierarchy & assessments
│ └── MenuController.java # Handles user interaction with menu
│
├── Manager/
│ └── EmployeeManager.java # Business logic: load employees, generate assessments
│
├── Models/
│ ├── Employee.java # Employee entity
│ └── Assessment.java # Assessment entity
│
├── Persistence/
│ └── EmployeeData.java # Generates employees and hierarchy data
│
├── View/
│ ├── Menu.java # Console-based UI
│ └── ConsoleColors.java # ANSI color constants
│
└── Main.java # Entry point
```

---

## ⚙️ Example run

```
=== MAIN MENU ===
1. Generate employee hierarchy
    1.1 More info about hierarchy

2. Generate performance assessments
    2.1 More info about assessments

3. Exit
Select an option: 1

Enter the number of levels (excluding CEO): 3
Employees loaded with 3 levels. Total employees: 67

=== MAIN MENU ===
...
Select an option: 2

Assessments created:
{'Employee ref=Employee{'id='2', name='Joan', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}', year=2025', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}', id=2'}
{'Employee ref=Employee{'id='5', name='Iris', isSupervisor=true', supervisor=Employee{'id='2', name='Joan', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}', year=2025', supervisor=Employee{'id='2', name='Joan', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}', id=5'}
{'Employee ref=Employee{'id='5', name='Iris', isSupervisor=true', supervisor=Employee{'id='2', name='Joan', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}', year=2025', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}', id=5'}
...
Number of assessments created: 183

=== MAIN MENU ===
...
Select an option: 3

Exiting program...
```

---

## Example hierarchy (for 3 levels)

```
CEO: Joan (1)
 ├── David (2)
 │    ├── Employee 5
 │    │     ├── Employee 14 … 19
 │    ├── Employee 6
 │    │     ├── Employee 20 … 25
 │    └── Employee 7
 │          ├── Employee 26 … 31
 ├── Artur (3)
 │    ├── Employee 8
 │    │     ├── Employee 32 … 37
 │    ├── Employee 9
 │    │     ├── Employee 38 … 43
 │    └── Employee 10
 │          ├── Employee 44 … 49
 └── Marta (4)
      ├── Iris 11
      │     ├── Employee 50 … 55 (Eva is here)
      ├── Employee 12
      │     ├── Employee 56 … 61
      └── Employee 13
            ├── Employee 62 … 67
```

---

## Example assessments - Eva (50) is evaluated by Iris (11), by Marta (4), and by CEO (1)

```
{'Employee ref=Employee{'id='50', name='Eva', isSupervisor=false', supervisor=Employee{'id='11', name='Iris', isSupervisor=true', supervisor=Employee{'id='4', name='Marta', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}'}', year=2025', supervisor=Employee{'id='11', name='Iris', isSupervisor=true', supervisor=Employee{'id='4', name='Marta', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}', id=50'}
{'Employee ref=Employee{'id='50', name='Eva', isSupervisor=false', supervisor=Employee{'id='11', name='Iris', isSupervisor=true', supervisor=Employee{'id='4', name='Marta', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}'}', year=2025', supervisor=Employee{'id='4', name='Marta', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}', id=50'}
{'Employee ref=Employee{'id='50', name='Eva', isSupervisor=false', supervisor=Employee{'id='11', name='Iris', isSupervisor=true', supervisor=Employee{'id='4', name='Marta', isSupervisor=true', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}'}'}'}', year=2025', supervisor=Employee{'id='1', name='Joan', isSupervisor=true', supervisor=None'}', id=50'}

```


