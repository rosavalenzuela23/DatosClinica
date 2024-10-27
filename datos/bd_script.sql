DROP DATABASE IF EXISTS conecta_contigo_consultorio;
Create database conecta_contigo_consultorio;
use conecta_contigo_consultorio;

create table empleados(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(70),
    contrasenia VARCHAR(255)
);

create table psicologos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
);

create table pacientes(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100),
    apellido_paterno VARCHAR(100),
    apellido_materno VARCHAR(100),
    vivienda ENUM('LOCAL', 'FORANEO'),
    escolaridad VARCHAR(255),
    estado_civil ENUM('CASADO', 'SOLTERO', 'VIUDO', 'DIVORCIADO', 'UNIONLIBRE'),
	fecha_nacimiento DATE,
    telefono VARCHAR(100),
    telefono_emergencia VARCHAR(100)
);

create table pacientes_psicologos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente int,
    id_psicologo int,
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id),
    FOREIGN KEY (id_psicologo) REFERENCES psicologos(id)
);

create table recepcionistas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
);

create table administradores(
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_psicologo INT,
    FOREIGN KEY(id_psicologo) REFERENCES psicologos(id)
);

create table expediente_paciente(
	id INT AUTO_INCREMENT PRIMARY KEY,
	motivo_consulta VARCHAR(2000),
    antecedentes VARCHAR(2000),
    deseo varchar(2000),
    enfermedad_previa VARCHAR(2000),
    pregunta_magica VARCHAR(2000),
    diagnostico VARCHAR(2000),
    id_paciente INT,
    FOREIGN KEY(id_paciente) REFERENCES pacientes(id)
);


create table cartas_consentimiento(
	id INT AUTO_INCREMENT PRIMARY KEY,
	ruta_archivo varchar(300),
    id_paciente INT,
    FOREIGN KEY(id_paciente) REFERENCES pacientes(id)
);

create table integrantes_hogar(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200),
    parentesco VARCHAR(100),
    status_relacion VARCHAR(255),
    ocupacion_escolaridad VARCHAR(400),
    fecha_nacimiento DATE,
	id_expediente INT,
    FOREIGN KEY(id_expediente) REFERENCES expediente_paciente(id)
);

create table medicamentos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(400)
);

create table medicamento_del_expediente(
	id INT AUTO_INCREMENT PRIMARY KEY,
    dosis VARCHAR(255),
    frecuencia VARCHAR(255),
    id_expediente INT,
    id_medicamento INT,
    FOREIGN KEY(id_expediente) REFERENCES expediente_paciente(id),
    FOREIGN KEY(id_medicamento) REFERENCES medicamentos(id)
);

create table familiares_confianza(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(200),
    parentesco VARCHAR(100),
    telefono VARCHAR(100),
    id_expediente INT,
    FOREIGN KEY(id_expediente) REFERENCES expediente_paciente(id)

);

create table sesiones(
	id INT AUTO_INCREMENT PRIMARY KEY,
	numerosesion INT,
    comentario_psicologa varchar(1000),
    fecha_hora DATETIME,
    puntuacion_vestimenta TINYINT,
    puntuacion_bienestar TINYINT,
    puntuacion_arreglo_personal TINYINT,
    puntuacion_postura TINYINT,
    puntuacion_contacto_visual TINYINT,
    puntuacion_habla TINYINT,
    puntuacion_velocidad_habla TINYINT,
    puntuacion_volumen_habla TINYINT,
    puntuacion_articulacion TINYINT,
    puntuacion_coherencia TINYINT,
    puntuacion_espontaneidad TINYINT,
	id_psicologo INT,
    id_expediente INT,
    FOREIGN KEY (id_psicologo) REFERENCES psicologos(id),
	FOREIGN KEY (id_expediente) REFERENCES expediente_paciente(id)
);

create table comentarios_sesion(
	id INT AUTO_INCREMENT PRIMARY KEY,
    aspecto_a_medir VARCHAR(255),
	valoracion_inicio INT,
    valoracion_fin INT,
    id_sesion INT,
    FOREIGN KEY (id_sesion) REFERENCES sesiones(id)
);

create table problemas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    afectacion_salud TINYINT,
    afectacion_familiar TINYINT,
    afectacion_amigos TINYINT,
    afectacion_espiritual TINYINT,
    afectacion_laboral TINYINT,
    afectacion_pareja TINYINT,
    afectacion_economico TINYINT,
    Intensidad INT,
    frecuencia VARCHAR(255),
    descripcion VARCHAR(255),
    id_sesion INT,
	FOREIGN KEY(id_sesion) REFERENCES sesiones(id)
);

create table instrumentos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(300),
    ruta_archivo_preguntas VARCHAR(300),
    id_psicologo INT,
    FOREIGN KEY (id_psicologo) REFERENCES psicologos(id)
);

create table evaluacion_instrumento(
	id INT AUTO_INCREMENT PRIMARY KEY,
    valoracion VARCHAR(300),
    ruta_archivo VARCHAR(300),
	id_psicologo INT,
    id_instrumento INT,
    id_expediente INT,
    FOREIGN KEY (id_psicologo) REFERENCES psicologos(id),
    FOREIGN KEY (id_instrumento) REFERENCES instrumentos(id),
    FOREIGN KEY (id_expediente) REFERENCES expediente_paciente(id)
);