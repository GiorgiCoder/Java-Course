Task:

Car Park

Goal of this assignment is to simulate a car park.

\- LicensePlate Realize a class LicensePlate with the attributes String
regionalCode, String letters and int digits together with a
constructor. - Car Realize a class Car with the attributes String brand
and LicensePlate licensePlate together with a meaningful constructor. -
chassisNumber Extend the class Car with an attribute int chassisNumber.
This number is going to increased by 1 for each newly built Car-object.
Hint: This works analogously to the class Count of the lecture. -
CarPark Realize a class CarPark which represents a car park with n
places to deposit cars (spaces). This size n of the car park is meant to
be provided to the constructor. - park Add to the class CarPark a method
int park(Car c) by which a car c receives the first empty space of the
car park. The method should additionall return the number of the
occupied space. When the car park is full, the value -1 should be
returned. - isEqual Extend the class LicensePlate by a method boolean
isEqual(LicensePlate other) which returns true if and only if when other
and this describe the same licence plate. - search Extend the class
CarPark with a method int search(LicensePlate lp) which searches for a
car by means of a license plate. If the car with license plate lp is
residing inside the car park, the method should return the number of the
occupied space, otherwise -1. - driveOff Extend the class CarPark with a
method Car driveOff(LicensePlate lp) which empties the space occupied by
the car with license plate lp and returns the corresponding car. If a
car with the given license plate lp cannot be found in the car park, the
value null should be returned. - toString Add to all classes a method
public String toString() which returns a String representation of the
respective objects. The output format is as follows: LicensePlate:
\<regionalCode\>:\<letters\> \<digits\> Car: Car \<chassisNumber\>:
(Brand: \<brand\>, License Plate: \<licensePlate\>) CarPark: Car
Park:\\n followed by \<index\>: \[\<car\>\]\\n or \<index\>: \[\]\\n for
every space
