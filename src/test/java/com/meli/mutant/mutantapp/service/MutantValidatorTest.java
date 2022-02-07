package com.meli.mutant.mutantapp.service;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class MutantValidatorTest {

    private MutantValidator mutantValidator;

    @BeforeEach
    public void setup() {
        mutantValidator = new MutantValidator();
    }

    @Test
    public void testValidateMutant_invalidCharacter() {
        //given
        String[] dna = {"CTXTGT", "CPATCT", "CGKANC", "AGAAAG", "TTATAA", "LGCTGC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isFalse();
    }

    @Test
    public void testValidateMutant_emptySequence() {
        //given
        String[] dna = new String[5];
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isFalse();
    }

    @Test
    public void testValidateMutant_nullSequence() {
        //given
        String[] dna = null;
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isFalse();
    }

    @Test
    public void testValidateMutant_diagonalLeftSequences() {
        //given
        String[] dna = {"CTCTGT", "CAAATT", "CGTTGC", "AGTGCG", "TACCGA", "AGCTGC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isTrue();
    }


    @Test
    public void testValidateMutant_diagonalRightSequences() {
        //given
        String[] dna = {"CTCTGT", "CAATCT", "CGAAGC", "AGAAAG", "TTCTAA", "AGCTGC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isTrue();
    }

    @Test
    public void testValidateMutant_verticalSequences() {
        //given
        String[] dna = {"CTCTAT", "CACAGT", "CGCGGA", "AGCGGG", "TATTGA", "AGAGTC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isTrue();
    }

    @Test
    public void testValidateMutant_horizontalSequences() {
        //given
        String[] dna = {"CTCTGT", "CACAAT", "CGAAAA", "AGCGCG", "TATTGA", "AGGGGC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isTrue();
    }

    @Test
    public void testValidateMutant_allDirectionsSequences() {
        //given
        String[] dna = {"GCCCCTA", "GATCGTG", "GGCGCAC", "GCCGGCC", "TATTGAT", "ATGAAAA"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isTrue();
    }

    @Test
    public void testValidateMutant_NoSequence() {
        //given
        String[] dna = {"CTCTAT", "CATAGT", "CGCGGA", "AGCGAG", "TATTGA", "AGAGTC"};
        //when
        boolean result = mutantValidator.isMutant(dna);
        //then
        BDDAssertions.then(result).isFalse();
    }
}
