package pl.stalostech.rest.model;

/**
 * Class representing left menu.
 * {
 *  id : 1,
 *  section : "Some name"
 * }
 * @author Bartosz Wysocki
 */
public class Menu {

    private int id;

    private String section;

    public Menu(int id, String section){
        this.id = id;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", section=" + section + "]";
    }

}
