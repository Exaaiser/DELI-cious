package com.pluralsight.builder;

import com.pluralsight.Sandwich;
import com.pluralsight.SideItem;
import com.pluralsight.toppings.CheeseTopping;
import com.pluralsight.toppings.MeatTopping;
import com.pluralsight.toppings.RegularTopping;
import com.pluralsight.toppings.SauceTopping;

import java.util.Scanner;

public class SandwichBuilder {
    private static final Scanner scanner = new Scanner(System.in);

    public static Sandwich build(Scanner scanner) {
        System.out.println("\n--- Build a Sandwich ---"); // Sandviç Oluştur

        // 1. Ekmek Tipi Seçimi
        String breadType = selectBread();

        // 2. Sandviç Boyutu Seçimi
        int size = selectSize();

        // Sandwich nesnesini temel özelliklerle oluşturuyoruz
        Sandwich sandwich = new Sandwich(size, breadType, false); // Tost özelliği şimdilik false

        // 3. Topping Ekleme
        addToppings(sandwich);

        // 4. Sos Ekleme
        addSauces(sandwich);

        // 5. Yan Ürün Ekleme (Au Jus, Sauce)
        addSides(sandwich);

        // 6. Tost Seçeneği
        boolean isToasted = askToToast();
        sandwich = new Sandwich(size, breadType, isToasted); // Tost bilgisini yansıtacak yeni sandviç nesnesi

        System.out.println("Sandwich added!"); // Sandviç eklendi!
        return sandwich;
    }

    private static String selectBread() {
        String breadType;
        while (true) {
            System.out.println("Select your bread type:"); // Ekmek tipini seçin:
            System.out.println("  1) White"); // Beyaz
            System.out.println("  2) Wheat"); // Buğday
            System.out.println("  3) Rye"); // Çavdar
            System.out.println("  4) Wrap"); // Wrap
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": breadType = "White"; break;
                case "2": breadType = "Wheat"; break;
                case "3": breadType = "Rye"; break;
                case "4": breadType = "Wrap"; break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-4."); // Geçersiz seçim. Lütfen 1-4 arasında bir sayı girin.
                    continue;
            }
            break;
        }
        return breadType;
    }

    private static int selectSize() {
        int size;
        while (true) {
            System.out.println("Select sandwich size:"); // Sandviç boyutunu seçin:
            System.out.println("  1) 4\""); // 4"
            System.out.println("  2) 8\""); // 8"
            System.out.println("  3) 12\""); // 12"
            System.out.print("Your choice: "); // Seçiminiz:
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim()); //Converting String choice to Int choice
                switch (choice) {
                    case 1: size = 4; break;
                    case 2: size = 8; break;
                    case 3: size = 12; break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3."); // Geçersiz seçim. Lütfen 1, 2 veya 3 girin.
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number."); // Geçersiz giriş. Lütfen bir sayı girin.
            }
        }
        return size;
    }

    private static void addToppings(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Add Topping ---"); // Topping Ekle
            System.out.println("1) Meat"); // Et
            System.out.println("2) Cheese"); // Peynir
            System.out.println("3) Regular Topping (Lettuce, Peppers etc.)"); // Normal Topping (Marul, Biber vb.)
            System.out.println("0) Finish adding toppings"); // Topping eklemeyi bitir
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addMeat(sandwich);
                    break;
                case "2":
                    addCheese(sandwich);
                    break;
                case "3":
                    addRegularTopping(sandwich);
                    break;
                case "0":
                    return; // Topping eklemeyi bitir
                default:
                    System.out.println("Invalid choice, please try again."); // Geçersiz seçim, tekrar deneyin.
            }
        }
    }

    private static void addMeat(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Select Meat ---"); // Et Seçin
            System.out.println("1) Steak"); // Steak
            System.out.println("2) Ham"); // Ham
            System.out.println("3) Salami"); // Salami
            System.out.println("4) Roast Beef"); // Roast Beef
            System.out.println("5) Chicken"); // Chicken
            System.out.println("6) Bacon"); // Bacon
            System.out.println("0) Back"); // Geri
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            String meatName = "";
            switch (choice) {
                case "1": meatName = "Steak"; break;
                case "2": meatName = "Ham"; break;
                case "3": meatName = "Salami"; break;
                case "4": meatName = "Roast Beef"; break;
                case "5": meatName = "Chicken"; break;
                case "6": meatName = "Bacon"; break;
                case "0": return;
                default:
                    System.out.println("Invalid choice."); // Geçersiz seçim.
                    continue;
            }

            boolean isExtra = askForExtra("extra " + meatName); // "ekstra et adı" ister misiniz?
            sandwich.addTopping(new MeatTopping(meatName, 0.0, isExtra));
            System.out.println(meatName + (isExtra ? " (Extra)" : "") + " added."); // Et eklendi.
            break;
        }
    }

    private static void addCheese(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Select Cheese ---"); // Peynir Seçin
            System.out.println("1) American"); // American
            System.out.println("2) Provolone"); // Provolone
            System.out.println("3) Cheddar"); // Cheddar
            System.out.println("4) Swiss"); // Swiss
            System.out.println("0) Back"); // Geri
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            String cheeseName = "";
            switch (choice) {
                case "1": cheeseName = "American"; break;
                case "2": cheeseName = "Provolone"; break;
                case "3": cheeseName = "Cheddar"; break;
                case "4": cheeseName = "Swiss"; break;
                case "0": return;
                default:
                    System.out.println("Invalid choice."); // Geçersiz seçim.
                    continue;
            }

            boolean isExtra = askForExtra("extra " + cheeseName); //  Do you want extra cheese?
            sandwich.addTopping(new CheeseTopping(cheeseName, 0.0, isExtra));
            System.out.println(cheeseName + (isExtra ? " (Extra)" : "") + " added."); // Cheese added
            break;
        }
    }

    private static void addRegularTopping(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Select Regular Topping ---"); // Normal Topping Seçin
            System.out.println("1) Lettuce"); // Marul
            System.out.println("2) Peppers"); // Biber
            System.out.println("3) Onions"); // Soğan
            System.out.println("4) Tomatoes"); // Domates
            System.out.println("5) Jalapeños"); // Jalapeños
            System.out.println("6) Cucumbers"); // Salatalık
            System.out.println("7) Pickles"); // Turşu
            System.out.println("8) Guacamole"); // Guacamole
            System.out.println("9) Mushrooms"); // Mantar
            System.out.println("0) Back"); // Geri
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            String toppingName = "";
            switch (choice) {
                case "1": toppingName = "Lettuce"; break;
                case "2": toppingName = "Peppers"; break;
                case "3": toppingName = "Onions"; break;
                case "4": toppingName = "Tomatoes"; break;
                case "5": toppingName = "Jalapeños"; break;
                case "6": toppingName = "Cucumbers"; break;
                case "7": toppingName = "Pickles"; break;
                case "8": toppingName = "Guacamole"; break;
                case "9": toppingName = "Mushrooms"; break;
                case "0": return;
                default:
                    System.out.println("Invalid choice."); // Geçersiz seçim.
                    continue;
            }
            sandwich.addTopping(new RegularTopping(toppingName));
            System.out.println(toppingName + " added."); // Topping eklendi.
            break;
        }
    }

    private static void addSauces(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Select Sauce ---"); // Sos Seçin
            System.out.println("1) Mayo"); // Mayo
            System.out.println("2) Mustard"); // Hardal
            System.out.println("3) Ketchup"); // Ketçap
            System.out.println("4) Ranch"); // Ranch
            System.out.println("5) Thousand Islands"); // Thousand Islands
            System.out.println("6) Vinaigrette"); // Vinaigrette
            System.out.println("0) Finish adding sauces"); // Sos eklemeyi bitir
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            String sauceName = "";
            switch (choice) {
                case "1": sauceName = "Mayo"; break;
                case "2": sauceName = "Mustard"; break;
                case "3": sauceName = "Ketchup"; break;
                case "4": sauceName = "Ranch"; break;
                case "5": sauceName = "Thousand Islands"; break;
                case "6": sauceName = "Vinaigrette"; break;
                case "0": return;
                default:
                    System.out.println("Invalid choice."); // Geçersiz seçim.
                    continue;
            }
            sandwich.addSauce(new SauceTopping(sauceName));
            System.out.println(sauceName + " added."); // Sos eklendi.
        }
    }

    private static void addSides(Sandwich sandwich) {
        while (true) {
            System.out.println("\n--- Select Side Item ---"); // Yan Ürün Seçin
            System.out.println("1) Au Jus"); // Au Jus
            System.out.println("2) Sauce"); // Sauce
            System.out.println("0) Finish adding side items"); // Yan ürün eklemeyi bitir
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();
            String sideName = "";
            switch (choice) {
                case "1": sideName = "Au Jus"; break;
                case "2": sideName = "Sauce"; break;
                case "0": return;
                default:
                    System.out.println("Invalid choice."); // Geçersiz seçim.
                    continue;
            }
            sandwich.addSide(new SideItem(sideName));
            System.out.println(sideName + " added."); // Yan ürün eklendi.
        }
    }

    private static boolean askForExtra(String itemType) {

        // The trading paarttt askk foor exttraaa produuctt buyinngg

        while (true) {
            System.out.print("Would you like " + itemType + "? (yes/no): "); // Bu öğeden [itemType] ister misiniz? (evet/hayır):
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                return true;
            } else if (choice.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static boolean askToToast() {
        while (true) {
            System.out.print("Would you like the sandwich toasted? (yes/no): "); // Sandviçi tostlamak ister misiniz? (evet/hayır):
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                return true;
            } else if (choice.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'."); // Geçersiz giriş. Lütfen 'yes' veya 'no' girin.
            }
        }
    }
}