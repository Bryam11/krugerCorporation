package com.krugercorporation.apiVaccine.handle;

import com.krugercorporation.apiVaccine.security.dto.SignupDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validations {

    public static String[] ValidateEmployee(SignupDTO signupDTO) {
        List<String> mensajes = new ArrayList<>();

        // validate if the fields are empty
        if (signupDTO.getName() == null || signupDTO.getLastName() == null || signupDTO.getCedula() == null || signupDTO.getEmail() == null) {
            mensajes.add("Todos los campos son obligatorios");
        }

        // validate cedula
        String cedula = signupDTO.getCedula();
        if (!Pattern.matches("^0\\d{9}$", cedula)) {
            mensajes.add("La cédula no tiene un formato válido.");
        }

        // validate that the email is valid
        String email = signupDTO.getEmail();
        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            mensajes.add("El correo electrónico no tiene un formato válido.");
        }

        // validate that the names and surnames do not contain special characters
        String names = signupDTO.getName();
        String surnames = signupDTO.getLastName();
        if (!Pattern.matches("^[\\p{L} ]+$", names) || !Pattern.matches("^[\\p{L} ]+$", surnames)) {
            mensajes.add("Los nombres y apellidos no pueden contener caracteres especiales.");
        }

        // return 200 if everything is ok
        return mensajes.toArray(new String[0]);
    }

    ;
}
