package assignment.address.model;

/**
 *  @author Daniel Byers | 13121312
 */
import java.util.*;

public class Country implements Comparable<Country>
{
    private  String name;    
    private double GDPGrowth, inflation, tradeBalance, tradePotential;    
    private int HDIRanking;
    private LinkedList<String> tradePartners;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGDPGrowth() {
        return GDPGrowth;
    }

    public void setGDPGrowth(double GDPGrowth) {
        this.GDPGrowth = GDPGrowth;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public double getTradeBalance() {
        return tradeBalance;
    }

    public void setTradeBalance(double tradeBalance) {
        this.tradeBalance = tradeBalance;
    }
    
    public double getTradePotential() {
        return tradePotential;
    }

    public void setTradePotential(double tradePotential) {
        this.tradePotential = tradePotential;
    }

    public int getHDIRanking() {
        return HDIRanking;
    }

    public void setHDIRanking(int HDIRanking) {
        this.HDIRanking = HDIRanking;
    }

    public LinkedList<String> getTradePartners() {
        return tradePartners;
    }

    public void setTradePartners(LinkedList<String> tradePartners) {
        this.tradePartners = tradePartners;
    }
    
    /**
    *   Empty constructor.
    */
    public Country ()
    {
        name = "";
        GDPGrowth = 0;
        tradeBalance = 0;
        inflation = 0;
        HDIRanking = 0;
        tradePartners = new LinkedList<>();
    }
    
    /**
    *   Constructors
    *   Initalises class as null/empty or with pre-defined
    *   variables.
    *   @param name passes in Name as a String.
    *   @param GDPGrowth passes in GDP Growth as a double.
    *   @param inflation passes in inflation as a double.
    *   @param tradeBalance passes in tradeBalance as a double.
    *   @param HDIRanking passes in HDI Ranking as an int.
    *   @param tradePartners passes in the Trade Partners as a LinkedList of type String.
    */
    public Country(String name, double GDPGrowth, double inflation, double tradeBalance, int HDIRanking, LinkedList<String> tradePartners)
    {
        this.name = name;
        this.GDPGrowth = GDPGrowth;
        this.tradeBalance = tradeBalance;
        this.inflation = inflation;
        this.HDIRanking = HDIRanking;
        this.tradePartners = tradePartners;
    }
    
    /**
    *   Overrides compareTo method that is being implemented from
    *   Comparable and defining it so that it compares the names of the
    *   countries using the compareTo method of the String class.
    *   @param other {@link Country} that is being compared to.
    *   @return returns a negative int for smaller, 0 for equal, or 
    *   a positive int for larger.
    */
    @Override
    public int compareTo (Country other)
    {
        return this.name.compareTo(other.name);
    }
    
    /**
    *   Similar to above method but compares the {@link Country} name with a
    *   pre-defined String. This will enable search functionality.
    *   @param other String variable being passed in to compare against {@link Country} name
    *   @return  returns a negative integer for smaller, 0 for equal, or 
    *   a positive integer for larger.
    */
    public int compareToByString (String other)
    {
        return this.name.compareToIgnoreCase(other);
    }
}
