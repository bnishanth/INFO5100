/* good Work
 * Score 8 + extra credit 1.5; Total score 9.5
 */
import java.util.Vector;


public class Assignment5
{
    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        checkout.enterItem(new Candy("Peanut Butter Fudge", 2.25, 399));
        checkout.enterItem(new IceCream("Vanilla Ice Cream", 105));
        checkout.enterItem(new Sundae("Choc. Chip Ice Cream", 145, "Hot Fudge", 50));
        checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);

        checkout.clear();

        checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
        checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
        checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
        checkout.enterItem(new Cookie("Chocolate Chip Cookies", 4, 399));
        checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
        checkout.enterItem(new Candy("Candy Corn", 3.0, 109));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);
    }
}






abstract class DessertItem
{
    String name; // access should be protected
    public DessertItem()
    {
    }
    public DessertItem(String name)
    {
        this.name = name;
    }
    public final java.lang.String getName()
    {
        return this.name;
    }

    public abstract int getCost();

    public String basicString()
    {
        DessertShoppe shop = new DessertShoppe();
        String stringFormat = "";
        String price = shop.cents2dollarsAndCents(this.getCost());
        if(this.name.length() < shop.maxSize)
        {
            stringFormat = stringFormat + this.name;
            for(int i = 0; i < shop.width - this.name.length() - price.length(); i++)
                stringFormat = stringFormat + " ";
        }
        else
        {
            stringFormat = stringFormat + this.name.substring(0, shop.maxSize) + "\n" + this.name.substring(shop.maxSize, this.name.length());
            for(int i = 0; i < shop.width - this.name.length() - price.length() + shop.maxSize; i ++)
                stringFormat = stringFormat + " ";
        }
        stringFormat = stringFormat + price + "\n";
        return stringFormat;
    }
    public abstract String toString();
}


class DessertShoppe
{
    int maxSize, width; // the attributes should be static and final
    public String name;
    double taxRate;
    public DessertShoppe()
    {
        this.maxSize = 35;
        this.width = 40;
        this.name = "Eskimos";
        this.taxRate = 0.065;
    }
    public String cents2dollarsAndCents(int cents)
    {
        double dollars = (double)cents / 100;
        return String.valueOf(dollars);
    }
}


class Candy extends DessertItem
{
    double weight; // access should be private
    int pricePerPound;
    public Candy(String name, double weight, int pricePerPound)
    {
        this.name = name;
        this.weight = weight;
        this.pricePerPound = pricePerPound;
    }
    public int getCost()
    {
        return (int)Math.round(this.weight * this.pricePerPound);
    }
    public String toString()
    {
        DessertShoppe shop = new DessertShoppe();
        String stringFormat = String.valueOf(this.weight) + " lbs. @ " + shop.cents2dollarsAndCents(this.pricePerPound) + " /lb.\n";
        stringFormat = stringFormat +  this.basicString();
        return stringFormat;
    }
}


class Cookie extends DessertItem
{
    int number, pricePerDozen;
    public Cookie(String name, int number, int pricePerDozen)
    {
        this.name = name;
        this.number = number;
        this.pricePerDozen = pricePerDozen;
    }
    public int getCost()
    {
        return (int)Math.round((double)this.number * this.pricePerDozen / 12);
    }
    public String toString()
    {
        DessertShoppe shop = new DessertShoppe();
        String stringFormat = String.valueOf(this.number) + " @ " + shop.cents2dollarsAndCents(this.pricePerDozen) + " /dz.\n";
        stringFormat = stringFormat + this.basicString();
        return stringFormat;
    }
}


class IceCream extends DessertItem
{
    int cost;
    public IceCream()
    {
    }
    public IceCream(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }
    public int getCost()
    {
        return this.cost;
    }
    public String toString()
    {
        String stringFormat = "";
        stringFormat = stringFormat + this.basicString();
        return stringFormat;
    }
}


class Sundae extends IceCream
{
    int topping;
    String toppingName;
    public Sundae(String name, int cost, String toppingName, int topping)
    {
        this.name = name;
        this.toppingName = toppingName;
        this.cost = cost;
        this.topping = topping;
    }
    public int getCost()
    {
        return (this.cost + this.topping);
    }
    public String toString()
    {
        String stringFormat = this.toppingName + " Sundae with\n";
        stringFormat  = stringFormat + this.basicString();
        return stringFormat;
    }
}


class Checkout
{
    Vector<DessertItem> items;
    public Checkout()
    {
        this.items = new Vector<DessertItem>();
    }
    public int numberOfItems()
    {
        return this.items.size();
    }
    public void enterItem(DessertItem item)
    {
        items.addElement(item);
    }
    public void clear()
    {
        this.items.clear();
    }
    public int totalCost()
    {
        int total = 0;
        for(int i = 0; i < this.items.size(); i++)
            total += this.items.get(i).getCost();
        return total;
    }
    public int totalTax()
    {
        DessertShoppe shop = new DessertShoppe();
        int tax = (int)Math.round(shop.taxRate * this.totalCost());
        return tax;
    }
    public java.lang.String toString()
    {
        DessertShoppe shop = new DessertShoppe();
        String receipt = "";
        for(int i = 0; i < shop.width - shop.name.length(); i += 2)
            receipt += " ";
        receipt = receipt + shop.name + "\n";

        for(int i = 0; i < this.items.size(); i++)
        {
            receipt += this.items.get(i).toString();
        }
        String tax = shop.cents2dollarsAndCents(this.totalTax()), total = shop.cents2dollarsAndCents(this.totalCost() + this.totalTax());
        receipt += "\nTax";
        for(int i = 0; i < shop.width - tax.length() - 3; i++)
            receipt += " ";
        receipt = receipt + tax + "\nTotal Cost";
        for(int i = 0; i < shop.width - total.length() - 10; i++)
            receipt += " ";
        receipt = receipt + total + "\n";
        return receipt;
    }
}



