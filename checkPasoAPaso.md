# Check paso a paso · Vivero Hojas del Sur

Rama de trabajo: `practica/vivero-guiada`
Regla: después de cada sub-hito se ejecuta (`Launcher`). Se marca con `[x]` solo lo que ya corre y funciona.

## Hito 0 · Lectura y rama
- [x] 0.1 Repo `poo_vivero_ep2` agregado a la sesión
- [x] 0.2 Leídos `ENUNCIADO.md`, `LEEME.txt`, `README.md` y `pom.xml`
- [x] 0.3 Rama `practica/vivero-guiada` creada desde `main` y subida
- [x] 0.4 Rama traída a IntelliJ (`git fetch origin` + `git checkout practica/vivero-guiada`)
- [x] 0.5 Plan de hitos aprobado (por Claude, por delegación de Nicolás)

## Hito 1 · Configuración (IntelliJ)
- [x] 1.1 Dependencia de Gson en `pom.xml`
- [x] 1.2 Recargar Maven y comprobar que compila
- [x] 1.3 Crear la estructura de paquetes: `controller`, `repository`
- [x] 1.4 Crear `module-info.java` (requires y opens)
- [x] 1.5 `mainClass` con `module-info.java`: funciona sin nombre de módulo (el plugin lo deduce y solo avisa)
- [x] 1.6 Compila con módulo y Gson
- [x] Cierre: compila y la app arranca

## Hito 2 · Vista (Scene Builder)
- [x] 2.1 Crear `main-view.fxml` en `src/main/resources/cl/duoc/vivero/view/`
- [x] 2.2 Formulario: selector de tipo, nombre, altura, cantidad
- [x] 2.3 Datos de interior (volumen) y de exterior (especie, heladas, despacho)
- [x] 2.4 Botón Registrar con `onAction`
- [x] 2.5 Tabla con 5 columnas: Tipo, Nombre, Altura (cm), Cantidad, Precio unitario
- [x] 2.6 Botón Eliminar con `onAction`
- [x] 2.7 Label de mensajes
- [x] 2.8 `fx:controller` en el nodo raíz y todos los `fx:id`
- [x] Cierre: la ventana abre y se ve completa

## Hito 3 · Repository (IntelliJ)
- [x] 3.1 Clase del repositorio con la lista en memoria
- [x] 3.2 Leer un archivo JSON con Gson (`TypeToken`, control de `null`, archivo inexistente)
- [x] 3.3 Validar lo leído
- [x] 3.4 Cargar `interiores.json` y `exteriores.json` al iniciar
- [x] 3.5 Escribir un archivo JSON con Gson (`try` con recursos y `toJson`)
- [x] 3.6 Guardar un archivo por tipo (separar interiores y exteriores)
- [x] 3.7 Agregar y eliminar con copia de la lista, guardado y luego cambio en memoria
- [x] Cierre: compila y el repositorio guarda y lee sin la ventana (probado en la copia de Claude; en tu PC se prueba de punta a punta en el hito 7)

## Hito 4 · Controlador base y tabla
- [x] 4.1 Campos `@FXML` enlazados a los `fx:id`
- [x] 4.2 `initialize()`: opciones del selector y columnas de la tabla
- [x] 4.3 Lista observable conectada a la tabla
- [x] 4.4 Habilitar y deshabilitar (y mostrar u ocultar) los datos según el tipo
- [x] 4.5 Mostrar las plantas cargadas al iniciar
- [x] Cierre: la app abre con la tabla enlazada y el selector funciona

## Hito 5 · Registrar y validaciones
- [x] 5.1 Validar tipo, nombre, altura y cantidad
- [x] 5.2 Validar volumen (interior) y especie (exterior)
- [x] 5.3 Mensajes de error en la ventana, conservando lo ingresado
- [x] 5.4 Crear la planta, guardar y actualizar la tabla
- [x] 5.5 Mensaje de éxito y formulario vacío
- [x] 5.6 Marcar lista para despacho en el exterior
- [x] Cierre: se registra una planta válida y una inválida da su aviso

## Hito 6 · Eliminar y archivos
- [x] 6.1 Eliminar la fila seleccionada
- [x] 6.2 Aviso si no hay selección, sin cambios
- [x] 6.3 Guardado automático al registrar y al eliminar
- [x] 6.4 Error de lectura o guardado informado en la ventana
- [x] Cierre: tabla y archivos coinciden en todo momento

## Hito 7 · Verificación con los datos de prueba
- [x] 7.1 Registrar Menta (interior), Helecho, Menta (exterior) y Rosal con sus precios
- [ ] 7.2 Eliminar sin selección muestra el aviso
- [ ] 7.3 Altura `abc` y cantidad `0` muestran el aviso y conservan los valores
- [ ] 7.4 Cerrar y reabrir: las 4 plantas, y la Menta de exterior sigue lista para despacho
- [ ] 7.5 Eliminar Rosal, cerrar y reabrir: ya no aparece
- [ ] 7.6 Resumen de lo aprendido y errores que más cuestan
- [ ] 7.7 Commit y push (solo cuando yo lo apruebe)
