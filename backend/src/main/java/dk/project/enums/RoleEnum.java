package dk.project.enums;

public enum RoleEnum {

    // Enums
    ADMIN("Admin", "System administrator"),
    MODERATOR("Moderator", "Moderation Team"),
    SUPPORT("Support", "Support Team"),
    CLINICIAN("Clinician", "Authorized MoodMap Clinician"),
    CLIENT("Client", "MoodMap Client");

    // ___________________________________________________________

    // Attributes
    private final String name;
    private final String description;

    // ___________________________________________________________

    RoleEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ___________________________________________________________

    public String getName() {
        return this.name;
    }

    // ___________________________________________________________

    public String getDescription() {
        return this.description;
    }

}