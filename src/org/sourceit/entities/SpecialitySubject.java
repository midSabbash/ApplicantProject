package org.sourceit.entities;

public class SpecialitySubject extends Entity {

    public Profession profession;
    public Subject subject;

    public SpecialitySubject(Profession profession, Subject subject) {
        this();
        this.profession = profession;
        this.subject = subject;
    }

    public SpecialitySubject() {
        this.id = -1;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
