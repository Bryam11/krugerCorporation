# ApiVaccination

Api creada para manejar el control de vacunacion de una empresa empezando con creacion de
empleados y sus respectivos roles.

## Arquitectura 📋

- Se esta siguiendo el patron de arquitectura de software MVC
- Se tiene una capa de controladores que se encargan de recibir las peticiones y enviarlas a la capa de servicios
- La capa de servicios se encarga de realizar las operaciones necesarias y enviarlas a la capa de repositorios
- La capa de repositorios se encarga de realizar las operaciones necesarias con la base de datos y enviarlas a la capa
  de controladores
- Un solo paquete de configuracion de Seguridad para la autenticacion de los usuarios y la generacion de los tokens
- Un solo paquete de configuracion de Swagger para la documentacion de los endpoints

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos
de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos 📋

_**- Maven**_
_**- Postgres**_
_**- Java SDK 17**_

### Instalación 🔧

_Para poder correr el proyecto primero debemos descargar todas las dependecias necesarias_

_Una vez hecho corremos el proyecto: run_

_Y listo podemos conectarnos al swagger para ver le funcionamiento._

```
Una vez se tenga levantado el proyecto se puede acceder 
al swagger
```

[Swagger](http://localhost:8080/swagger-ui/) 👈👈

## Ejecutando las pruebas ⚙️

_Para poder ejecutar las pruebas se debe tener instalado el plugin de maven en el IDE_

al momento de probar en el swagger se debe tener en cuenta que el token de autenticacion
debe ingresarse de la siguiente manera:

- Bearer + token

## Construido con 🛠️

| Base de datos | BACKEND         |
|---------------|-----------------|
| PostgreSQL    | Spring          |
|               | Apache Maven    | 
|               | swagger         |
|               | JWT             |
|               | Spring-Security |

## Tabla de roles 📋

| Rol      | Descripcion                                                                                                                  |                             
|----------|------------------------------------------------------------------------------------------------------------------------------|
| ADMIN    | Administrador de la empresa.<br/>este rol <br/>**crea** <br/>**visualiza**<br/>**elimina** <br/>los empleados                |                             
| EMPLOYEE | Empleado de la empresa con este rol sole se<br/>puede actualizar la informacion del empleado<br/>y visualizar su informacion |                             

## Informacion de los endpoints 📋

| Endpoint                                              | Descripcion                                                         | Rol   |
|-------------------------------------------------------|---------------------------------------------------------------------|-------|
| /api/employee/nuevo                                   | Crea un empleado                                                    | ADMIN |
| /api/employee/deleteEmployeeByCedula/{Cedula}         | Elimina un empleado                                                 | ADMIN |
| /api/employee/listAllEmployee                         | Visualiza todos los empleados                                       | ADMIN |
| /api/employee/listAllEmployeeDelete                   | Visualiza todos los empleados eliminados                            | ADMIN |
| /api/vaccinationReport/getEmployeeByStatusVaccination | Lista los empleados por estado de vacunacion (Vacunado No Vacunado) | ADMIN |
| /api/vaccinationReport/getEmployeeByTypeVaccine       | Lista los empleados por tipo de vacuna                              | ADMIN |
| /api/vaccinationReport/getEmployeeByVaccinationDate   | Lista todos los empleados vacunados por un rango de fecha           | ADMIN |
| **auth**                                              | Logueo en el servicio                                               | All   |
| /api/employee/update                                  | Logueo en el servicio                                               | All   |

## Manejo de Usuarios 👨‍💻

```
_Breve explicacion sobre como se generan los usuarios._

Al momento que se levanta el servicio por primera vez se crean un
usuario por defecto que tiene las siguentes credenciales

- Usuario: admin
- Contraseña: admin

```

Al momento que el admin registra un nuevo empleado
se le asigna el rol de empleado y se le genera
un usuario y contraseña

- [x] Usuario: la primera letra del nombre mas el apellido paterno
- [x] Contraseña: la primera letra del nombre mas la cedula
  ejemplo:
- Nombres: Juan Antonio
- Apellidos: Perez Machado
- Cedula: 123456789

- Usuario: JPerez
- Contraseña: J123456789

## Versionado 📌

Usamos [Git](github.com) para el versionado. esta es la primera version, mira
las [versiones](https://github.com/Bryam11).

## Autores ✒️

* **Bryam Xavier ChuchucaGuzman** - *Developer* - xavierchuchuca18@gmail.com


