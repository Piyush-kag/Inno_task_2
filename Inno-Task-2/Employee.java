import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int yearOfJoining;
    private double salary;

    public Employee(int employeeID, String name, Integer age, String gender, String department, Integer yearOfJoining,
            Double salary) {

        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.gender = gender != null ? gender : "Unknown";
        this.department = department != null ? department : "Unknown";
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    // Getter methods to access employee attributes
    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return "Employee ID: " + employeeID +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nDepartment: " + department +
                "\nYear of Joining: " + yearOfJoining +
                "\nSalary: " + salary + "\n";
    }

    /**
     * @param ar
     */
    public static void main(String[] ar) {
        // Male Female Count

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        // Male and Female Count

        Map<String, Long> genderCount = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        // Get the count of male and female employees
        long maleCount = genderCount.getOrDefault("Male", 0L);
        long femaleCount = genderCount.getOrDefault("Female", 0L);
        System.out.println("\n");
        // Display the counts
        System.out.println("Number of Male Employees: " + maleCount);
        System.out.println("Number of Female Employees: " + femaleCount);

        // Department and EmployeeCount

        Map<String, Long> departmentCount = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        // Display department counts
        System.out.println("\n");
        departmentCount.forEach((department, count) -> {
            System.out.println("Department: " + department + " - Employee Count: " + count);
        });
        System.out.println("\n");

        // Average Age of male and female Count

        Map<String, Double> averageAgeByGender = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge)));

        // Get the average age of male and female employees
        double averageAgeMale = averageAgeByGender.getOrDefault("Male", 0.0);
        double averageAgeFemale = averageAgeByGender.getOrDefault("Female", 0.0);

        // Display the average age
        System.out.println("Average Age of Male Employees: " + averageAgeMale);
        System.out.println("Average Age of Female Employees: " + averageAgeFemale);

        System.out.println("\n");

        //

        List<String> namesOfEmployeesJoinedAfter2015 = employeeList.stream()
                .filter(employee -> employee.getYearOfJoining() > 2015)
                .map(Employee::getName)
                .collect(Collectors.toList());

        // Display the names of employees who joined after 2015
        System.out.println("Names of Employees Who Joined After 2015:");
        namesOfEmployeesJoinedAfter2015.forEach(System.out::println);

        Map<String, Double> averageSalaryByDepartment = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("\n");

        // Display the average salary per department
        System.out.println("Average Salary Per Department:");
        averageSalaryByDepartment.forEach((department, avgSalary) -> {
            System.out.println("Department: " + department + " - Average Salary: " + avgSalary);
        });

        // youngest male employee in the product development department

        Employee youngestMaleInProductDev = employeeList.stream()
                .filter(employee -> employee.getGender().equals("Male")
                        && employee.getDepartment().equals("Product Development"))
                .min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .orElse(null);

        // Check if a youngest male employee in "Product Development" was found
        System.out.println("\n");

        if (youngestMaleInProductDev != null) {
            // Display the details of the youngest male employee in "Product Development"
            System.out.println("Details of the Youngest Male Employee in Product Development:");
            System.out.println("Employee ID: " + youngestMaleInProductDev.employeeID);
            System.out.println("Name: " + youngestMaleInProductDev.name);
            System.out.println("Age: " + youngestMaleInProductDev.age);
            System.out.println("Gender: " + youngestMaleInProductDev.gender);
            System.out.println("Department: " + youngestMaleInProductDev.department);
            System.out.println("Year of Joining: " + youngestMaleInProductDev.yearOfJoining);
            System.out.println("Salary: " + youngestMaleInProductDev.salary);
        } else {
            System.out.println("No youngest male employee found in the Product Development department.");
        }
        System.out.println("\n");

        // List down the names of all employees in each department

        Map<String, List<String>> namesByDepartment = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())));

        // Display the names of employees in each department
        System.out.println("Names of Employees in Each Department:");
        namesByDepartment.forEach((department, names) -> {
            System.out.println("Department: " + department);
            names.forEach(name -> System.out.println("  - " + name));
        });

        System.out.println("\n");

        // How many male and female employees are there in the sales and marketing team

        Map<String, Long> genderCountInSalesAndMarketing = employeeList.stream()
                .filter(employee -> employee.getDepartment().equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        // Get the count of male and female employees in "Sales and Marketing"
        long maleC = genderCountInSalesAndMarketing.getOrDefault("Male", 0L);
        long femaleC = genderCountInSalesAndMarketing.getOrDefault("Female", 0L);

        // Display the counts
        System.out.println("Number of Male Employees in Sales and Marketing: " + maleC);
        System.out.println("Number of Female Employees in Sales and Marketing: " + femaleC);

        System.out.println("\n");

        // What is the average salary of male and female employees

        Map<String, Double> averageSalaryByGender = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingDouble(Employee::getSalary)));

        // Display the average salary for male and female employees
        System.out.println("Average Salary for Male and Female Employees:");
        averageSalaryByGender.forEach((gender, avgSalary) -> {
            System.out.println("Gender: " + gender);
            System.out.println("Average Salary: " + avgSalary);
        });
        System.out.println("\n");
        // What is the average salary and total salary of the whole organization?

        double totalSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        double averageSalary = totalSalary / employeeList.size();

        // Display the total salary and average salary
        System.out.println("Total Salary of the Whole Organization: " + totalSalary);
        System.out.println("Average Salary of the Whole Organization: " + averageSalary);

        System.out.println("\n");

        // Separate the employees who are younger or equal to 25 years from those
        // employees who are older than 25 years?

        List<Employee> youngerOrEqual25 = employeeList.stream()
                .filter(employee -> employee.getAge() <= 25)
                .collect(Collectors.toList());

        List<Employee> olderThan25 = employeeList.stream()
                .filter(employee -> employee.getAge() > 25)
                .collect(Collectors.toList());

        // Display the separated employees
        System.out.println("Employees 25 Years Old or Younger:");
        youngerOrEqual25.forEach(
                e -> System.out.println("Employee ID: " + e.employeeID + ", Name: " + e.name + ", Age: " + e.age));

        System.out.println("\nEmployees Older Than 25 Years:");
        olderThan25.forEach(
                e -> System.out.println("Employee ID: " + e.employeeID + ", Name: " + e.name + ", Age: " + e.age));

        System.out.println("\n");

        // Who is the oldest employee in the organization? What is his age and which
        // department he belongs to?

        Employee oldestEmployee = employeeList.stream()
                .max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                .orElse(null);

        // Check if the oldest employee was found
        if (oldestEmployee != null) {
            // Display the details of the oldest employee
            System.out.println("Oldest Employee in the Organization:");
            System.out.println("Name: " + oldestEmployee.getName());
            System.out.println("Age: " + oldestEmployee.getAge());
            System.out.println("Department: " + oldestEmployee.getDepartment());
        } else {
            System.out.println("No oldest employee found.");
        }

        // Get the details of highest paid employee in the organization

        Optional<Employee> highestPaidEmployee = employeeList.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        // Check if an employee with the highest salary was found       
        if (highestPaidEmployee.isPresent()) {
            Employee highestPaid = highestPaidEmployee.get();
            // Display the details of the highest-paid employee
            System.out.println("Details of the Highest-Paid Employee:");
            System.out.println("Employee ID: " + highestPaid.employeeID);
            System.out.println("Name: " + highestPaid.name);
            System.out.println("Age: " + highestPaid.age);
            System.out.println("Gender: " + highestPaid.gender);
            System.out.println("Department: " + highestPaid.department);
            System.out.println("Year of Joining: " + highestPaid.yearOfJoining);
            System.out.println("Salary: " + highestPaid.salary);
        } else {
            System.out.println("No employee found in the organization.");
        }
    }
}