package model;

public class Person {
    private int Id;
    private String Firstname;
    private String Username;
    private String Email;
    private String Password;
    private boolean IsAdmin;

    public Person() {
        this.Id = 0;
        this.Firstname = "";
        this.Username = "";
        this.Email = "";
        this.Password = "";
        boolean isAdmin = false;
    }

    public Person(int id, String firstname, String username, String email, String password, boolean isAdmin) {
        this.Id = id;
        this.Firstname = firstname;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.IsAdmin = isAdmin;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        if(id >= 0) {
            this.Id = id;
        }
    }
    public String getFirstname() {
        return Firstname;
    }
    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        this.Username = username;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public boolean getIsAdmin(){
        return IsAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.IsAdmin = isAdmin;
    }

}
