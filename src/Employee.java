public class Employee {
    private String national_code;
    private String name;
    private String last_name;
    private String pass;
    private String email;
    private int person_id;
    static int id=1;
    private double salary;
    private int workcode;

    public Employee( String name, String last_name,String national_code,String workcode, String email, String pass, double salary) {
        this.person_id = id;
        this.workcode= Integer.parseInt(id+national_code);
        this.national_code = national_code;
        this.name = name;
        this.last_name = last_name;
        this.pass = pass;
        this.email = email;
        this.salary = salary;
        id++;
    }

    public String getNational_code() {
        return national_code;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public int getPerson_id() {
        return person_id;
    }

    public double getSalary() {
        return salary;
    }

    public int getWorkcode() { return workcode;}


    public void setNational_code(String national_code) {
        this.national_code = national_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkcode(int workcode) {
        this.workcode = workcode;
    }
}
