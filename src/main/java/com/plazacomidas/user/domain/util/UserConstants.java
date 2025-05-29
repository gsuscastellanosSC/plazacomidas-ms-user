package com.plazacomidas.user.domain.util;


public final class UserConstants {

    private UserConstants() {}

    public static final String ROLE_OWNER = "PROPIETARIO";
    public static final String ROLE_EMPLOYEE = "EMPLEADO";
    public static final String ROLE_ADMIN = "ADMINISTRADOR";

    public static final String ERROR_INVALID_EMAIL = "Correo inválido";
    public static final String ERROR_INVALID_DOCUMENT = "El documento debe ser numérico";
    public static final String ERROR_INVALID_PHONE = "El celular es inválido";
    public static final String ERROR_INVALID_DATE_FORMAT = "Formato de fecha inválido.";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String ERROR_RESTAURANT_ID_REQUIRED = "El id del restaurante es obligatorio y debe ser numérico positivo";

    public static final String DATE_FORMAT_DD_MM_YYYY_SLASH = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_DASH = "dd-MM-yyyy";

    public static final String REGEX_EMAIL = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String REGEX_PHONE = "^\\+?\\d{1,13}$";
    public static final String REGEX_DOCUMENT = "\\d+";

    public static final String JSON_FIRST_NAME = "nombre";
    public static final String JSON_LAST_NAME = "apellido";
    public static final String JSON_DOCUMENT_ID = "documentoDeIdentidad";
    public static final String JSON_PHONE = "celular";
    public static final String JSON_BIRTH_DATE = "fechaNacimiento";
    public static final String JSON_EMAIL = "correo";
    public static final String JSON_PASSWORD = "clave";
    public static final String JSON_RESTAURANT_ID = "idRestaurante";
    public static final String JSON_ROLE = "rol";

    public static final String COLUMN_FIRST_NAME = "nombre";
    public static final String COLUMN_LAST_NAME = "apellido";
    public static final String COLUMN_DOCUMENT_ID = "documento_de_identidad";
    public static final String COLUMN_PHONE = "celular";
    public static final String COLUMN_BIRTH_DATE = "fecha_nacimiento";
    public static final String COLUMN_EMAIL = "correo";
    public static final String COLUMN_PASSWORD = "clave";
    public static final String COLUMN_ROLE_ID = "id_rol";
    public static final String COLUMN_RESTAURANT_ID = "id_restaurantel";
}
