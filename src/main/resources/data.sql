INSERT INTO roles (id, nombre, descripcion) VALUES (1, 'PROPIETARIO', 'Rol de propietario');
INSERT INTO roles (id, nombre, descripcion) VALUES (2, 'ADMINISTRADOR', 'Rol de administrador');
INSERT INTO roles (id, nombre, descripcion) VALUES (3, 'EMPLEADO', 'Rol de empleado');
INSERT INTO roles (id, nombre, descripcion) VALUES (4, 'CLIENTE', 'Rol de cliente');

-- Usuarios de prueba (las contraseñas ya están encriptadas con BCrypt)

INSERT INTO usuarios (
    nombre, apellido, documento_de_identidad, celular,
    fecha_nacimiento, correo, clave, id_rol
) VALUES (
             'Admin', 'Plaza', '10000001', '+573000000001',
             '1990-01-01', 'admin@plazacomidas.com',
             '$2a$10$kXrmQN9WDmSNIT8T7fOP9.0da/o3Epa3zH6x6Wu3sl7CFoDhTPm2W', -- admin123
             2
         );

INSERT INTO usuarios (
    nombre, apellido, documento_de_identidad, celular,
    fecha_nacimiento, correo, clave, id_rol
) VALUES (
             'Pedro', 'Propietario', '10000002', '+573000000002',
             '1985-05-10', 'propietario@plazacomidas.com',
             '$2a$10$hKHkTRjEhZPBDuWh2nrtWulNZEyDyp4sbzdwPJBr8jbbxRYPzKgDy', -- propietario123
             1
         );

INSERT INTO usuarios (
    nombre, apellido, documento_de_identidad, celular,
    fecha_nacimiento, correo, clave, id_rol
) VALUES (
             'Emilia', 'Empleado', '10000003', '+573000000003',
             '2000-08-15', 'empleado@plazacomidas.com',
             '$2a$10$rDUJ0Z5FKULPYp1kpDFJQOmv1r/.xPYXw9cpCNLv.JoXlmjE0Y47C', -- empleado123
             3
         );

INSERT INTO usuarios (
    nombre, apellido, documento_de_identidad, celular,
    fecha_nacimiento, correo, clave, id_rol
) VALUES (
             'Carla', 'Cliente', '10000004', '+573000000004',
             '1995-12-12', 'cliente@plazacomidas.com',
             '$2a$10$Bv0ruNu1qvhHfOejbyU./eb8V/7O8f5L0W5jBT0C.6uHyfveh1mlK', -- cliente123
             4
         );