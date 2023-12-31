package com.example.tic_tac_toe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Label playerXscoreLabel, playerOscoreLabel;
private Button[][] buttons=new Button[3][3];
private int playerxscore=0,playerOscore=0;
private boolean playerxturn=true;
    private BorderPane createContent(){
        BorderPane root=new BorderPane();
        //Title
        Label ititle=new Label("Tic Tac Toe");
        ititle.setStyle("-fx-font-size:34pt;-fx-font-weight:bold;");
        root.setTop(ititle);
        BorderPane.setAlignment(ititle, Pos.CENTER);
        //game board

        GridPane gridpane=new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(16);
        gridpane.setAlignment(Pos.CENTER);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Button button=new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size:24pt;-fx-font-weight:bold;");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j]=button;
                gridpane.add(button,j,i);
            }
        }
root.setCenter(gridpane);
        BorderPane.setAlignment(gridpane, Pos.CENTER);
        //Score

        HBox scoreBoard=new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXscoreLabel=new Label("Player x:0");
        playerXscoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold;");
        playerOscoreLabel=new Label("Player O:0");
        playerOscoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold;");
        scoreBoard.getChildren().addAll(playerXscoreLabel,playerOscoreLabel);
        root.setBottom(scoreBoard);
        return root;
    }
 private void buttonClicked(Button button){

        if(button.getText().equals("")){
            if(playerxturn){
            button.setText("X");
        }else{
            button.setText("O");
        }
     playerxturn=!playerxturn;
            checkWinner();
        }


       
 }

 private void checkWinner(){
        //row
for(int row=0;row<3;row++){
    if(buttons[row][0].getText().equals(buttons[row][1].getText())&&buttons[row][1].getText().equals(buttons[row][2].getText())&&!buttons[row][0].getText().isEmpty())

    {
        //we will have a wiiner
        String winner=buttons[row][0].getText();
        showWinnerDialog(winner);
        updateScore(winner);
        resetBoard();
        return;
    }
}
      //col
     for(int col=0;col<3;col++){
         if(buttons[0][col].getText().equals(buttons[1][col].getText())&&buttons[1][col].getText().equals(buttons[2][col].getText())&&!buttons[0][col].getText().isEmpty())

         {
             //we will have a wiiner
             String winner=buttons[0][col].getText();
             showWinnerDialog(winner);
             updateScore(winner);
             resetBoard();
             return;
         }
     }

     //diagonal


         if(buttons[0][0].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[2][2].getText())&&!buttons[0][0].getText().isEmpty())

         {
             //we will have a wiiner
             String winner=buttons[0][0].getText();
             showWinnerDialog(winner);
             updateScore(winner);
             resetBoard();
             return;
         }


     if(buttons[2][0].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[0][2].getText())&&!buttons[2][0].getText().isEmpty())

     {
         //we will have a wiiner
         String winner=buttons[2][0].getText();
         showWinnerDialog(winner);
         updateScore(winner);
         resetBoard();
         return;
     }
  boolean tie=true;
     for(Button row[]:buttons){
         for(Button button:row){
            if(button.getText().isEmpty()){
                tie=false;
                break;
            }
         }
     }
 if(tie){
     showTieDialog();
     resetBoard();
 }
 }
//private void showWinner
private void showWinnerDialog(String winner){
Alert alert=new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("WINNER");
alert.setContentText("Congratulation"+winner+"! You won the game");
alert.setHeaderText("");
alert.showAndWait();
}

    private void showTieDialog(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game over It's a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }


private void updateScore(String winner){
if(winner.equals("X")){
    playerxscore++;
    playerXscoreLabel.setText("Player X:"+playerxscore);
}else{
    playerOscore++;
    playerOscoreLabel.setText("Player O:"+playerOscore);
}
}

public void resetBoard(){
        for(Button row[]:buttons){
        for(Button button:row){
            button.setText("");
        }
        }
}
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("TIC TAC TOE!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}