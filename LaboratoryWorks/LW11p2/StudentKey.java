package LW11p2;

class StudentKey {
    private int groupNumber;
    private String surname;

    public StudentKey(int groupNumber, String surname) {
        this.groupNumber = groupNumber;
        this.surname = surname;
    }

    Integer getGroupNumber() {
        return this.groupNumber;
    }

    String getSurname() {
        return this.surname;
    }
}