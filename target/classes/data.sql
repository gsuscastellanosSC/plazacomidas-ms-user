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
             '$2a$10$20yjJ6HKbCLBR9FYcLW2nOQfGmAXLn4JLdYqtSxUaeq8c2F3yKce2', -- admin123
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
