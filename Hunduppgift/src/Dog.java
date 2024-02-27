//Alexander alpa7946
public class Dog{

    private static final double DEFAULT_DASCHSHUND_TAIL_LENGTH = 3.7; //static final variable, ett värde som kommer aldrig ändras, NAMNGES MED STORA BOKSTÄVER SAMT UNDERSTRÄCK

    private Owner dogOwner;

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tail;

    public Dog(String name, String breed, int age, int weight)
    {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public String getName()
    {
        return name;
    }

    public String getBreed()
    {
        return breed;
    }

    public int getAge()
    {
        return age;
    }

    public int getWeight()
    {
        return weight;
    }

    public void addAge()
    {
        age++;
    }

    public double getTailLength()
    {
        //skapar array för taxnamn
        String [] arrayTaxNames = {"tax","dachshund", "teckel", "mäayräakoira"};

        int i = 0;
        //går igenom alla namn för taxar
        while(i<arrayTaxNames.length)
        {
            //om namnet matchar avbryt metoden och skicka iväg 3.7
            if(breed.equalsIgnoreCase(arrayTaxNames[i]))
            {
                tail =  DEFAULT_DASCHSHUND_TAIL_LENGTH;
                return tail;
            }
            i++;

        }
        tail = this.age * (this.weight/10.0);
        return tail;
    }

    @Override
    public String toString() {
        return getName() + " (" + getBreed() + ", " + getAge() + " years, " + getWeight() + " kilo, " + getTailLength() +  " cm tail, ";
    }

    public void setOwner(Owner newOwner) {

        if(this.dogOwner == null && newOwner != null) { //if this dogOwner är null så har den redan en ägare && ifall owner inte är = null
            this.dogOwner = newOwner;
            newOwner.assignDogToOwner(this);
        }
        this.dogOwner = newOwner;
    }

    public Owner getOwner() {
        return this.dogOwner;
    }

    public boolean hasOwner(){
        return dogOwner != null;
    }

    public void removeOwner(){
        if(!hasOwner()){
            return;
        }
        Owner o = this.dogOwner;
        this.dogOwner = null;
        o.removeDog(this);
    }

}