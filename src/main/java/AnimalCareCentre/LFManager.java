/**
package AnimalCareCentre;
import AnimalCareCentre.enums.AnimalColor;
import AnimalCareCentre.enums.AnimalSize;
import AnimalCareCentre.enums.AnimalType;
import AnimalCareCentre.models.LostAnimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Scanner;

public class LFManager {

    static SessionFactory sessionFactory;

    public static void showMenu(){


        Scanner sc = new Scanner(System.in);
        int choice= 0;

        System.out.println("\n=== LOST AND FOUND ===");
        System.out.println("1. Register lost animal");
        System.out.println("2. Register found animal");
        System.out.println("3. Show lost animal");
        System.out.println("4. Show found animals");
        try {
            choice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input ");
            showMenu();
        }

        switch (choice){

            case 1 : registerLostAnimal();
            case 2 : registerFoundAnimal();
            case 3 : showLostAnimals();
            case 4 : showFoundAnimals();

        }
    }

    private static void showLostAnimals() {
        Session session = sessionFactory.openSession();
        Query<LostAnimal> query = session.createQuery("FROM LostAnimals",LostAnimal.class);
        query.setParameter("true",true);
        List<LostAnimal> lostAnimals = query.getResultList();
        for (LostAnimal animal : lostAnimals){
            System.out.println(animal);
        }
    }

    private static void registerFoundAnimal() {
        Scanner in = new Scanner(System.in);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String animalName = "";


        System.out.println("Insert animal name");
        animalName = in.nextLine();
        System.out.print("Type (DOG, CAT or RABBIT): ");
        String typeInput = in.nextLine();
        AnimalType type = AnimalType.fromString(typeInput);
        if (type == null) {
            System.out.println("Invalid type");
            return;
        }

        System.out.print("Available races for : " + type + ": ");
        for (String race : type.getBreeds()) {
            System.out.println(race);
        }

        System.out.print("Race: ");
        String race = in.nextLine();

        if (!type.getBreeds().contains(race)) {
            System.out.println("Invalid race");
        }

        System.out.print("Size (SMALL, MEDIUM, LARGE): ");
        AnimalSize size = AnimalSize.valueOf(in.nextLine().toUpperCase());

        System.out.print("Color: ");
        AnimalColor color = AnimalColor.valueOf(in.nextLine().toUpperCase());

        System.out.print("Description: ");
        String description = in.nextLine();

        System.out.print("Last seen location: ");
        String location= in.nextLine();

        LostAnimal newAnimal = new LostAnimal(animalName,type,race,color,size,description,location);
        newAnimal.setLost(false);
        session.persist(newAnimal);
    }

    private static void registerLostAnimal() {
            Scanner in = new Scanner(System.in);

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String animalName = "";


            System.out.println("Insert animal name");
            animalName = in.nextLine();
            System.out.print("Type (DOG, CAT or RABBIT): ");
            String typeInput = in.nextLine();
            AnimalType type = AnimalType.fromString(typeInput);
            if (type == null) {
                System.out.println("Invalid type");
                return;
            }

            System.out.print("Available races for : " + type + ": ");
            for (String race : type.getBreeds()) {
                System.out.println(race);
            }

            System.out.print("Race: ");
            String race = in.nextLine();

            if (!type.getBreeds().contains(race)) {
                System.out.println("Invalid race");
            }

            System.out.print("Size (SMALL, MEDIUM, LARGE): ");
            AnimalSize size = AnimalSize.valueOf(in.nextLine().toUpperCase());

            System.out.print("Color: ");
            AnimalColor color = AnimalColor.valueOf(in.nextLine().toUpperCase());

            System.out.print("Description: ");
            String description = in.nextLine();

            System.out.print("Last seen location: ");
            String location= in.nextLine();

            LostAnimal newAnimal = new LostAnimal(animalName,type,race,color,size,description,location);
            newAnimal.setLost(true);
            session.persist(newAnimal);
            session.close();
        }



    private static void showFoundAnimals() {
        Session session = sessionFactory.openSession();
        Query<LostAnimal> query = session.createQuery("FROM LostAnimals WHERE isLost = :false",LostAnimal.class);
        query.setParameter("false",false);
        List<LostAnimal> lostAnimals = query.getResultList();
        for (LostAnimal animal : lostAnimals){
            System.out.println(animal);
        }

    }
}
*/