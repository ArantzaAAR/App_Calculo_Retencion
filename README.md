# 📱 Calculadora de Salario Neto

Una aplicación Android desarrollada en Kotlin con Jetpack Compose que calcula el salario neto anual y mensual.

## 🚀 Características

- **Interfaz moderna** con Material Design 3
- **Navegación fluida** entre pantallas usando Navigation Component
- **Cálculo automático** de retenciones IRPF y deducciones
- **Validación de datos** en tiempo real
- **Diseño responsive** y adaptable

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - Framework de UI moderna
- **Android Studio** - Entorno de desarrollo

## 📋 Funcionalidades

### Pantalla de Formulario
- Campos para ingresar datos personales y laborales
- Validación de entrada de datos
- Cálculo automático al presionar el botón

### Pantalla de Resultados
- Visualización clara de:
  - Salario bruto anual
  - Retención IRPF aplicada
  - Deducciones totales
  - Salario neto anual
  - Salario neto mensual

## 🎨 Decisiones de Diseño

### Uso de Surface en lugar de Box

**Preferí utilizar `Surface` en vez de `Box`** en el diseño de las pantallas por varias razones:

- **Mayor flexibilidad estilística**: `Surface` permite aplicar esquinas redondeadas, elevación y colores de fondo de manera más intuitiva
- **Compatibilidad con Material Design**: Mejor integración con el sistema de diseño Material 3
- **Atributos adicionales**: Capacidad para usar `shadowElevation`, `shape`, y `color` de forma nativa
- **Mejor semántica**: `Surface` representa mejor una "superficie" en el contexto de Material Design

📊 Parámetros de Cálculo
La aplicación considera los siguientes factores para el cálculo:

Salario bruto anual

Número de pagas

Edad del usuario

Grupo profesional

Grado de discapacidad (si aplica)

Estado civil

Número de hijos

🔧 Fórmula de Cálculo
text
Salario Neto = Salario Bruto - Retención IRPF - Deducciones

Retención IRPF = (Salario Bruto × 17%) - (Número de Hijos × 500€)
Deducciones = 1000€ + (Número de Hijos × 200€)

📝 Personalización
Puedes modificar los porcentajes de retención y deducciones en la función calcularSalarioNeto() ubicada en MainActivity.kt.
