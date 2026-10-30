package cl.duoc.vivero.controller;
//HERMANOS
import cl.duoc.vivero.model.*;
import cl.duoc.vivero.repository.PlantaRepository;

// IMPORTS NACIDOS DEL BUILDER
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

//?
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;

public class MainController {
    // ATRIBUTOS DEL BUILDER
    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtAltura;
    @FXML private TextField txtCantidad;

    @FXML private HBox boxInterior;
    @FXML private TextField txtVolumen;

    @FXML private HBox boxExterior;
    @FXML private TextField txtEspecie;
    @FXML private CheckBox chkHeladas;
    @FXML private CheckBox chkDespacho;

    @FXML private TableView<Planta> tblPlantas;
    @FXML private TableColumn<Planta, String> colTipo;
    @FXML private TableColumn<Planta, String> colNombre;
    @FXML private TableColumn<Planta, Integer> colAltura;
    @FXML private TableColumn<Planta, Integer> colCantidad;
    @FXML private TableColumn<Planta, String> colPrecio;

    @FXML private Label lblMensaje;

    // ATRIBUTOS POSTERIORES
    private final PlantaRepository repositorio = new PlantaRepository();
    private final ObservableList<Planta> plantasTabla = FXCollections.observableArrayList();





    @FXML
    private void initialize() {
        cmbTipo.getItems().addAll("Interior", "Exterior");                                                                                    //Le agrego los valores al cmbTipo

        colTipo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getTipo()));
        colNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
        colAltura.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getAlturaCm()));
        colCantidad.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getCantidad()));
        colPrecio.setCellValueFactory(fila -> new SimpleStringProperty(Planta.pesos(fila.getValue().calcularPrecio())));

        cmbTipo.valueProperty().addListener(((observable, anterior, nuevo) -> actualizarCamposPorTipo()));
        actualizarCamposPorTipo();

        tblPlantas.setItems(plantasTabla);

        try {
            repositorio.cargar();
            sincronizarTabla();
        } catch (IOException e){
            lblMensaje.setText(e.getMessage());
        }
    }


    private void actualizarCamposPorTipo(){
        String tipo = cmbTipo.getValue();
        boolean esInterior = "Interior".equals(tipo);
        boolean esExterior = "Exterior".equals(tipo);

        boxInterior.setDisable(!esInterior);
        boxExterior.setDisable(!esExterior);

        //Opcionales para ocultar
        boxInterior.setVisible(esInterior);
        boxInterior.setManaged(esInterior);
        boxExterior.setVisible(esExterior);
        boxExterior.setManaged(esExterior);
    }



    private void sincronizarTabla() {
        plantasTabla.setAll(repositorio.listar());
    }

    @FXML
    private void onRegistrar() {
        // Parte 01
        String tipo = cmbTipo.getValue();
        if (tipo == null) {
            lblMensaje.setText("Debe seleccionar el tipo de planta.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            lblMensaje.setText("El nombre no puede estar vacío.");
            return;
        }

        Integer altura = leerEntero(txtAltura, "altura", 1, 300);
        if (altura == null) {
            return;
        }

        Integer cantidad = leerEntero(txtCantidad, "cantidad", 1, 500);
        if (cantidad == null) {
            return;
        }



        // Parte 02
        Double volumen = null;
        String especie = null;

        if ("Interior".equals(tipo)) {
            volumen = leerVolumen();
            if (volumen == null) {
                return;
            }
        } else {
            especie = txtEspecie.getText().trim();
            if (especie.isEmpty()) {
                lblMensaje.setText("La especie no puede estar vacía.");
                return;
            }
        }

        // Parte 03
        Planta planta;
        if ("Interior".equals(tipo)) {
            planta = new PlantaInterior(nombre, altura, cantidad, volumen);
        } else {
            //Tip importantisimo de variable intermedia para evitar casteo mediante exterior.
            PlantaExterior exterior = new PlantaExterior(nombre, altura, cantidad, especie, chkHeladas.isSelected());
            if (chkDespacho.isSelected()) {
                exterior.marcarListaParaDespacho();
            }
            planta = exterior;
        }

        try {
            repositorio.agregar(planta);
            sincronizarTabla();
            lblMensaje.setText(planta.getNombre() + " (" + planta.getTipo() + ") registrada correctamente.");
            limpiarFormulario();
        } catch (IOException e) {
            lblMensaje.setText(e.getMessage());
        }
    }

    private Integer leerEntero(TextField campo, String nombreCampo, int minimo, int maximo) {
        int valor;
        try {
            valor = Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            lblMensaje.setText("La " + nombreCampo + " debe ser un número entero.");
            return null;
        }
        if (valor < minimo || valor > maximo) {
            lblMensaje.setText("La " + nombreCampo + " debe estar entre " + minimo + " y " + maximo + ".");
            return null;
        }
        return valor;
    }

    private Double leerVolumen() {
        double valor;
        try {
            valor = Double.parseDouble(txtVolumen.getText().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            lblMensaje.setText("El volumen de la maceta debe ser un número.");
            return null;
        }
        if (!Double.isFinite(valor) || valor <= 0) {
            lblMensaje.setText("El volumen de la maceta debe ser mayor que cero.");
            return null;
        }
        return valor;
    }

    private void limpiarFormulario() {
        cmbTipo.setValue(null);
        txtNombre.clear();
        txtAltura.clear();
        txtCantidad.clear();
        txtVolumen.clear();
        txtEspecie.clear();
        chkHeladas.setSelected(false);
        chkDespacho.setSelected(false);
    }

    @FXML
    private void onEliminar() {
        Planta seleccionada = tblPlantas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            lblMensaje.setText("Debe seleccionar una planta de la tabla para eliminarla.");
            return;
        }

        try {
            repositorio.eliminar(seleccionada);
            sincronizarTabla();
            lblMensaje.setText(seleccionada.getNombre() + " (" + seleccionada.getTipo() + ") eliminada correctamente.");
        } catch (IOException e) {
            lblMensaje.setText(e.getMessage());
        }
    }




}
