package dk.project.util;

import dk.project.entity.Client;
import dk.project.entity.MedicationCategory;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.MedicationCategoryEnum;
import dk.project.enums.RoleEnum;
import dk.project.service.internal.ClientService;
import dk.project.service.internal.MedicationCategoryService;
import dk.project.service.internal.RoleService;
import dk.project.service.internal.UserService;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PopulateDB {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static void populate(EntityManager em){

        // Initial
        RoleService roleService = new RoleService(em);
        UserService userService = new UserService(em);
        ClientService clientService = new ClientService(em);
        MedicationCategoryService medicationCategoryService = new MedicationCategoryService(em);

        // Clean DB
        roleService.deleteAllSafe();
        userService.deleteAllSafe();
        clientService.deleteAllSafe();
        medicationCategoryService.deleteAllSafe();

        // Roles
        List<Role> roleList = new ArrayList<>();
        for (RoleEnum roleEnum : RoleEnum.values()) {
            Role role = Role.builder()
                    .name(roleEnum.getName())
                    .description(roleEnum.getDescription())
                    .build();
            roleService.create(role);
            roleList.add(role);
        }

        // Users
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            User user = User.builder()
                    .username("bruger" + (i + 1))
                    .email("mail" + (i + 1) + "@mail.dk")
                    .password("password" + (i + 1))
                    .role(role)
                    .build();
            userService.createUser(user);
        }

        // Client
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            int last4Random = 1000 + (int)(Math.random() * 9000);
            int first6Random = 100000 + (int)(Math.random() * 900000);
            String randomCPR = first6Random + "-" + last4Random;
            Client client = Client.builder()
                    .id(randomCPR)
                    .idEnding(last4Random)
                    .build();
            clientService.createClient(client);
        }

        // MedicationCategory
        List<MedicationCategory> medicationCategoryList = new ArrayList<>();
        for (MedicationCategoryEnum medicationCategoryEnum : MedicationCategoryEnum.values()) {
            MedicationCategory medicationCategory = MedicationCategory.builder()
                    .name(medicationCategoryEnum.getName())
                    .description(medicationCategoryEnum.getDescription())
                    .warningLevel(medicationCategoryEnum.getWarningLevel())
                    .build();
            medicationCategoryService.create(medicationCategory);
            medicationCategoryList.add(medicationCategory);
        }

        // Final Confirmation
        System.out.println("\nDatabase populated");

    }

}