# Proyecto de juego: Piedra, Papel o Tijera

Este proyecto implementa Programación Orientada a Objetos (POO) para realizar un juego interactivo contra la computadora, donde tiene varios turnos y los movimientos tienen enfriamiento (15 aprox). 
Puede usar 6 tipos de movimientos diferentes agregando 3 movimientos innovadores, los cuales son: **Nebulosa**, **Fuego**, **Agua**.

---

## Tecnologías utilizadas

| Capa | Herramientas |
|------|---------------|
| **Frontend** | Framework de UI JavaFX |
| **Backend** | Java JDK 21 |
| **Entorno de desarrollo** | Visual Studio Code |

---

## Funcionalidades principales

- **Renderizado de Interfaz Dinámica:** Carga de vistas mediante archivos FXML y actualización en tiempo real de componentes `ImageView` con recursos locales desde `/images/`.
- **Gestión de Entidades (POO):** Control individual del estado, nombres, elecciones actuales y puntajes acumulados tanto del jugador humano como de la máquina.
- **Selección Aleatoria de la IA:** Generación automatizada y equilibrada del movimiento de la computadora utilizando la biblioteca `Random` de Java.
. **Árbitro de Partidas (Lógica de Juego):** Evaluación algorítmica instantánea de las jugadas para determinar el resultado de la ronda (Victoria, Derrota o Empate).
- **Sistema de Puntuación Local:** Contador de victorias persistente durante la sesión de juego para identificar al ganador definitivo.

---

## Estructura del proyecto

```
📂 Juego_Piedra_Papel_o_Tijera/
│
├── 📂 images/
│   ├── 📄 piedra-jpg
│   ├── 📄 papel.jpg
│   ├── 📄 tijera.jpg
│   ├── 📄 fuego.jpg
│   ├── 📄 agua.jpg
│   └── 📄 nebulosa.jpg
│
├── 🖥️ GameFrame.java
├── 🚀 Main.java
├── 👤 Player.java
├── 📊 ResultLogic.java
├── ⚙️ Move.java
└── 📝 README.md
```
---

## Instalación y ejecución

1. **Clona el repositorio**
   ```bash
   git clone https://github.com/cruzzdf/Juego_Piedra_Papel_o_Tijera.git
   cd Juego_Piedra_Papel_o_Tijera
   
2. **Entra a tu entorno de desarrollo**
   Con la ruta puesta en el archivo "Juego_Piedra_Papel_o_Tijera
    ```bash
    code .

3. **Ejecuta el programa con "run"**
4. **Diviertete**
   
---

## Autor
- Cruzzdf (SWE) 
- Estudiante del Politécnico Grancolombiano  
- Proyecto académico de construcción de software.

---

## Licencia
**Este proyecto se distribuye bajo la licencia MIT.
Puedes usarlo, modificarlo y compartirlo libremente con atribución.**