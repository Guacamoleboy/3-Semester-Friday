package dk.project.enums;

public enum RoleEnum {

    // Enums
    ADMIN("Admin", "System administrator"),
    MODERATOR("Moderator", "Moderation Team"),
    SUPPORT("Support", "Support Team"),
    CLINICIAN("Clinician", "Authorized MoodMap Clinician"),
    CLINIC("Clinic", "Authorized MoodMap Clinic"),
    CLIENT("Client", "MoodMap Client");

    // _________________________________________________________________________________________________________________

    // Attributes
    private final String name;
    private final String description;

    // _________________________________________________________________________________________________________________

    RoleEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // _________________________________________________________________________________________________________________

    public String getName() {
        return this.name;
    }

    // _________________________________________________________________________________________________________________

    public String getDescription() {
        return this.description;
    }

}