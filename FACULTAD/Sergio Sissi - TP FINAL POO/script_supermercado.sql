CREATE DATABASE IF NOT EXISTS supermercado;

USE `supermercado`;

CREATE TABLE IF NOT EXISTS persona (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  dni VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
  id_persona BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id_persona),
  CONSTRAINT cliente_persona_id_fk FOREIGN KEY (id_persona) REFERENCES persona(id)
);


CREATE TABLE IF NOT EXISTS pago (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  montoPagado DOUBLE NOT NULL,
  fechaPago DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS compra (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  cliente_id BIGINT UNSIGNED NOT NULL,
  total DOUBLE NOT NULL,
  fechaCompra DATE NOT NULL,
  pago_id BIGINT UNSIGNED NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES cliente(id_persona),
  FOREIGN KEY (pago_id) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS producto (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  stock INT NOT NULL,
  departamento VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS compra_producto (
  id INT UNSIGNED AUTO_INCREMENT,
  compra_id BIGINT UNSIGNED NOT NULL,
  producto_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (compra_id) REFERENCES compra(id),
  FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE IF NOT EXISTS efectivo (
  id_pago BIGINT UNSIGNED NOT NULL,
  descuento DOUBLE NOT NULL,
  PRIMARY KEY (id_pago),
  CONSTRAINT efectivo_pago_id_fk FOREIGN KEY (id_pago) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS tarjeta (
  id_pago BIGINT UNSIGNED NOT NULL,
  cuotas INT NOT NULL,
  recargo DOUBLE NOT NULL,
  tipo_tarjeta VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_pago),
  CONSTRAINT tarjeta_pago_id_fk FOREIGN KEY (id_pago) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS empleado (
  id_persona BIGINT UNSIGNED NOT NULL,
  legajo VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_persona),
  CONSTRAINT empleado_persona_id_fk FOREIGN KEY (id_persona) REFERENCES persona(id)
);

CREATE TABLE IF NOT EXISTS producto_compuesto (
  producto_id INT UNSIGNED NOT NULL,
  PRIMARY KEY ( producto_id),
  CONSTRAINT producto_compuesto_producto_id_fk FOREIGN KEY (producto_id) REFERENCES producto (id) 
);

CREATE TABLE IF NOT EXISTS producto_simple (
  producto_id INT UNSIGNED NOT NULL,
  precio DOUBLE NOT NULL,
  PRIMARY KEY ( producto_id),
  CONSTRAINT producto_simple_producto_id_fk FOREIGN KEY (producto_id) REFERENCES producto (id) 
);

CREATE TABLE IF NOT EXISTS producto_peso (
  producto_id INT UNSIGNED NOT NULL,
  peso DOUBLE NOT NULL,
  PRIMARY KEY ( producto_id),
  CONSTRAINT producto_peso_producto_id_fk FOREIGN KEY (producto_id) REFERENCES producto (id)
);

CREATE TABLE IF NOT EXISTS producto_compuesto_producto (
  id INT UNSIGNED AUTO_INCREMENT NOT NULL, 
  producto_compuesto_id INT UNSIGNED NOT NULL,
  producto_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ProductoCompuesto_ProductoCompuestoProducto FOREIGN KEY (producto_compuesto_id) REFERENCES producto_compuesto (producto_id),
  CONSTRAINT FK_Producto_ProductoCompuestoProducto FOREIGN KEY (producto_id) REFERENCES producto (id)
);
