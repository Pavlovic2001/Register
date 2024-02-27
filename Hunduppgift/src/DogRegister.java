//Alexander alpa7946

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DogRegister {

    private  InputHandler input = new InputHandler();

    private  ArrayList <Owner> ownerList = new ArrayList<>();
    private  ArrayList <Dog> dogList = new ArrayList<>();


    public void run() {

        System.out.println("Welcome!");
        allCommandos();
        processCommands();
    }

    private  void processCommands() {
        while(true) {
            System.out.println();
            String command = input.readLine("Command");
            switch (command) {
                case "register new dog":
                case "rnd":
                    registerNewDog();
                    break;
                case "list dogs":
                case "ld":
                    sortDogs();
                    showDogList();
                    break;
                case "increase age":
                case "ia":
                    increaseDogAge();
                    break;
                case "remove dog":
                case "rd":
                    removeDog();
                    break;
                case "register new owner":
                case "rno":
                    registerNewOwner();
                    break;
                case "give dog":
                case "gd":
                    giveDog();
                    break;
                case "list owners":
                case "lo":
                    showOwnerList();
                    break;
                case "remove owned dog":
                case "rod":
                    removeOwnedDog();
                    break;
                case "remove owner":
                case "ro":
                    removeOwner();
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    input.close();
                    return;
                default:
                    allCommandos();
                    break;
            }
        }
    }

    // info about commands method

    private  void allCommandos()
    {
        System.out.println();
        System.out.println("Följande kommandon finns:");
        System.out.println("* register new dog");
        System.out.println("* list dogs");
        System.out.println("* increase age");
        System.out.println("* remove dog");
        System.out.println("* register new owner");
        System.out.println("* give dog");
        System.out.println("* list owners");
        System.out.println("* remove owned dog");
        System.out.println("* remove owner");
        System.out.println("* exit");
    }

    // register new dog

    private  void registerNewDog()
    {

        String name = input.readLine("Name");
        String breed = input.readLine("Breed");

        int age = input.readInt("Age");
        int weight = input.readInt("Weight");

        Dog newDog = new Dog(name.trim(), breed.trim(), age, weight);
        dogList.add(newDog);

        System.out.println(newDog.getName() + " added to the register");


    }

    // sorting

    private  void swapDogsWithCollection(int indexOne, int indexTwo) {
        if (indexOne < 0 || indexOne >= dogList.size() || indexTwo < 0 || indexTwo >= dogList.size()) {
            return;
        }
        Collections.swap(dogList, indexOne, indexTwo);
    }

    private int findSmallestDog(int index) {
        if (index < 0 || index >= dogList.size()) {
            return -1;
        }

        Dog smallestDog = dogList.get(index);
        int smallestDogIndex = index;

        for (int i = index + 1; i < dogList.size(); i++) {

            Dog currentDog = dogList.get(i);

            if (currentDog.getTailLength() < smallestDog.getTailLength() ||
                    currentDog.getTailLength() == smallestDog.getTailLength() && currentDog.getName().compareTo(smallestDog.getName()) < 0) {

                smallestDog = currentDog;
                smallestDogIndex = i;
            }
        }
        return smallestDogIndex;
    }

    private int sortDogs() {
        if (dogList.isEmpty()) {
            return 0;
        }

        int numSwaps = 0;

        for (int i = 0; i < dogList.size(); i++) {
            int smallestDogIndex = findSmallestDog(i);
            if (smallestDogIndex != i) {
                swapDogsWithCollection(i, smallestDogIndex);
                numSwaps++;
            }
        }

        return numSwaps;
    }

    // list dogs

    private  void showDogList()
    {

        ArrayList <Dog> newDogList = new ArrayList<>();

        if(dogList == null || dogList.isEmpty())
        {
            System.out.println("Error: no dogs in register");
            return;
        }

        double choseTailLenght = input.readDouble("Smallest tail length to display");


        for(int i = 0; i < dogList.size(); i++)
        {
            if(dogList.get(i).getTailLength() >= choseTailLenght)
            {

                newDogList.add(dogList.get(i)); //hämtar hundarna från gamla listan och lägger till dem i nya om dem har rätt tailing
            }
        }

        if(newDogList == null || newDogList.isEmpty())
        {
            System.out.println("Error: no dog with such large tails");
        }

        else{

            System.out.println("The following dogs has such a large tail:");

            for(Dog dog : newDogList)
            {
                if(dog.getOwner() == null)
                {
                    System.out.println(dog.toString() + "no owner");
                }

                else{
                    System.out.println(dog.toString() + "owned by " + dog.getOwner().getName());
                }
            }

        }

    }

    // increase age

    private  void increaseDogAge()
    {
        String dogName = input.readLine("Enter the name of the dog");

        Dog dog = findDog(dogName);

        if(dogList.contains(dog))
        {
            dog.addAge();
            System.out.println(dogName + " is now one yer older");
        }

        else
        {
            System.out.println("Error: no such dog");
        }


    }

    // remove dog

    private  void removeDog()
    {
        String dogName = input.readLine("Enter the name of the dog");

        Dog dog = findDog(dogName);

        if(dogList.contains(dog))
        {
            Owner owner = dog.getOwner();
            if (owner != null) {
                owner.removeDog(dog);  // remove the dog from the owner's list of dogs
            }
            dogList.remove(dog);
            System.out.println(dogName + " is removed from the register");
        }

        else
        {
            System.out.println("Error: no such dog");
        }
    }

    // register new owner

    private  void registerNewOwner() {

        String name = input.readLine("Name");

        while(name.trim().isEmpty() || name.isBlank()) //sålänge name är tomt be användaren skriva in name igen
        {
            System.out.println("Error: the name can\'t be empty");
            name = input.readLine("Name"); // be användare skriva in name igen
        }

        Owner newOwner = new Owner(name.trim());
        ownerList.add(newOwner);

        System.out.println(newOwner.getName() + " added to register");
    }

    // give dog

    private  void giveDog()
    {

        Dog dog = getDog();

        if(dog == null)
        {
            System.out.println("Error: no dog with that name");
            return;
        }

        if(dog.getOwner() != null)
        {
            System.out.println("Error: the dog already has an owner");
            return;
        }

        Owner owner = getOwner();

        if(owner == null)
        {
            System.out.println("Error: no such owner");
            return;
        }

        owner.assignDogToOwner(dog);

        System.out.println(dog.getName()  +  " has been given to " + owner.getName());

    }

    // list owners
    private  void showOwnerList() {
        if(ownerList.isEmpty()) {
            System.out.println("Error: no owners in register");
            return;
        }

        for(Owner owner : ownerList) {
            String dogString = owner.getOwnedDogsNames();
            if(dogString != null && !dogString.isEmpty()) {
                System.out.println(owner.getName() + " [" + dogString + "]");
            } else {
                System.out.println(owner.getName() + " []");
            }
        }
    }


    // remove owned dog

    private  void removeOwnedDog()
    {
        String dogName = input.readLine("Enter the name of the dog");

        Dog dog = findDog(dogName);

        if(dogList.contains(dog))
        {
            Owner owner = dog.getOwner();
            if (owner != null) {
                owner.removeDog(dog);  // remove the dog from the owner's list of dogs
                System.out.println(dogName + " is removed from " + owner.getName());
            }

            if(owner == null)
            {
                System.out.println("Error: " + dogName + " is not owned by anyone");
                return;
            }

        }

        else
        {
            System.out.println("Error: no such dog");
        }
    }

    // remove owner

    private  void removeOwner()
    {
        Owner owner = getOwner();

        if(owner == null)
        {
            System.out.println("Error: no such owner");
            return;
        }

        Iterator<Dog> iterator = dogList.iterator();

        while (iterator.hasNext()) {
            Dog dog = iterator.next();
            if (dog.getOwner() == owner) {
                iterator.remove();
            }
        }

        ownerList.remove(owner);

        System.out.println(owner.getName() +  " is removed from the register");
    }

    // help methods

    // dog finder
    private  Dog findDog(String name)
    {
        for (Dog dog : dogList){
            if (dog.getName().equalsIgnoreCase(name)){
                return dog;
            }
        }
        return null;

    }

    // dog getter
    private  Dog getDog()
    {
        System.out.print("Enter the name of the dog");
        String dogname = input.readLine("");

        Dog dog = findDog(dogname);

        return dog;
    }

    // owner finder
    private  Owner findOwner(String name)
    {
        for (Owner owner : ownerList){
            if (owner.getName().equalsIgnoreCase(name)){
                return owner;
            }
        }
        return null;
    }

    // owner getter
    private  Owner getOwner() {

        System.out.print("Enter the name of the owner");
        String ownerName = input.readLine("");

        Owner owner = findOwner(ownerName);

        return owner;
    }



}