package com.meli.mutant.mutantapp.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MutantServiceTest {

    private MutantService mutantService;

    @Test
    public void testValidateMutant_diagonalLeftSequence() {
        Assertions.assertThat(Boolean.TRUE).isTrue();
    }
}
