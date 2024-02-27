//Alexander alpa7946

public class DogList {
    private Dog[] dogs;
    private int size;

    public DogList() {
        dogs = new Dog[size];
    }

    public void addDogToList(Dog d) {
        if (d == null) {
            return; //return om d = null
        }

        String name = d.getName();

        //checka om dog med samma namn finns i listan
        for (int i = 0; i < size; i++) {
            if (dogs[i].getName().equals(name)) {
                return; //return om dog har samma namn i listan
            }
        }

        //skapa ny array med +1 storlek
        Dog[] newDogs = new Dog[size + 1];

        //kopiera elements från gamla array till nya
        System.arraycopy(dogs, 0, newDogs, 0, dogs.length);

        //add ny dog till slutet på nya array
        newDogs[newDogs.length-1] = d;

        //uppdatera orginala array reference som pekar mot nya array
        dogs = newDogs;

        //uppdatera storlek
        size++;
    }

    public void removeDogFromList(Dog d) {
        if (d == null) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (dogs[i].getName().equals(d.getName())) {
                //skapa en till array med en mindre element
                int newSize = dogs.length - 1;
                Dog[] newDogList = new Dog[newSize];

                //kopiera element från gamla till nya, skippar
                int j = 0;
                for (int k = 0; k < size; k++) {
                    if (!dogs[k].getName().equals(d.getName())) {
                        newDogList[j] = dogs[k];
                        j++;
                    }
                }

                size--;
                dogs = newDogList;
                d.setOwner(null);
                return;
            }
        }
    }

    public boolean isDogInList(Dog d) {
        if (d == null) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (dogs[i].getName().equals(d.getName())) {
                return true;
            }
        }
        return false;
    }

    public String fetchDogsName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(dogs[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}