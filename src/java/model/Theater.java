package model;

import java.util.Objects;

public class Theater {
    private String id;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String image;

    public Theater() {
    }

    public Theater(String id, String name, String street, String ward, String district, String city, String image) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Theater other = (Theater) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
