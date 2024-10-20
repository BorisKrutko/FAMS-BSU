package UniversityManagementSystem;

public class Student extends Person implements Notifiable {
    private String login;
    private String email;

    Student(String name, String login, String email) {
        super(name);
        this.login = login;
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "name: " + this.getName() + " login: " + this.getLogin() + " email: " + this.getEmail() + "\n";
    } 

    @Override
    public void notify(String massage) {
        System.out.println(" Student{login= " + login + ", " + "email=" + email + "}" + '\n');
    }
}
