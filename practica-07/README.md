# Práctica 07 - Mi primera app Android
**Estudiante:** Josue Piña

## Respuestas a la Exploración (Ej. 1)

**16. ¿Qué hace la función setContent {} en MainActivity.kt?**
Define el diseño de la actividad a través de funciones Composable. Es el punto donde se vincula el código de la actividad con la interfaz de usuario de Jetpack Compose, reemplazando el antiguo `setContentView(R.layout.xml)`.

**17. ¿Qué significan minSdk, targetSdk y compileSdk en build.gradle.kts?**
*   **minSdk:** Es la versión mínima de Android requerida para ejecutar la aplicación. Los dispositivos con versiones anteriores no podrán instalarla.
*   **compileSdk:** Es la versión del SDK que utiliza Gradle para compilar la aplicación. Define qué APIs están disponibles para el desarrollador durante la escritura del código.
*   **targetSdk:** Es la versión de Android para la cual se diseñó y probó la aplicación, indicando al sistema que la app es compatible con los comportamientos y políticas de seguridad de esa versión.

**18. ¿Para qué sirve el archivo libs.versions.toml?**
Sirve para centralizar la gestión de dependencias y plugins. Permite declarar las versiones en un solo lugar y referenciarlas de forma organizada en los diferentes archivos `build.gradle.kts` del proyecto (Version Catalog).

**19. ¿Qué anotación convierte una función en un componente de UI en Compose?**
La anotación `@Composable`.
