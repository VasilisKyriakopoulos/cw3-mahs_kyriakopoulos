package gr.uop;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    TextField search = new TextField();
    ListView  list = new ListView<Text>();
    ArrayList<Text>  arrList = new ArrayList<Text>();
    ListView  list2 = new ListView<Text>();
    @Override
    public void start(Stage stage) {
       
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Text text1 =  new Text("To");
        TextField numbers  = new TextField("");
        Button addButton = new Button("Add...");
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
        message.setOnKeyPressed(e -> {
            
            int num = message.getLength();

            text2.setText(num + "/160");
         });
        vbox.getChildren().addAll(hbox,messageBox,sendButton,text2Box);
        var scene = new Scene(vbox, 640, 480);
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.setTitle("SMS App");
        stage.setScene(scene);
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
public class Search implements EventHandler <ActionEvent>{
    public void handle(ActionEvent event){
        list.getItems().clear();
        String text = search.getText();
        for(Text item : arrList){
            String text2 = item.getText();
            boolean flag = Pattern.compile(text).matcher(text2).find();
            if(flag){
                list.getItems().add(new Text(text2));
            }
        }
    }
}
    public static void main(String[] args) {
        launch(args);
    }

}