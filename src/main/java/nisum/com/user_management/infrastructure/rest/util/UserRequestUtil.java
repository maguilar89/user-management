package nisum.com.user_management.infrastructure.rest.util;

public interface UserRequestUtil {

    String REGULAR_EXPRESSION_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    String MESSAGE_EMAIL = "El correo debe seguir una expresión regular para validar que formato sea el correcto.(aaaaaaa@dominio.cl)";
    String PASSWORD_EMPTY = "Password es obligatorio";
    String REGULAR_EXPRESSION_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$";
    String MESSAGE_EXPRESSION_PASSWORD = "La contraseña debe tener al menos 7 caracteres, contener al menos una " +
            "letra mayúscula, un número y un carácter especial.";
    String CITY_CODE = "citycode";
    String COUNTY_CODE = "contrycode";
    String MS_SWAGGER_SUMMARY = "CREAR NUEVO USUARIO";
    String MS_SWAGGER_DESCRIPTION = "CREAR NUEVO USUARIO";


}
