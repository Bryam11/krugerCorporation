package com.krugercorporation.apiVaccine;

import com.krugercorporation.apiVaccine.security.dto.JwtDto;
import com.krugercorporation.apiVaccine.security.dto.LoginUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testLogin() {
        // Crear un objeto que representa las credenciales de inicio de sesión
        LoginUsuario loginRequest = new LoginUsuario();
        loginRequest.setNombreUsuario("admin");
        loginRequest.setPassword("admin");

        // Hacer una llamada POST al endpoint de inicio de sesión
        ResponseEntity<JwtDto> response = testRestTemplate.postForEntity("/auth/login", loginRequest, JwtDto.class);

        // Verificar que la respuesta incluya un token de autenticación
        assertNotNull(response.getBody().getToken());
    }
}
