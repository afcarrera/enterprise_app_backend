# EnterpriseControl
***

## Información
***
Control de empresas.
## Tecnologías
***
* [Java](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html): Version 11
* [Spring Boot](https://spring.io/projects/spring-boot): Version 2.7.8
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/): Ultimate 2022.3
* [MySQL](https://dev.mysql.com/downloads/mysql/): Version 5.5.5-10.1.38-MariaDB
* [Git](https://mirrors.edge.kernel.org/pub/software/scm/git/): Version 2.39.1.windows.1

## Instalación
***
Comandos recomendados para instalación en plataformas Windows. 
```
> git clone https://github.com/afcarrera/enterprise_app_backend
> cd enterprise_app_backend
> cd database
> mysql -u root -p <CREATE_DATABASE.sql
> mysql -u root -p <CREATE_TABLES.sql
> cd..
> cd enterpriseControl
> mvnw spring-boot:run 
```

## Uso
***
La aplicación responde a lo planteado por las preguntas de sicpa con sus respectivas validaciones, su documentación se encuentra en [http://localhost:9000/enterprise-control/swagger-ui.html](#)

El detalle de los endpoint es el siguiente.
### CONTEXT
[/enterprise-control](#)

### POST
[/api/v1/enterprise](#)   
**Request**
```
{
    "name" :"SICPA",
    "phone" :"1234567890",
    "address" :"CALLE 123"
}
```
[/api/v1/department](#)   
**Request**
```
{
    "name" : "TECNOLOGÍA",
    "description" : "Departamento de tecnología",
    "idEnterprise" : "39a9546b-6e4e-4ffe-9535-ebdc01d17ca5",
    "phone" :"1234567890"
}
```
[/api/v1/employee](#)   
**Request**
```
{
    "name" :"Alberto",
    "surname" :"Carrera",
    "email":"acarrera@mail.com"
    "age" : 55,
    "position" : "ING"
}
```
### PATCH
[/api/v1/enterprise/{enterpriseId}](#)   
**Request**
```
{
    "name" :"SICPA",
    "phone" :"1234567890",
    "address" :"CALLE 123"
}
```
[/api/v1/department/{departmentId}](#)   
**Request**
```
{
    "name" : "TECNOLOGÍA",
    "description" : "Departamento de tecnología",
    "idEnterprise" : "39a9546b-6e4e-4ffe-9535-ebdc01d17ca5",
    "phone" :"1234567890"    
}
```
[/api/v1/employee/{employeeId}](#)   
**Request**
```
{
    "name" :"Alberto",
    "surname" :"Carrera",
    "email":"acarrera@mail.com"
    "age" : 55,
    "position" : "CEO"
}
```
### GET ALL
[/api/v1/enterprise](#)

[/api/v1/department](#)

[/api/v1/employee](#)
### GET BY ID
[/enterprise/{enterpriseId}](#) 

[/api/v1/department/{departmentId}](#) 

[/api/v1/employee/{employeeId}](#) 
