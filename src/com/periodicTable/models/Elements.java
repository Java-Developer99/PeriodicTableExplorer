package com.periodicTable.models;

public class Elements {

    private int atomicNumber;
    private String name;
    private String symbol;
    private double atomicWeight;
    private int group;
    private int period;

    public Elements(int atomicNumber, String name, String symbol, double atomicWeight, int group, int period) {
        this.atomicNumber = atomicNumber;
        this.name = name;
        this.symbol = symbol;
        this.atomicWeight = atomicWeight;
        this.group = group;
        this.period = period;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public int getGroup() {
        return group;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "Elements{" +
                "atomicNumber=" + atomicNumber +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", atomicWeight=" + atomicWeight +
                ", group=" + group +
                ", period=" + period +
                '}';
    }
}
