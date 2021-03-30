# **Medical Office**
###Classes

* Person 
    * abstract class
        - First name
        - Last name
        - Age

* Patient
    * abstract class
    * extends Person
        - Id

* Adult 
    * extends Patient
        - Phone number

* Child 
    * extends Patient
        - Mother's name
        - Father's name
    
* Employee
    * abstract class
    * extends Patient
        - Id 
        - Tax percentage
        - Salary
        - Years of Experience
    
* Doctor
    * extends Employee
        - Shift
        - Specialization
    * calculate annual income
    * check for night shift bonus
    
* Assistant
    * extends Employee
        - bonus
    * calculate annual income
    
* Prescription
    * Aggregation
        - Patient
        - Employee
        - Diagnosis
        - Medicine - Linked List
    
* Medicine
    * Name
    * Price
    * Quantity
    
* Appointment
    * Aggregation
        * Discount
        * Date
        * Start Time
        * End Time
        * Patient
        * Price
    * Calculate duration of the appointment
    * Apply discount for children
    
####Enums
* Shift
* Specialization 
* Diagnosis
    
###Service Class
* MedicalOffice
    * Singleton pattern
        * Office Name
        * Patients - ArrayList
        * Employees - ArrayList
        * Appointments & Prescriptions - TreeMap 
    * Actions
        1. Add a new employee to the list
        2. Add a new patient to the list
            - Age validation
            - Phone number validation
        3. Print the list of employees
        4. Print the list of patients
        5. Make a new appointment 
            - checked if any appointments are overlapping 
        6. Print list of appointments ordered by date and time
            - custom comparator for date and time sorting
        7. Write prescription for a patient and assign it to an appointment
            - includes a method for prescribing meds
        8. Print all prescriptions for a specific patient
        9. Sort employees by criteria:
            - Annual Income
            - Years of Experience
        10. Apply discount for all of children's appointments
    
* Menu 
    * calls all the actions from above 