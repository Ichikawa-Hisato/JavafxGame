import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.image.*;

public class Sample1 extends Application{
    private Label lb1, lb2;
    private RadioButton rb1, rb2, rb3;
    private ToggleGroup tg;
    private CheckBox ch1;
    private Image im;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        //コントロールの作成
        lb1 = new Label("いらっしゃいませ");
        lb2 = new Label("車です。");
        rb1 = new RadioButton("赤");
        rb2 = new RadioButton("青");
        rb3 = new RadioButton("黄色");
        tg = new ToggleGroup();
        ch1 = new CheckBox("画像の表示");
        im = new Image(getClass().getResourceAsStream("img/chap-mapfield.png"));

        //コントロールの設定
        lb2.setGraphic(new ImageView(im));
        ch1.setSelected(true);

        //トグルグループへの追加
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        rb1.setSelected(true);
        lb1.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        //ペインの作成
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        VBox vb = new VBox();

        //ペインへの追加
        hb.getChildren().add(rb1);
        hb.getChildren().add(rb2);
        hb.getChildren().add(rb3);
        hb.setAlignment(Pos.CENTER);

        vb.getChildren().add(hb);
        vb.getChildren().add(lb2);
        vb.setAlignment(Pos.CENTER);

        bp.setTop(lb1);
        bp.setCenter(vb);
        bp.setBottom(ch1);

        //イベントハンドラの登録
        rb1.setOnAction(new SampleEventHandler());
        rb2.setOnAction(new SampleEventHandler());
        rb3.setOnAction(new SampleEventHandler());
        ch1.setOnAction(new CheckBoxEventHandler());

        //シーンの作成
        Scene sc = new Scene(bp, 300, 200);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("サンプル");
        stage.show();

    }

    //イベントハンドラクラス
    class SampleEventHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            RadioButton tmp = (RadioButton) e.getSource();
            if(tmp == rb1){
                lb1.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            }else if(tmp == rb2){
                lb1.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
            }else if (tmp == rb3){
                lb1.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
            }

            CheckBox tmp2 = (CheckBox) e.getSource();
            if(tmp2.isSelected() == true){
                lb2.setGraphic(new ImageView(im));
            }else if(tmp2.isSelected() == false){
                lb2.setGraphic(null);;
            }
        }
    }

    class CheckBoxEventHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e) {
            CheckBox tmp = (CheckBox) e.getSource();
            if (tmp.isSelected() == true) {
                lb2.setGraphic(new ImageView(im));
            } else if (tmp.isSelected() == false) {
                lb2.setGraphic(null);;
            }
        }
    }
}