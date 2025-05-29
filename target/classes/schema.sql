CREATE TABLE roles (
                       id BIGINT PRIMARY KEY,
                       nombre VARCHAR(50) NOT NULL,
                       descripcion VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuarios (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(255),
    apellido VARCHAR(255),
    documento_de_identidad VARCHAR(255),
    celular VARCHAR(255),
    fecha_nacimiento DATE,
    correo VARCHAR(255) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL,
    id_rol BIGINT,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES roles(id)
    );