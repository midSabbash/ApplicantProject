package org.sourceit.entities;

public class Applicant extends Entity {

    private long professionId;
    private String professionName;
    private String lastName;
    private String firstName;
    private int entranceYear;

    public Applicant() {
        this.id = -1;
    }

    public Applicant(long professionId, String professionName, String lastName, String firstName, int entranceYear) {
        this();
        this.professionId = professionId;
        this.professionName = professionName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.entranceYear = entranceYear;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(long professionId) {
        this.professionId = professionId;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionName() {
        return professionName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }
}
