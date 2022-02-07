package com.meli.mutant.mutantapp.model.request;

import java.util.Arrays;
import java.util.List;

public class DnaRequestDTO {

    public String[] dna;

    public DnaRequestDTO() {
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "DnaRequest{" +
                "dna=" + Arrays.toString(dna) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnaRequestDTO that = (DnaRequestDTO) o;
        return Arrays.equals(dna, that.dna);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dna);
    }
}
