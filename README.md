# Mesa de Ayuda 2.0

Mesa de Ayuda 2.0 es una aplicación de soporte técnico que permite a los técnicos gestionar los casos asignados, con funcionalidades como ver, modificar y actualizar el estado de los casos. La aplicación se implementa utilizando **Jetpack Compose** y **MVVM** en un proyecto de Android.

## Funcionalidades

1. **Gestión de Casos**: Los técnicos pueden ver una lista de casos, que incluyen detalles como el ID del caso, la descripción y el estado (Abierto, Pendiente, Resuelto).
2. **Cambio de Estado**: Los técnicos pueden cambiar el estado de un caso a "Resuelto" para indicar que el caso ha sido atendido.
3. **Pantalla de Casos**: La aplicación tiene una pantalla dedicada para ver los casos que están asignados al técnico.

Estructura 
 - app/
  - src/
    - main/
      - java/
        - com.example.mesadeayuda2/
          - entidades/          # Contiene las entidades (modelos) como Case.
          - screens/            # Contiene las pantallas de la app (UI).
          - viewmodel/          # Contiene los ViewModels, como TechnicianCasesViewModel.
      - res/
        - layout/              # Archivos XML de layout (si los hay).
        - values/              # Archivos de recursos como colores y cadenas.


## Requisitos

- **Android Studio** con soporte para **Jetpack Compose**.
- **Kotlin** (el lenguaje utilizado en el proyecto).
- **ViewModel** y **StateFlow** para la gestión de datos.
- 

## Instalación

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

### 1. Clona el repositorio

```bash


git clone https://github.com/tuusuario/mesadeayuda2.git
