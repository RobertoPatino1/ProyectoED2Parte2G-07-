/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;


public class StartMenuController implements Initializable {
public static  int id = 0;
    @FXML
    private BorderPane borderPane;
    private ArrayList<String> listaPreguntas = new ArrayList<>();
    private ArrayList<String> listaRespuestas = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML
    private void jugar(ActionEvent event) {
        
        if(listaPreguntas.isEmpty()||listaRespuestas.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Por favor, asegurese de cargar sus preguntas y respuestas antes de jugar"); //FIXME
            alerta.setTitle("Error");
            alerta.show();  
        }else{
           borderPane.getChildren().clear(); 
        }
        
        
        //Cargamos todas las preguntas y respuestas
        
    }

    @FXML
    private void cargarPreguntas(ActionEvent event) throws IOException {
        Alert aviso = new Alert(Alert.AlertType.INFORMATION,"Asegurese de seleccionar primero el archivo de preguntas \ny luego el archivo de respuestas"); //FIXME
        aviso.setHeaderText("Importante: ");

        aviso.show();  
        try{
        FileChooser fileChooser = new FileChooser(); //Este nos permite abrir el explorador de archivo
        fileChooser.setTitle("Cargar archivos");
       
        

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        

        List<File> fileList = fileChooser.showOpenMultipleDialog(null);
        
        
        if(fileList!=null){

            Path fromPreguntas = Paths.get(fileList.get(0).toURI());
            Path toPreguntas = Paths.get("archivos/preguntas/"+id+"_"+fileList.get(1).getName());
            Path fromRespuestas = Paths.get(fileList.get(0).toURI());
            Path toRespuestas = Paths.get("archivos/respuestas/"+id+"_"+fileList.get(0).getName());
            
            Files.copy(fromPreguntas, toPreguntas);
            Files.copy(fromRespuestas, toRespuestas);

            id++;
            
        }
        

    
       }catch(FileAlreadyExistsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"El archivo que se ha seleccionado ya se encuentra almacenado, por favor seleccione otro"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");

            alerta.show();  
       }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
    
    private void actualizarListaArchivos(){

        File[] files = new File("archivos/").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : files) {
            if (file.isFile()) {
                listaPreguntas.add(file.getName());
            }
        }
    }

}
