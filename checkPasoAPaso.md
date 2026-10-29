# Check paso a paso · Vivero Hojas del Sur

Rama de trabajo: `practica/vivero-guiada`
Regla: después de cada sub-hito se compila (`mvn compile`). Se marca con `[x]` solo lo que ya compila y funciona.

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
- [ ] 2.7 Label de mensajes
- [ ] 2.8 `fx:controller` en el nodo raíz y todos los `fx:id`
- [ ] Cierre: la ventana abre y se ve completa

## Hito 3 · Repository (IntelliJ)
- [ ] 3.1 Clase del repositorio con la lista en memoria
- [ ] 3.2 Leer un archivo JSON con Gson (`TypeToken`, control de `null`, archivo inexistente)
- [ ] 3.3 Validar lo leído
- [ ] 3.4 Cargar `interiores.json` y `exteriores.json` al iniciar
- [ ] 3.5 Agregar con copia de la lista, guardado y luego cambio en memoria
- [ ] 3.6 Eliminar con la misma regla
- [ ] 3.7 Guardar un archivo por tipo
- [ ] Cierre: compila y el repositorio guarda y lee sin la ventana

## Hito 4 · Controlador base y tabla
- [ ] 4.1 Campos `@FXML` enlazados a los `fx:id`
- [ ] 4.2 `initialize()`: opciones del selector y columnas de la tabla
- [ ] 4.3 Lista observable conectada a la tabla
- [ ] 4.4 Habilitar y deshabilitar los datos según el tipo
- [ ] 4.5 Mostrar las plantas cargadas al iniciar
- [ ] Cierre: la app abre con la tabla enlazada y el selector funciona

## Hito 5 · Registrar y validaciones
- [ ] 5.1 Validar tipo, nombre, altura y cantidad
- [ ] 5.2 Validar volumen (interior) y especie (exterior)
- [ ] 5.3 Mensajes de error en la ventana, conservando lo ingresado
- [ ] 5.4 Crear la planta, guardar y actualizar la tabla
- [ ] 5.5 Mensaje de éxito y formulario vacío
- [ ] 5.6 Marcar lista para despacho en el exterior
- [ ] Cierre: se registra una planta válida y una inválida da su aviso

## Hito 6 · Eliminar y archivos
- [ ] 6.1 Eliminar la fila seleccionada
- [ ] 6.2 Aviso si no hay selección, sin cambios
- [ ] 6.3 Guardado automático al registrar y al eliminar
- [ ] 6.4 Error de lectura o guardado informado en la ventana
- [ ] Cierre: tabla y archivos coinciden en todo momento

## Hito 7 · Verificación con los datos de prueba
- [ ] 7.1 Registrar Menta (interior), Helecho, Menta (exterior) y Rosal con sus precios
- [ ] 7.2 Eliminar sin selección muestra el aviso
- [ ] 7.3 Altura `abc` y cantidad `0` muestran el aviso y conservan los valores
- [ ] 7.4 Cerrar y reabrir: las 4 plantas, y la Menta de exterior sigue lista para despacho
- [ ] 7.5 Eliminar Rosal, cerrar y reabrir: ya no aparece
- [ ] 7.6 Resumen de lo aprendido y errores que más cuestan
- [ ] 7.7 Commit y push (solo cuando yo lo apruebe)
