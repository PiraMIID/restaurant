package pl.ki.recruitment.restaurant.domain.localization;

import java.util.Objects;

public class Localization {
    private Long id;
    private String country;
    private String city;
    private String address;

    public Localization(Long id, String country, String city, String address) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localization that = (Localization) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, address);
    }
}
