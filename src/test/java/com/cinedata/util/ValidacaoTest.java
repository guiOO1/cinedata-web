package com.cinedata.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacaoTest {

    @Test
    void testSenhaValida() {
        assertTrue(Validacao.validarSenha("123456"));
    }

    @Test
    void testSenhaInvalida() {
        assertFalse(Validacao.validarSenha("12345"));
    }

    @Test
    void testEmailValido() {
        assertTrue(Validacao.validarEmail("teste@email.com"));
    }

    @Test
    void testEmailInvalido() {
        assertFalse(Validacao.validarEmail("emailerrado"));
    }
}