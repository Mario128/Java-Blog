package model;

class Person {
    String name;
    String vorname;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Person(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }
}

