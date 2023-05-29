package model;

public class Account {
    private String id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String city;
    private String avatar;

    public Account() {
    }

    public Account(String id, String email, String password, String role, String name, String city, String avatar) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.city = city;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
