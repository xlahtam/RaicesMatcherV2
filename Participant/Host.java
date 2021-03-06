// Created by: Alx Pareja
package Participant;

public class Host implements RaicesParticipant {
    private String name;
    private String email;
    private String phone;
    private String homeTown;
    private Student[] hosting;
    private boolean[] accommodations;
    private int priority;
    private int capacity;

    public Host(String name, String[] accommodations, String homeTown, String phone, String email, String capacity) {
        this.name = name;
        this.homeTown = homeTown;
        this.phone = phone;
        this.email = email;
        this.accommodations = new boolean[accommodations.length];
        this.priority = setPref(accommodations);
        this.capacity = Integer.parseInt(capacity);
        this.hosting = new Student[this.capacity];
    }

    // Sets the priority of the host and initializes its
    private int setPref(String[] accommodationList) {
        int hostPriority = 0;

        try {

            for (int i = 0; i < accommodationList.length; i++) {
                if (accommodationList[i].equals("true")) {
                    hostPriority += (i + 1);
                    this.accommodations[i] = true;
                } else {
                    this.accommodations[i] = false;
                }
            }

        } catch (NullPointerException e) {
            System.out.println(this.name + " does not have sufficient accommodation information.");
        }

        return hostPriority;
    }

    // Matches the Participant.Host with given Participant.Student
    protected void match(Student s) {
        hosting[hosting.length - capacity] = s;
        capacity -= 1;
    }

    // Returns true if host has at least one match and false otherwise
    public boolean isHosting() {
        return hosting[0] != null;
    }

    /**
     * The following methods are used to retrieve information about the Participant.Host.
     * Method names indicate the information that is retrieved.
     * All methods do not require an input.
     */

    // Returns the host's name
    public String name() {
        return name;
    }

    public String firstName() { return name.split("\\s+")[1]; }

    public String lastName() { return name.split("\\s+")[0]; }

    // Returns the host's email
    public String email() {
        return email;
    }

    // Returns the host's phone number
    public String phone() {
        return phone;
    }

    // Returns the host's hometown
    public String homeTown() {
        return homeTown;
    }

    // Returns the host's priority
    public int priority() {
        return priority;
    }

    // Returns the host's capacity
    public int capacity() {
        return capacity;
    }

    // Returns whether or not host is at capacity
    public boolean isMatched() {
        return hosting[hosting.length - 1] != null;
    }

    // Returns string array with required accommodations
    public boolean[] getAccommodations() {
        return accommodations;
    }

    public Student[] getHosting() {
        return hosting;
    }

    // Prints the students that are currently being hosted
    public void printMatches() {
        if (!isHosting()) {
            System.out.println(name + " is not hosting anyone.");
        } else {
            String returnString = name + " is hosting: ";
            for (Student student: hosting) {
                returnString += student.name() + ", ";
            }
            returnString = returnString.substring(0, returnString.length() - 2);
            System.out.println(returnString);
        }
    }

    // Compares one participant to another
    @Override
    public int compareTo(Participant other) {
        return this.priority - other.priority();
    }



    // Used for testing purposes
    public static void main(String[] args) {
        String[] accommodations = {"true", "true", "true"};
        String capacity = "5";

        Host h = new Host("host", accommodations, "hostown",
                "323-981-1234", "host@ymail.com", capacity);

        System.out.println(h.name());
        System.out.println(h.email());
        System.out.println(h.phone());
        System.out.println(h.homeTown());
        System.out.println("Participant.Host's priority is: " + h.priority());
        System.out.println("Participant.Host's capacity is: " + h.capacity());
        System.out.println("Is the host fully matched? " + h.isMatched());
    }
}
