package com.plazacomidas.user.domain.util;

public final class UsuarioConstants {

    private UsuarioConstants() {}

    public static final String ROL_PROPIETARIO = "PROPIETARIO";
    public static final String ERROR_ROL_NO_ENCONTRADO = "Rol no encontrado";
    public static final String ERROR_CORREO_INVALIDO = "Correo inválido";
    public static final String ERROR_DOCUMENTO_INVALIDO = "El documento debe ser numérico";
    public static final String ERROR_CELULAR_INVALIDO = "El celular es inválido";
    public static final String ERROR_EDAD_INVALIDA = "El usuario debe ser mayor de edad";

    public static final String REGEX_CORREO = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String REGEX_CELULAR = "^\\+?\\d{1,13}$";
    public static final String REGEX_DOCUMENTO = "\\d+";
}
