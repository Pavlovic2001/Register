//Alexander alpa7946
public class Owner {
    private String name;

    private DogList dogs = new DogList();

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString()
    {
        return name + " added to the register";
    }

    public void assignDogToOwner(Dog dog)
    {
        //kolla om hunden har en ägare
        if(dog.getOwner() != null && dog.getOwner() != this)
        {
            return;
        }
        dogs.addDogToList(dog); // annars lägg till hund i array
        dog.setOwner(this);

    }

    public String getOwnedDogsNames()
    {
        return this.dogs.fetchDogsName();
    }

    public boolean doesOwnerOwnDog(Dog d){
        if(this.dogs.isDogInList(d)){
            return true;
        }
        return false;
    }

    public void removeDog(Dog d){
        if(!this.doesOwnerOwnDog(d)){
            return;
        }

        this.dogs.removeDogFromList(d);
        d.removeOwner();
    }

}