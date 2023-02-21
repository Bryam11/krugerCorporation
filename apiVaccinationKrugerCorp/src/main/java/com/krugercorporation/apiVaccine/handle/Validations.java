package com.krugercorporation.apiVaccine.handle;

import com.krugercorporation.apiVaccine.constants.GeneralConstants;
import com.krugercorporation.apiVaccine.dto.EmployeeUpdateDto;
import com.krugercorporation.apiVaccine.security.dto.SignupDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validations {

    public static String[] ValidateEmployee(SignupDTO signupDTO) {
        List<String> mensajes = new ArrayList<>();

        // validate if the fields are empty
        if (signupDTO.getNames() == null || signupDTO.getSurnames() == null || signupDTO.getCedula() == null || signupDTO.getEmail() == null) {
            mensajes.add("Todos los campos son obligatorios");
        }

        // validate cedula
        String cedula = signupDTO.getCedula();
        if (cedula.length() < 10 || !cedula.matches("[0-9]+")) {
            mensajes.add("La cédula no tiene un formato válido.");
        }

        // validate that the email is valid
        String email = signupDTO.getEmail();
        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            mensajes.add("El correo electrónico no tiene un formato válido.");
        }

        // validate that the names and surnames do not contain special characters
        String names = signupDTO.getNames();
        String surnames = signupDTO.getSurnames();
        if (!Pattern.matches("^[\\p{L} ]+$", names) || !Pattern.matches("^[\\p{L} ]+$", surnames)) {
            mensajes.add("Los nombres y apellidos no pueden contener caracteres especiales.");
        }

        // validamos que el idRole no sea 0
        if (signupDTO.getIdRole() == 0) {
            mensajes.add("El rol es obligatorio");
        }

        // validamos que el idRole no sea mayor al numero 2
        if (signupDTO.getIdRole() > 2) {
            mensajes.add("El rol no es válido actualmente solo puede ser 1 que es el rol de administrador o 2 que es el rol de empleado");
        }

        // return 200 if everything is ok
        return mensajes.toArray(new String[0]);
    }

    ;

    public static String[] validateUpdateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        List<String> mensajes = new ArrayList<>();

        if (employeeUpdateDto.getTelephone() == null || employeeUpdateDto.getTelephone().isEmpty()) {
            mensajes.add("El teléfono es obligatorio");
        }
        if (!employeeUpdateDto.getTelephone().matches("[0-9]+")) {
            mensajes.add("El teléfono no tiene un formato válido.");
        }
        if (!employeeUpdateDto.getStateVaccination().equals(GeneralConstants.STATE_VACCINATION_YES) || !employeeUpdateDto.getStateVaccination().equals(GeneralConstants.STATE_VACCINATION_NO)) {
            mensajes.add("Solo puede ser: Vacunado, No Vacunado");
        }
        if (employeeUpdateDto.getStateVaccination() == null || employeeUpdateDto.getStateVaccination().isEmpty()
                || employeeUpdateDto.getStateVaccination().equals(GeneralConstants.STATE_VACCINATION_NO)) {
            return mensajes.toArray(new String[0]);
        }
        if (employeeUpdateDto.getStateVaccination().length() > 11) {
            mensajes.add("El estado de vacunación no puede tener más de 11 caracteres solo puede ser: Vacunado, No Vacunado");
        }

        if (employeeUpdateDto.getIdTypeVaccine() == 0 || employeeUpdateDto.getDateVaccination() == null || employeeUpdateDto.getDose() == 0) {
            mensajes.add("El tipo de vacuna, la fecha de vacunación y la dosis son obligatorios");
        }

        return mensajes.toArray(new String[0]);
    }

//    public static String[] validateStateVaccine(EmployeeUpdateDto employeeUpdateDto) {
//        List<String> mensajes = new ArrayList<>();
//
//        if (employeeUpdateDto.getStateVaccination() == null || employeeUpdateDto.getStateVaccination().isEmpty()) {
//            mensajes.add("El estado de vacunación es obligatorio");
//            return mensajes.toArray(new String[0]);
//        }
//        if (employeeUpdateDto.getStateVaccination().length() > 11) {
//            mensajes.add("El estado de vacunación no puede tener más de 11 caracteres solo puede ser: Vacunado, No Vacunado");
//        }
//
//        if (employeeUpdateDto.getIdTypeVaccine() == 0 || employeeUpdateDto.getDateVaccination() == null || employeeUpdateDto.getDose() == 0) {
//            mensajes.add("El tipo de vacuna, la fecha de vacunación y la dosis son obligatorios");
//        }
//
//
//        return mensajes.toArray(new String[0]);
//    }
}
