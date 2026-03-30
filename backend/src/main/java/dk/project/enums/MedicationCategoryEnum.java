package dk.project.enums;

public enum MedicationCategoryEnum {

    // Enums
    PRESCRIPTION("Prescription Medication", "This medication requires a valid prescription from your doctor", 2),
    NO_PRESCRIPTION("No prescription needed", "No prescription needed for this drug", 0),
    PENDING_FDA("Pending FDA approval","This drug is currently pending FDA approval",3),
    FDA_APPROVED("FDA approved", "This drug has been FDA approved", 0),
    TRIAL("Drug in trial", "This drug is currently being tested in trial. Use with causion", 4),
    GOVERNMENT_CONTROLLED("Government controlled drug", "This drug is being monitored by the government", 2),
    VACCINE("Vaccine", "This drug is being used as a vaccine", 1),
    UNKNOWN("Unknown category", "We don't know anything about this drug.", 5),
    GENERIC("Generic Drug", "This drug is a generic version of the original. Keep track of side effects.", 2),
    ORIGINAL_PRODUCT("Original Product", "This drug is the original version of the drug", 0),
    BIOLOGIC_PRODUCT("Biological Product", "This drug has a higher risk of immune reactions compared to normal drugs", 2),
    HIGH_ALERT("High alert", "This drug has been flagged as high alert. Use with causion and with doctor supervision", 5),
    DIETARY_SUPPLEMENT("Dietarty Supplement", "Minerals, Vitamins, weight gainer etc.", 1),
    RECALLED("Recalled", "This product has been recalled. Do not use unless your doctor tells you to", 5),
    DISCONTINUED("Discontinued", "This products has been discontinued. Getting hands on it will become tricky. Ask your doctor for alternatives", 3);

    // _________________________________________________________________________________________________________________

    private final String name;
    private final String description;
    private final int warningLevel;

    // _________________________________________________________________________________________________________________

    MedicationCategoryEnum(String name, String description, int warningLevel) {
        this.name = name;
        this.description = description;
        this.warningLevel = warningLevel;
    }

    // _________________________________________________________________________________________________________________

    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWarningLevel() {
        return this.warningLevel;
    }

    // _________________________________________________________________________________________________________________

}