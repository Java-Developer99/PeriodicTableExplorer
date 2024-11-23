package com.periodicTable.services;

import com.periodicTable.models.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class ElementService {

    private final List<Elements> elements;

    // Constructor to inject the list of elements (e.g., loaded by DataLoader)
    public ElementService(List<Elements> elements) {
        this.elements = elements;
    }

    public List<Elements> searchByName(String name) {
        return elements.stream()
                .filter(element -> element.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Elements> searchBySymbol(String symbol) {
        return elements.stream()
                .filter(element -> element.getSymbol().toLowerCase().contains(symbol.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Elements> getByGroup(int group) {
        return elements.stream()
                .filter(element -> element.getGroup() == group)
                .collect(Collectors.toList());
    }

    public List<Elements> getByPeriod(int period) {
        return elements.stream()
                .filter(element -> element.getPeriod() == period)
                .collect(Collectors.toList());
    }

    public List<Elements> getByAtomicWeight(double weight) {
        return elements.stream()
                .filter(element -> element.getAtomicWeight() > weight)
                .collect(Collectors.toList());
    }
}
