DROP DATABASE IF EXISTS jraceros;
CREATE DATABASE jraceros;
USE jraceros;

-- Tabla Trabajador
CREATE TABLE trabajador (
                            id_trabajador INT(3) PRIMARY KEY AUTO_INCREMENT,
                            nombre VARCHAR(50) NOT NULL,
                            apellido VARCHAR(100) NOT NULL,
                            dni INT(8) NOT NULL,
                            rol VARCHAR(15) NOT NULL,
                            telefono INT(9) NOT NULL,
                            correo VARCHAR(30) NOT NULL,
                            contraseña VARCHAR(255) NOT NULL
);

-- Tabla Cliente
CREATE TABLE cliente (
                         id_cliente INT(3) PRIMARY KEY AUTO_INCREMENT,
                         nombre VARCHAR(50) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                         telefono INT(9) NOT NULL,
                         correo VARCHAR(30) NOT NULL,
                         tipo_documento VARCHAR(30) NOT NULL,
                         numero_documento VARCHAR(15) NOT NULL
);

-- Tabla Proveedor
CREATE TABLE proveedor (
                           id_proveedor INT(3) PRIMARY KEY AUTO_INCREMENT,
                           nombre_empresa VARCHAR(30) NOT NULL,
                           telefono INT(9) NOT NULL,
                           correo VARCHAR(30) NOT NULL,
                           ruc INT(13) NOT NULL
);

-- Tabla Categoria
CREATE TABLE categoria (
                           id_categoria INT(3) PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(30) NOT NULL
);


-- Tabla Producto
CREATE TABLE producto (
                          id_producto INT(3) PRIMARY KEY AUTO_INCREMENT,
                          id_categoria INT(3) NOT NULL,
                          nombre VARCHAR(30) NOT NULL,
                          tipo VARCHAR(20) NOT NULL,
                          descripcion VARCHAR(50) NOT NULL,
                          precio DOUBLE(6,2) NOT NULL,
                          FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

-- Tabla Inventario
CREATE TABLE inventario (
                            id_inventario INT(3) PRIMARY KEY DEFAULT = 1,
                            id_producto INT(3) NOT NULL,
                            stock INT NOT NULL,
                            FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

-- Tabla MovimientoInventario
CREATE TABLE movimiento_inventario (
                                      id_movimiento_inventario INT(3) PRIMARY KEY AUTO_INCREMENT,
                                      id_producto INT(3) NOT NULL,
                                      id_trabajador INT(3) NOT NULL,
                                      id_proveedor INT(3) NOT NULL,
                                      cantidad INT NOT NULL,
                                      tipo_movimiento VARCHAR(15) NOT NULL,
                                      fecha TIMESTAMP NOT NULL,
                                      FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
                                      FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id_trabajador),
                                      FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)
);

-- Tabla Venta
CREATE TABLE venta (
                       id_venta INT(3) PRIMARY KEY AUTO_INCREMENT,
                       id_cliente INT(3) NOT NULL,
                       id_trabajador INT(3) NOT NULL,
                       metodo_pago VARCHAR(15) NOT NULL,
                       fecha TIMESTAMP NOT NULL,
                       tipo_comprobante VARCHAR(15) NOT NULL,
                       igv DOUBLE(6,2) NOT NULL,
                       total DOUBLE(6,2) NOT NULL,
                       FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
                       FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id_trabajador)
);

-- Tabla DetalleVenta
CREATE TABLE detalle_venta (
                              id_detalle_venta INT(3) PRIMARY KEY AUTO_INCREMENT,
                              id_venta INT(3) NOT NULL,
                              id_producto INT(3) NOT NULL,
                              cantidad INT NOT NULL,
                              precio_unitario DOUBLE(6,2) NOT NULL,
                              sub_total DOUBLE(6,2) NOT NULL,
                              FOREIGN KEY (id_venta) REFERENCES Venta(id_venta),
                              FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);