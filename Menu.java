import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.*;
import java.util.*;
public class Menu {
    private static Organization o;
    public Menu(Organization org) {
        o = org;
        startMenu();
    }
    private void startMenu() {
        System.out.println("Insert your phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        if(phone.matches("[0-9]+") && (phone.length() >= 8)) {
            if(o.getAdmin().getPhone().equals(phone)) {
                adminMenu(o.getAdmin());
            } else {
                boolean flag = false;
                for(Donator d : o.getdonatorList()) {
                    if(d.getPhone().equals(phone)) {
                        flag = true;
                        donatorMenu(d);
                        break;
                    }
                }
                if(flag == false) {
                    for(Beneficiary b : o.getbeneficiaryList()) {
                        if(b.getPhone().equals(phone)) {
                            flag = true;
                            beneficiaryMenu(b);
                            break;
                        }
                    }
                }
                if(flag == false) {
                    registerMenu(phone);
                }
            }
        } else {
            System.out.println("You must give correct phone number format.");
            startMenu();
        }
    }
    private void adminMenu(Admin a) {
        try {
        System.out.println("############################################################");
        System.out.println("Welcome " + a.getName() + " to the " + o.getName() + "\n");
        System.out.println("1. View\n2. Monitor Organization\n3. Back\n4. Logout\n5. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option == 1) {
            adminMenu_1(a);
        } else if(option == 2) {
            adminMenu_2(a);
        } else if(option == 3) {
            System.out.println("Can't go back from this point.");
            adminMenu(a);
        } else if(option == 4) {
            startMenu();
        } else if(option == 5) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        }catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu(a);
        } catch(WrongMenuOption e) {
            adminMenu(a);
        }
    }
    private void adminMenu_1(Admin a) {
        try {
        System.out.println("1. View\n\t1. Material\n\t2. Services\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option == 1) {
            adminMenu_1_1(a);
        } else if(option == 2) {
            adminMenu_1_2(a);
        } else if(option == 3) {
            adminMenu(a);
        } else if(option == 4) {
            startMenu();
        } else if(option == 5) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_1(a);
        }
    }
    private void adminMenu_2(Admin a) {
        try {
        System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\tb. List Donators\n\tc.Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b : o.getbeneficiaryList()) {
                b.getrecievedList().reset();  
            }
            adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
                adminMenu(a);
            } else if(opt == 4) {
                startMenu();
            } else if(opt == 5) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer or one of the letters.");
            adminMenu_2(a);
        }catch(WrongMenuOption e) {
            adminMenu_2(a);
        }
    }
    private void adminMenu_1_1(Admin a) {
        try {
            HashMap<Integer, Material> map = new HashMap<Integer, Material>();
            int count = 1;
            for(Entity e : o.getentityList()) {
                if(e.getClass().equals(Material.class)) {
                    map.put(count, (Material)e);
                    System.out.print(count + ". " + e.getName());
                    ++count;
                    RequestDonation rq = o.getCurrentDonations().get(e.getId());
                    if(rq != null) {
                        System.out.println(" (" + rq.getQuantity() + ")");
                    } else {
                        System.out.println(" (0)");
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt); 
            if(option <= 0 || option >= count) {
                throw new WrongMenuOption();
            } else {
                System.out.println(map.get(option).getDetails());
                adminMenu(a);
            }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu_1_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_1_1(a);
        }
    }
    private void adminMenu_1_2(Admin a) {
        try {
            HashMap<Integer, Service> map = new HashMap<Integer, Service>();
            int count = 1;
            for(Entity e : o.getentityList()) {
                if(e.getClass().equals(Service.class)) {
                    map.put(count, (Service)e);
                    System.out.print(count + ". " + e.getName());
                    ++count;
                    RequestDonation rq = o.getCurrentDonations().get(e.getId());
                    if(rq != null) {
                        System.out.println(" (" + rq.getQuantity() + ")");
                    } else {
                        System.out.println(" (0)");
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt); 
            if(option <= 0 || option >= count) {
                throw new WrongMenuOption();
            } else {
                System.out.println(map.get(option).getDetails());
                adminMenu(a);
            }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu_1_2(a);
        } catch(WrongMenuOption e) {
            adminMenu_1_2(a);
        }
    }
    private void adminMenu_2_1_1(Admin a) {
        try {
        o.listBeneficiaries();
        if(o.getbeneficiaryList().size() == 0) {
            System.out.println("There are no beneficiaries.");
            adminMenu(a);
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option <= 0 || option > o.getbeneficiaryList().size()) {
            throw new WrongMenuOption();
        } else {
            adminMenu_2_1_2(a, o.getbeneficiaryList().get(option-1));
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu_2_1_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_2_1_1(a);
        }
    }
    private void adminMenu_2_1_2(Admin a, Beneficiary b) {
        try {
        System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\t\tRecieved Entities: ");
        System.out.print("\t\t\t");
        if(b.getrecievedList().getrdEntities().size() > 0) {
            for(RequestDonation rd : b.getrecievedList().getrdEntities()) {
                System.out.print(rd.getEntity().getName() + "(" + rd.getQuantity() + ") ");
            }
        } else {
            System.out.print("No entities received.");
        }
        System.out.println("\n\t\ti. Clear recievedList\n\t\tii. Delete Beneficiary\n\tb. List Donators\n\tc. Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
            b.getrecievedList().reset();
            System.out.println("recievedList has been cleared");
            adminMenu(a);
        } else if(option.equals("ii")) {
            o.removeBeneficiary(b);
            System.out.println("Beneficiary has been deleted");
            adminMenu(a);
        } else if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b2 : o.getbeneficiaryList()) {
                b2.getrecievedList().reset(); 
            }
            adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
                adminMenu_2_1_1(a);
            } else if(opt == 4) {
                startMenu();
            } else if(opt == 5) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer or one of the letters.");
            adminMenu_2_1_2(a, b);
        }catch(WrongMenuOption e) {
            adminMenu_2_1_2(a, b);
        }
    }
    private void adminMenu_2_2_1(Admin a) {
        try {
        o.listDonators();
        if(o.getdonatorList().size() == 0) {
            System.out.println("There are no donators.");
            adminMenu(a);
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option <= 0 || option > o.getdonatorList().size()) {
            throw new WrongMenuOption();
        } else {
            adminMenu_2_2_2(a, o.getdonatorList().get(option-1));
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            adminMenu_2_2_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_2_2_1(a);
        }
    }
    private void adminMenu_2_2_2(Admin a, Donator d) {
        try {
        System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\t\tb. List Donators\n\t\tWished offers: ");
        System.out.print("\t\t\t");
        if(d.getoffersList().getrdEntities().size() > 0) {
            for(RequestDonation rd : d.getoffersList().getrdEntities()) {
                System.out.print(rd.getEntity().getName() + "(" + rd.getQuantity() + ") ");
            }
        } else {
            System.out.print("No offers.");
        }
        System.out.println("\n\t\ti. Delete Donator\n\tc. Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
            o.removeDonator(d);
            System.out.println("Donator has been deleted");
            adminMenu(a);
        } else if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b : o.getbeneficiaryList()) {
                b.getrecievedList().reset(); 
            }
            adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
                adminMenu_2_1_1(a);
            } else if(opt == 4) {
                startMenu();
            } else if(opt == 5) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer or one of the letters.");
            adminMenu_2_2_2(a, d);
        }catch(WrongMenuOption e) {
            adminMenu_2_2_2(a, d);
        }
    }
    private void donatorMenu(Donator d) {
        try {
        System.out.println("############################################################");
        System.out.println("Welcome " + d.getName() + " to the " + o.getName() + "\n");
        System.out.println("1. Add Offer\n2. Show Offers\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            donatorMenu_1(d);
        } else if(option == 2) {
            donatorMenu_2_1(d);
        } else if(option == 3) {
            d.donatorCommit(o);
            System.out.println("Your offers have been commited.");
            donatorMenu(d);
        } else if(option == 4) {
            System.out.println("Can't go back from this point.");
            donatorMenu(d);
        } else if(option == 5) {
            startMenu();
        } else if(option == 6) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            donatorMenu(d);
        } catch(WrongMenuOption e) {
            donatorMenu(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu(d);
        } catch(NegativeQuantity nq) {
            donatorMenu(d);
        }
    }
    private void beneficiaryMenu(Beneficiary b) { 
        try {
        System.out.println("############################################################");
        System.out.println("Welcome " + b.getName() + " to the " + o.getName() + "\n");
        System.out.println("1. Add Request\n2. Show Requests\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            beneficiaryMenu_1(b);
        } else if(option == 2) {
            beneficiaryMenu_2_1(b);
        } else if(option == 3) {
            b.beneficiaryRequestsCommit(o);
            beneficiaryMenu(b);
        } else if(option == 4) {
            System.out.println("Can't go back from this point.");
            beneficiaryMenu(b);
        } else if(option == 5) {
            startMenu();
        } else if(option == 6) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            beneficiaryMenu(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu(b);
        }
    }
    private void beneficiaryMenu_1(Beneficiary b) {
        try {
        System.out.println("1. Add Request\n\t1. Material\n\t2. Services\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            beneficiaryMenu_1_1(b); 
        } else if(option == 2) {
            beneficiaryMenu_1_2(b);
        } else if(option == 3) {
            b.beneficiaryRequestsCommit(o);
            beneficiaryMenu(b);
        } else if(option == 4) {
            beneficiaryMenu(b);
        } else if(option == 5) {
            startMenu();
        } else if(option == 6) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            beneficiaryMenu_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1(b);
        }catch(NegativeQuantity nq) {
            beneficiaryMenu_1(b);
        }
    }
    private void beneficiaryMenu_1_1(Beneficiary b) { 
        try {
        HashMap<Integer, Material> map = new HashMap<Integer, Material>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Material.class)) {
                map.put(count, (Material)e);
                System.out.print(count + ". " + e.getName());
                ++count;
                RequestDonation rq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
                    System.out.println(" (" + rq.getQuantity() + ")");
                } else {
                    System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
            System.out.println("\n" + map.get(option).getEntityInfo());
            System.out.println("Do you want to request this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
                System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
                RequestDonation rd = new RequestDonation(map.get(option), quantity);
                b.beneficiaryRequestsAdd(rd, o); 
                beneficiaryMenu(b);
            } else if(option2.equals("n")) {
                beneficiaryMenu(b);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            beneficiaryMenu_1_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1_1(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_1_1(b);
        }
    }
    private void beneficiaryMenu_1_2(Beneficiary b) {
        try {
        HashMap<Integer, Service> map = new HashMap<Integer, Service>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Service.class)) {
                map.put(count, (Service)e);
                System.out.print(count + ". " + e.getName());
                ++count;
                RequestDonation rq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
                    System.out.println(" (" + rq.getQuantity() + ")");
                } else {
                    System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
            System.out.println("\n" + map.get(option).getEntityInfo());
            System.out.println("Do you want to request this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
                System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q); 
                RequestDonation rd = new RequestDonation(map.get(option), quantity);
                b.beneficiaryRequestsAdd(rd, o); 
                beneficiaryMenu(b);
            } else if(option2.equals("n")) {
                beneficiaryMenu(b);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            beneficiaryMenu_1_2(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1_2(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1_2(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1_2(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1_2(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_1_2(b);
        }
    }
    private void beneficiaryMenu_2_1(Beneficiary b) {
        try {
        System.out.println("1. Add Request\n2. Show Requests\n\ta. Select Request\n\tb. Clear Requests\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            beneficiaryMenu_2_2(b);
        } else if(option.equals("b")) {
            b.getrequestsList().reset();
            System.out.println("Your requests have been cleared.");
            beneficiaryMenu(b);
        } else if(option.equals("c")) {
            b.beneficiaryRequestsCommit(o);
            beneficiaryMenu(b);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                beneficiaryMenu_1(b);
            } else if(opt == 2) {
                beneficiaryMenu_2_1(b);
            } else if(opt == 3) {
                b.getrequestsList().commit(b, o);
                beneficiaryMenu(b);
            } else if(opt == 4) {
                beneficiaryMenu(b);
            } else if(opt == 5) {
                startMenu();
            } else if(opt == 6) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer or one of the letters.");
            beneficiaryMenu_2_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_2_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_2_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_2_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_1(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_2_1(b);
        }
    }
    private void beneficiaryMenu_2_2(Beneficiary b) {
        try {
        HashMap<Integer, RequestDonation> map = new HashMap<Integer, RequestDonation>();
        if(b.getrequestsList().getrdEntities().isEmpty()) {
            System.out.println("There are no requests at the moment.");
            beneficiaryMenu(b);
        } else {
            int count = 0;
            for(RequestDonation rd : b.getrequestsList().getrdEntities()) {
                ++count;
                map.put(count, rd);
                System.out.println(count + ". " + rd.getEntity().getName() + "(" + rd.getQuantity() + ")");
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            if(option <= 0 || option > count) {
                throw new WrongMenuOption();
            } else {
                beneficiaryMenu_2_3(b, map.get(option));
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            beneficiaryMenu_2_2(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_2(b);
        }
    }
    private void beneficiaryMenu_2_3(Beneficiary b, RequestDonation rd) {
        try {
        System.out.println("1. Add Request\n2. Show Requests\n\ta.\n\t" + rd.getEntity().getName() + 
        "(" + rd.getQuantity() +")\n\t\ti. Delete this request\n\t\tii. Modify this request\n\tb. Clear Requests\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
            b.beneficiaryRemove(rd);
            System.out.println("Request removed");
            beneficiaryMenu(b);
        } else if(option.equals("ii")) {
            System.out.println("Enter new quantity");
            String op = scanner.nextLine();
            double opt = Double.parseDouble(op);
            RequestDonation rd2 = new RequestDonation(rd.getEntity(), opt);
            b.beneficiaryRequestsModify(rd2, o);
            beneficiaryMenu(b);
        } else if(option.equals("a")) {
            beneficiaryMenu_2_2(b);
        } else if(option.equals("b")) {
            b.getrequestsList().reset();
            System.out.println("Your requests have been cleared.");
            beneficiaryMenu(b);
        } else if(option.equals("c")) {
            b.beneficiaryRequestsCommit(o);
            beneficiaryMenu(b);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                beneficiaryMenu_1(b);
            } else if(opt == 2) {
                beneficiaryMenu_2_1(b);
            } else if(opt == 3) {
                b.beneficiaryRequestsCommit(o);
                beneficiaryMenu(b);
            } else if(opt == 4) {
                beneficiaryMenu(b);
            } else if(opt == 5) {
                startMenu();
            } else if(opt == 6) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give a double.");
            beneficiaryMenu_2_3(b, rd);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_3(b, rd);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_2_3(b, rd);
        }
    }
    private void registerMenu(String phone) {
        try {
        System.out.println("Do you wish to be a donator or a beneficiary?");
        System.out.println("\t1. Donator\n\t2. Beneficiary");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            System.out.println("Please enter your name: ");
            String name = scanner.nextLine();
            Donator d = new Donator(name, phone);
            o.insertDonator(d);
            donatorMenu(d);
        } else if(option == 2) {
            System.out.println("Please enter your name: ");
            String name = scanner.nextLine();
            Beneficiary b = new Beneficiary(name, phone);
            o.insertBeneficiary(b);
            beneficiaryMenu(b);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            registerMenu(phone);
        } catch(WrongMenuOption e) {
            registerMenu(phone);
        } catch(UserAlreadyExists u) {
            startMenu();
        }
    }
    private void donatorMenu_1(Donator d) {
        try {
        System.out.println("1. Add Offer\n\t1. Material\n\t2. Services\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            donatorMenu_1_1(d); 
        } else if(option == 2) {
            donatorMenu_1_2(d);
        } else if(option == 3) {
            d.donatorCommit(o);
            System.out.println("Your offers have been commited.");
            donatorMenu(d);
        } else if(option == 4) {
            donatorMenu(d);
        } else if(option == 5) {
            startMenu();
        } else if(option == 6) {
            System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            donatorMenu_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_1(d);
        }
    }
    private void donatorMenu_2_1(Donator d) {
        try {
        System.out.println("1. Add Offer\n2. Show Offers\n\ta. Select Offer\n\tb. Clear Offers\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            donatorMenu_2_2(d);
        } else if(option.equals("b")) {
            d.getoffersList().reset();
            System.out.println("Your offers have been cleared.");
            donatorMenu(d);
        } else if(option.equals("c")) {
            d.donatorCommit(o);
            donatorMenu(d);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                donatorMenu_1(d);
            } else if(opt == 2) {
                donatorMenu_2_1(d);
            } else if(opt == 3) {
                d.donatorCommit(o);
                System.out.println("Your offers have been commited.");
                donatorMenu(d);
            } else if(opt == 4) {
                donatorMenu(d);
            } else if(opt == 5) {
                startMenu();
            } else if(opt == 6) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer or one of the letters.");
            donatorMenu_2_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_2_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_2_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_2_1(d);
        }
    }
    private void donatorMenu_2_2(Donator d) {
        try {
        HashMap<Integer, RequestDonation> map = new HashMap<Integer, RequestDonation>();
        if(d.getoffersList().getrdEntities().isEmpty()) {
            System.out.println("There are no offers at the moment.");
            donatorMenu(d);
        } else {
            int count = 0;
            for(RequestDonation rd : d.getoffersList().getrdEntities()) {
                ++count;
                map.put(count, rd);
                System.out.println(count + ". " + rd.getEntity().getName() + "(" + rd.getQuantity() + ")");
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            if(option <= 0 || option > count) {
                throw new WrongMenuOption();
            } else {
                donatorMenu_2_3(d, map.get(option));
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            donatorMenu_2_2(d);
        } catch(WrongMenuOption e) {
            donatorMenu_2_2(d);
        }
    }
    private void donatorMenu_2_3(Donator d, RequestDonation rd) {
        try {
        System.out.println("1. Add Offer\n2. Show Offers\n\ta.\n\t" + rd.getEntity().getName() + 
        "(" + rd.getQuantity() +")\n\t\ti. Delete this offer\n\t\tii. Modify this offer\n\tb. Clear Offers\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
            d.getoffersList().remove(rd);
            System.out.println("Offer removed");
            donatorMenu(d);
        } else if(option.equals("ii")) {
            System.out.println("Enter new quantity");
            String q = scanner.nextLine();
            double opt = Double.parseDouble(q); 
            rd.setQuantity(opt); 
            d.getoffersList().modify(rd);
            System.out.println("Quantity modified");
            donatorMenu(d);
        } else if(option.equals("a")) {
            donatorMenu_2_2(d);
        } else if(option.equals("b")) {
            d.getoffersList().reset();
            System.out.println("Your offers have been cleared.");
            donatorMenu_1(d);
        } else if(option.equals("c")) {
            d.donatorCommit(o);
            donatorMenu(d);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                donatorMenu_1(d);
            } else if(opt == 2) {
                donatorMenu_2_1(d);
            } else if(opt == 3) {
                d.donatorCommit(o);
                System.out.println("Your offers have been commited.");
                donatorMenu(d);
            } else if(opt == 4) {
                donatorMenu(d);
            } else if(opt == 5) {
                startMenu();
            } else if(opt == 6) {
                System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give a double.");
            donatorMenu_2_3(d, rd);
        } catch(WrongMenuOption e) {
            donatorMenu_2_3(d, rd);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_2_3(d, rd);
        } catch(NegativeQuantity nq) {
            donatorMenu_2_3(d, rd);
        }
    }
    private void donatorMenu_1_1(Donator d) {
        try {
        HashMap<Integer, Material> map = new HashMap<Integer, Material>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Material.class)) {
                map.put(count, (Material)e);
                System.out.print(count + ". " + e.getName());
                ++count;
                RequestDonation rq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
                    System.out.println(" (" + rq.getQuantity() + ")");
                } else {
                    System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
            System.out.println("\n" + map.get(option).getEntityInfo());
            System.out.println("Do you want to offer this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
                System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
                RequestDonation rd = new RequestDonation(map.get(option), quantity);
                d.donatorAdd(rd, o); 
                donatorMenu(d);
            } else if(option2.equals("n")) {
                donatorMenu(d);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give a double.");
            donatorMenu_1_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_1_1(d);
        }
    }
    private void donatorMenu_1_2(Donator d) {
        try {
        HashMap<Integer, Service> map = new HashMap<Integer, Service>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Service.class)) {
                map.put(count, (Service)e);
                System.out.print(count + ". " + e.getName());
                ++count;
                RequestDonation rq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
                    System.out.println(" (" + rq.getQuantity() + ")");
                } else {
                    System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
            System.out.println("\n" + map.get(option).getEntityInfo());
            System.out.println("Do you want to offer this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
                System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
                RequestDonation rd = new RequestDonation(map.get(option), quantity);
                d.donatorAdd(rd, o); 
                donatorMenu(d);
            } else if(option2.equals("n")) {
                donatorMenu(d);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatException nfe) {
            System.out.println("You must give an integer.");
            donatorMenu_1_2(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1_2(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1_2(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_1_2(d);
        }
    }
}
