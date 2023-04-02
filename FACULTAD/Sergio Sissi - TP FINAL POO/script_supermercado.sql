CREATE TABLE IF NOT EXISTS persona (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  dni VARCHAR(255) NOT NULL,
  telefono VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
  id BIGINT UNSIGNED PRIMARY KEY,
  legajo VARCHAR(255) NOT NULL,
  FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE IF NOT EXISTS compra (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  cliente_id BIGINT UNSIGNED NOT NULL,
  total DOUBLE PRECISION NOT NULL,
  fechaCompra DATE NOT NULL,
  pago_id BIGINT UNSIGNED NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  FOREIGN KEY (pago_id) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS compra_producto (
  compra_id BIGINT UNSIGNED NOT NULL,
  producto_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (compra_id, producto_id),
  FOREIGN KEY (compra_id) REFERENCES compra(id),
  FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE IF NOT EXISTS pago (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  montoPagado DOUBLE PRECISION NOT NULL,
  fechaPago DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS efectivo (
  id BIGINT UNSIGNED NOT NULL,
  descuento DOUBLE PRECISION NOT NULL,
  FOREIGN KEY (id) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS tarjeta (
  id BIGINT UNSIGNED NOT NULL,
  cuotas INT NOT NULL,
  recargo DOUBLE PRECISION NOT NULL,
  tipo_tarjeta VARCHAR(255) NOT NULL,
  FOREIGN KEY (id) REFERENCES pago(id)
);

CREATE TABLE IF NOT EXISTS empleado (
  id BIGINT UNSIGNED PRIMARY KEY,
  legajo VARCHAR(255) NOT NULL,
  FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE IF NOT EXISTS producto (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  stock INT NOT NULL,
  departamento VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS producto_compuesto (
  id INT UNSIGNED NOT NULL,
  FOREIGN KEY (id) REFERENCES producto(id)
);

CREATE TABLE IF NOT EXISTS producto_simple (
  id INT UNSIGNED NOT NULL,
  precio DOUBLE PRECISION NOT NULL,
  FOREIGN KEY (id) REFERENCES producto(id)
);

CREATE TABLE IF NOT EXISTS producto_por_peso (
  id INT UNSIGNED NOT NULL,
  peso DOUBLE PRECISION NOT NULL,
  FOREIGN KEY (id) REFERENCES producto_simple(id)
);

CREATE TABLE IF NOT EXISTS producto_compuesto_producto (
  producto_compuesto_id INT UNSIGNED NOT NULL,
  producto_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (producto_compuesto_id, producto_id),
  FOREIGN KEY (producto_compuesto_id) REFERENCES producto_compuesto(id),
  FOREIGN KEY (producto_id) REFERENCES producto(id)
);
