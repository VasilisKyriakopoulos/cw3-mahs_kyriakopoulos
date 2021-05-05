package gr.uop;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {
    ListView  list = new ListView<Text>();
    ListView  list2 = new ListView<Text>();
    @Override
    public void start(Stage stage) {
       
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Text text1 =  new Text("To");
        TextField numbers  = new TextField("");
        Button addButton = new Button("Add...");
        addButton.setMinWidth(50);
        numbers.setPrefWidth(Integer.MAX_VALUE);
        HBox numbersBox = new HBox();
        numbersBox.getChildren().addAll(text1,numbers,addButton);
        numbersBox.setPadding(new Insets(5,5,5,5));
        numbersBox.setSpacing(10);
        hbox.getChildren().addAll(numbersBox);
        TextArea message = new TextArea();
        message.setPrefHeight(Integer.MAX_VALUE);
        message.setPrefWidth(Integer.MAX_VALUE);
        VBox messageBox = new VBox();
    
        messageBox.getChildren().add(message);
        messageBox.setPadding(new Insets(5,5,5,5));
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(Integer.MAX_VALUE);
        HBox sendButtonBox = new HBox();
        sendButtonBox.getChildren().add(sendButton);
        Text text2 =  new Text("0/160");
        HBox text2Box = new HBox();
        text2Box.getChildren().add(text2);
        text2Box.setAlignment(Pos.BASELINE_RIGHT);
        text2Box.setPadding(new Insets(0,10,0,0));
        message.setOnKeyTyped(e -> {
            String str = message.getText();
            int num = message.getLength();
            for ( int i=0 ; i< str.length();i++){
                if(str.charAt(i)=='\n'){
                    num--;
                }
            }
            int lim = 160;
            if ( num > lim ){
                int div = num/lim;
                lim += lim*div;
            }
            else if (num < lim-160){
                lim -=160;
            }
            text2.setText(num +"/"+ lim);
         });
        vbox.getChildren().addAll(hbox,messageBox,sendButton,text2Box);



        ////////////////////////////////////////////////////////////////////
        VBox  vbox3 = new VBox();
        list.setPrefHeight(Integer.MAX_VALUE);
        list.setMaxWidth(200);
        vbox3.getChildren().addAll(list);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setSpacing(10);
        vbox3.setPadding(new Insets(10,10,50,10));
        HBox hbox3 = new HBox();
        Button buttonRight = new Button("Add");
        Button buttonLeft = new Button("Remove");
        buttonLeft.setMaxWidth(200);
        buttonRight.setMaxWidth(200);
        VBox buttonsRightLeft = new VBox();
        buttonsRightLeft.getChildren().addAll(buttonRight,buttonLeft);
        buttonsRightLeft.setAlignment(Pos.CENTER);
        buttonsRightLeft.setSpacing(5);
        VBox  vbox4 = new VBox();
        list2.setPrefHeight(Integer.MAX_VALUE);
        list2.setMaxWidth(200);
        vbox4.getChildren().addAll(list2);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setSpacing(10);
        vbox4.setPadding(new Insets(10,10,50,10));
        hbox3.getChildren().addAll(vbox3,buttonsRightLeft,vbox4);
        hbox3.setAlignment(Pos.CENTER);
         
         for(int i=0 ; i<50 ; i++){
            Random rand = new Random();
            int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
            int num2 = rand.nextInt(743);
            int num3 = rand.nextInt(10000);
            DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
            DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros
            String phoneNumber = df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
            list.getItems().add(new Text(phoneNumber));
         }
         buttonRight.setOnAction(new MoveToRightList());
         buttonLeft.setOnAction(new MoveToLeftList());
         Button buttonOk = new Button("OK");
         Button buttonCancel = new Button("Cancel");
         buttonOk.setMinWidth(50);
         buttonCancel.setMinWidth(50);
         HBox boxOkCancel  = new HBox();
         boxOkCancel.getChildren().addAll(buttonOk,buttonCancel);
         boxOkCancel.setAlignment(Pos.CENTER_RIGHT);
         boxOkCancel.setPadding(new Insets(10,80,10,10));
         boxOkCancel.setSpacing(10);
         
         VBox mainVbox = new VBox();
         mainVbox.setAlignment(Pos.CENTER);
         mainVbox.getChildren().addAll(hbox3,boxOkCancel);
        var scene = new Scene(vbox, 640, 480);
        var scene2 = new Scene(mainVbox, 640, 480);
        Stage stage2 = new Stage();
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (e) -> {
                 Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to close the window?");
    
                 Optional<ButtonType> result = alert.showAndWait();
    
                 if (result.isPresent()) {
                     if (result.get() == ButtonType.OK) {
                         System.out.println("Closing...");
                    }
                    else if (result.get() == ButtonType.CANCEL) {
                         System.out.println("Cancel");
                         e.consume();
                     }
                 }
             });
             addButton.setOnAction(e->{
                stage.close();
                stage2.show();
            });
            buttonCancel.setOnAction(e -> {
                stage2.close();
                stage.show();
            });
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.setTitle("SMS App");
        stage.setScene(scene);
        stage2.setMinHeight(300);
        stage2.setMinWidth(300);
        stage2.setTitle("Add recipients");
        stage2.setResizable(false);
        stage2.setScene(scene2);
        stage.show();
        
    
}
public class MoveToRightList implements EventHandler <ActionEvent>{
        
    public void handle(ActionEvent event){
        int index = list.getSelectionModel().getSelectedIndex();
        Text text = (Text) list.getSelectionModel().getSelectedItem();
        list2.getItems().add(text);
        list.getItems().remove(index);
        
    }
}
public class MoveToLeftList implements EventHandler <ActionEvent>{
    
    public void handle(ActionEvent event){
        int index = list2.getSelectionModel().getSelectedIndex();
        Text text = (Text) list2.getSelectionModel().getSelectedItem();
        list.getItems().add(text);
        list2.getItems().remove(index);
        
    }
}
    public static void main(String[] args) {
        launch(args);
    }

}