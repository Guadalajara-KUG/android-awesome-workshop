# DOMAIN LAYER

* Es responsable de encapsular la logica de negocio compleja o reutilizable.
* Esta capa es opcional dado que no todas las aplicaciones tienen estos requerimientos y se encuentre entre la UI y Data layer.
* Esta capa tiene los siguientes beneficios:
  * Evita la duplicacion de codigo
  * Mejora la legibilidad en las clases que utilizan clases de la Domain layer.
  * Mejora la testeabilidad
  * Ayuda a reducir la carga de las clases , repartiendo la responsabilidad.
* Para mantener estas clases simples y legibles se recomiendan que cada UseCase tenga una sola responsabilidad y no contengan datos mutables. Estos datos mutables deben ser manejados en su UI o Data layer.

## NOTE:
    Esta capa de dominio es bajo la definicion de la arquitectura de android, no sobre la "arquitectura limpia"
