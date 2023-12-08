package com.uni.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class NavController {
    @FXML
    private TextField statusField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField friendField;
    @FXML
    private Pane contentPane;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox friendsVBox;
    @FXML
    private Label messagesLabel;
    @FXML
    private Label listLabel;
    private HashMap<String, Person> users = new HashMap<>();

    @FXML
    protected void onAddButtonClick(ActionEvent actionEvent) {
        String name = nameField.getText();
        if(!name.isEmpty() && users.get(name) == null){
            users.put(name.toLowerCase(), new Person(name.toLowerCase()));
            onLookupButtonClick();
        }
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent actionEvent) {
        String name = nameField.getText();
        if(!name.isEmpty() && users.get(name) != null){
            users.remove(name.toLowerCase());
            listLabel.setText("");
            friendsVBox.setStyle("-fx-background-color: #FFFFFF");
            nameLabel.setText("");
            Image image = new Image("noImage.png");
            profileImage.setImage(image);
            statusLabel.setText("");
            friendsVBox.getChildren().clear();
            messagesLabel.setText("User " + name + " was removed!");
        } else {
            messagesLabel.setText("User " + name + " was not found!");
        }
    }

    @FXML
    protected void onLookupButtonClick(){
        String name = nameField.getText();
        if (!name.isEmpty()){
            Person user = users.get(name.toLowerCase());
            if(user != null){
                listLabel.setText("Friends List");
                friendsVBox.setStyle("-fx-background-color: #e0e0e0");
                friendsVBox.getChildren().clear();
                nameLabel.setText(user.getName());
                if(!user.getImage().isEmpty()) {
                    Image image = new Image(user.getImage());
                    profileImage.setImage(image);
                }
                statusLabel.setText(user.getStatus());
                user.getFriends().forEach((key, value) -> {
                    Label label = new Label(key);
                    friendsVBox.getChildren().add(label);
                });
                messagesLabel.setText("User " + user.getName() + " was found!");
            } else {
                messagesLabel.setText("User " + name + " was not found!");
            }
        }
    }

    @FXML
    protected void onAddFriendButtonClick(){
        String name = friendField.getText();
        String name2 = nameLabel.getText();
        if (!name.isEmpty() && !name2.isEmpty() && !name.equalsIgnoreCase(name2)) {
            Person user = users.get(name.toLowerCase());
            Person user2 = users.get(name2.toLowerCase());
            if(user != null && user2 != null){
                user2.getFriends().put(name.toLowerCase(), user);
                user.getFriends().put(name2.toLowerCase(), user2);
                friendsVBox.getChildren().clear();
                user2.getFriends().forEach((key, value) -> {
                    Label label = new Label(key);
                    friendsVBox.getChildren().add(label);
                });
            } else {
                messagesLabel.setText("User " + name + " was not found");
            }
        } else if(name.equalsIgnoreCase(name2)){
            messagesLabel.setText("You can't add yourself!");
        }
    }

    @FXML
    protected void onRemoveFriendButtonClick(){
        String name = friendField.getText();
        String name2 = nameLabel.getText();
        if (!name.isEmpty() && !name2.isEmpty() && !name.equalsIgnoreCase(name2)) {
            Person user = users.get(name.toLowerCase());
            Person user2 = users.get(name2.toLowerCase());
            if(user != null && user2 != null){
                user2.getFriends().remove(name.toLowerCase());
                user.getFriends().remove(name2.toLowerCase());
                friendsVBox.getChildren().clear();
                user2.getFriends().forEach((key, value) -> {
                    Label label = new Label(key);
                    friendsVBox.getChildren().add(label);
                });
            } else {
                messagesLabel.setText("User " + name + " was not found");
            }
        } else if(name.equalsIgnoreCase(name2)){
            messagesLabel.setText("You can't add yourself!");
        }
    }

    @FXML
    protected void onSetImageButtonClick(){
        String image = imageField.getText();
        if(!image.isEmpty() && !nameLabel.getText().isEmpty()){
            Image asset = new Image(image);
            users.get(nameLabel.getText().toLowerCase()).setImage(image);
            profileImage.setImage(asset);
            messagesLabel.setText("Image was set");
        } else if (image.isEmpty()) {
            messagesLabel.setText("You can't set nothing!");
        }
    }

    @FXML
    protected void onSetStatusButtonClick(){
        String status = statusField.getText();
        if(!status.isEmpty() && !nameLabel.getText().isEmpty()){
            users.get(nameLabel.getText().toLowerCase()).setStatus(status);
            statusLabel.setText(status);
            messagesLabel.setText("Status was set");
        } else if (status.isEmpty()) {
            messagesLabel.setText("You can't set nothing!");
        }
    }
}