package com.plazacomidas.user.adapters.in.rest.routes;

public class ApiRoutes {

    private ApiRoutes() {
    }

    public static final String LOGIN = "/login";
    public static final String USERS ="/usuarios";
    public static final String OWNER = "/propietario";
    public static final String EMPLOYEE = "/empleado";
    public static final String CLIENT = "/cliente";
    public static final String OWNER_BY_ID = "/{id}";
}
