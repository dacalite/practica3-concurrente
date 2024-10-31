# 🧙‍♂️ Ministerio de Magia - Sistema de Gestión Mágico

Este repositorio contiene el código para el **Sistema de Gestión Mágico** del Ministerio de Magia, desarrollado con **Spring Boot** y **Programación Orientada a Aspectos (AOP)**. El sistema permite gestionar hechizos y eventos mágicos, aplicando aspectos transversales como seguridad, auditoría y gestión de transacciones, manteniendo el rendimiento y la modularidad del sistema.

## 📋 Descripción

El Ministerio de Magia está implementando un sistema avanzado de gestión de eventos mágicos para procesar, auditar y asegurar las actividades mágicas. Este sistema utiliza AOP para aplicar preocupaciones transversales como **seguridad** y **auditoría** de forma transparente, sin afectar el núcleo del código de negocio. Esto permite una arquitectura modular y escalable, donde los aspectos de seguridad y registro son fácilmente integrables.

Además, se han implementado **tokens JWT** para la autenticación y manejo de sesiones de usuario, permitiendo controlar el acceso y gestionar sesiones expirables de manera segura.

## 🎥 Demo del Proyecto



https://github.com/user-attachments/assets/f2d40854-4195-488f-aa4b-a8a90d8e8d35



## 🏗️ Arquitectura General

La arquitectura del sistema está diseñada para maximizar la eficiencia, la modularidad y la seguridad. Incluye:

- **Gestión de Hechizos**: Beans para cada tipo de hechizo, controlados por IoC, asegurando un ciclo de vida bien gestionado.
- **AOP con Spring**: Aspectos para seguridad, auditoría y transacciones aplicados mediante anotaciones como `@Aspect`, `@Before`, `@After` y `@Around`.
- **Control de Acceso y Autorización**: Configuración de **Spring Security** para la autenticación y definición de roles y permisos.
- **Registro y Auditoría de Eventos Mágicos**: Sistema de auditoría para mantener registros de eventos mágicos, con almacenamiento eficiente de logs.
- **Monitorización del Sistema**: Configuración de **Spring Actuator** para monitorear el estado del sistema y registrar eventos.

### Tecnologías clave:

- **Spring Boot**: Framework principal que permite una fácil integración de AOP y Spring Security.
- **JWT (JSON Web Tokens)**: Para autenticar y gestionar las sesiones de los usuarios, proporcionando expiración automática.
- **Spring Actuator**: Herramienta para monitorear y auditar el estado del sistema y la salud de los servicios.

## 🚀 Instalación y Ejecución

Sigue estos pasos para levantar el sistema completo:

### 1. Clonar el repositorio:

```bash
git clone https://github.com/dacalite/practica3-concurrente
cd practica3-concurrente
```

### 2. Compilar el packete de Java

```bash
cd backend
./mvnw package
```

### 2. Levantar los contenedores con docker compose

```bash
cd ..
docker compose up
```

### 2. Levantar los contenedores con docker compose

Acceder en un navegador a la aplicación en http://localhost:5173
