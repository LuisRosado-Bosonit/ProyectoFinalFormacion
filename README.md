
# Aplicación final de formación backend

La aplicación desarrollada es una app de reserva de viajes por
autobús, podemos elegir entre 4 destinos, como son: Madrid, 
Valencia, Bilbao o Barcelona; Además de 4 horarios distintos 
como son : 08,12,16,20.

La aplicación principal se compone de 4 aplicaciones o 
de microservicios, donde encontramos: 

    - Kafka con zookeeper para el paso de mensajes asíncronos 
    apps.
    - Eureka Server para la identificación de las distintas instancias.
    - BackWeb donde se reciben las reservas desde el front.
    - BackEmpresa donde se reciben las reservas desde backweb o desde 
    el front también si se quisiese, se envían los correos, se registran
    y logean los usuarios...etc

Cuando se realiza una reserva desde el backweb, esta comprueba primero en
local si es posible y si hay plazas disponibles, una vez hecho esto, la envía
a través de kafka a backempresa, donde se comprueba de nuevo con la base de datos
si hay disponibilidad, si hay averías y demás. Finalmente se envía un correo al 
usuario, haya disponibilidad o no, informando del estado de la reserva.
## BackWeb

### Endpoints

    
## BackEmpresa

### Endpoints
    Nombre: register
    Ruta de Acceso:POST /api/v0/usuario/register
    Parámetros: inputUsuarioDTO
    Descripción: Recibe un usuario y lo guarda en la base de datos
    Respuesta esperada:
        Caso esperado: http 200 : Se ha registrado el usuario correctamente
        Caso de error: http 501 : ErrorDTO



    Nombre: login
    Ruta de Acceso:POST /api/v0/usuario/login
    Parámetros: @RequestParam user, @RequestParam password
    Descripción: Recibe un usuario y contraseña y devuelve un token asociado al rol correspondiente
    Respuesta esperada:
        Caso esperado: http 200 : Token
        Caso de error: http 501 : ErrorDTO
    


    Nombre: realizarReserva
    Ruta de Acceso:POST /api/v0/reserva
    Parámetros: inputReservaDTO
    Descripción: Recibe un usuario y contraseña y devuelve un token asociado al rol correspondiente
    Respuesta esperada:
        Caso esperado: http 200 : La reserva se ha añadido
         satisfactoriamente(Y envía el correo de confirmación)
        Caso de error: http 503 : ErrorDTO



    Nombre: consultarReservas
    Ruta de Acceso:POST /api/v0/reservas/consultarOcupadas
    Parámetros: @RequestParam Fecha, @RequestParam Hora, @RequestParam Destino
    Descripción: Recibe una fecha, una hora y una ciudad de destino y devuelve
     las plazas ocupadas para el mismo
    Respuesta esperada:
        Caso esperado: http 200 : El número de plzas ocupadas
        Caso de error: http 406 : ErrorDTO



    Nombre: plazasLibres
    Ruta de Acceso:GET /api/v0/autobus
    Parámetros: @RequestParam id
    Descripción: Recibe el ID de un autobus y comprueba las plazas libres
    Respuesta esperada:
        Caso esperado: http 200 : El número de plzas disponibles
        Caso de error: http 503 : ErrorDTO
