# Proyecto: API para Gestión de Médicos

Este proyecto es una API desarrollada en **Java** utilizando el framework **Spring Boot**. Su propósito es administrar información sobre médicos, permitiendo registrar, listar, actualizar y eliminar registros. Además, se implementa un sistema de eliminación lógica para mantener la integridad de los datos.

---

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL** 
- **Lombok**
- **Flyway** (para el manejo de migraciones)
- **Hibernate Validator**

---

## Funcionalidades

### 1. Registrar Médico
**Endpoint:** `POST /medicos`  
Permite registrar un nuevo médico en la base de datos.

**Request Body:**
```json
{
  "nombre": "Pedro D.",
  "email": "pedro.d@voll.med",
  "telefono": "2821849",
  "documento": "666666",
  "especialidad": "ORTOPEDIA",
  "direccion": {
    "calle": "calle 1",
    "distrito": "distrito 1",
    "ciudad": "Lima",
    "numero": "1",
    "complemento": "a"
  }
}
```

---

### 2. Listado de Médicos Activos
**Endpoint:** `GET /medicos`  
Retorna un listado paginado de médicos que estén activos.

**Parámetros opcionales:**
- `size`: Tamaño de la página (por defecto 5)
- `page`: Número de página (por defecto 0)

Ejemplo de Respuesta:
```json
{
  "content": [
    {
      "nombre": "Pedro D.",
      "especialidad": "ORTOPEDIA",
      "documento": "666666",
      "email": "pedro.d@voll.med"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  }
}
```

---

### 3. Actualizar Información de un Médico
**Endpoint:** `PUT /medicos`  
Actualiza los datos de un médico existente.

**Request Body:**
```json
{
  "id": 1,
  "nombre": "Pedro Diaz",
  "telefono": "123456789"
}
```

---

### 4. Eliminar Médico
#### Eliminación Lógica
**Endpoint:** `DELETE /medicos/{id}`  
Desactiva un médico, estableciendo el campo `activo` en `false`.

#### Eliminación Física
Esta funcionalidad está comentada en el código fuente para su uso opcional. Si se desea habilitar, descomentar el método correspondiente.

---

## Estructura del Proyecto

### **Entidades**
1. **Medico**:
   - `id`: Identificador del médico.
   - `nombre`: Nombre del médico.
   - `email`: Correo electrónico.
   - `telefono`: Teléfono de contacto.
   - `documento`: Documento de identificación.
   - `especialidad`: Especialidad del médico (enum).
   - `direccion`: Dirección del médico (relación embebida).
   - `activo`: Indicador de estado (activo/inactivo).

2. **Direccion**:
   - `calle`: Nombre de la calle.
   - `distrito`: Distrito.
   - `ciudad`: Ciudad.
   - `numero`: Número de la dirección.
   - `complemento`: Información adicional de la dirección.

### **DTOs**
- **DatosRegistroMedico**: Datos necesarios para registrar un médico.
- **DatosListadoMedico**: Datos resumidos para listar médicos.
- **DatosActualizarMedico**: Datos necesarios para actualizar un médico.

### **Controladores**
- **MedicoController**: Controla las operaciones CRUD relacionadas con los médicos.

### **Repositorios**
- **MedicoRepository**: Extiende `JpaRepository` y define métodos personalizados como `findByActivoTrue`.

---

## Ejecución

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd <nombre-del-proyecto>
   ```

2. **Ejecutar la aplicación**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **URL**
   ```end point "medicos"
   http://localhost:8080/medicos
   ```

---

## Notas
- El uso de Lombok simplifica la generación de código repetitivo como getters, setters y constructores.

**Desarrollador:** Pedro David Carassa Torres
