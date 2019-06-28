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


public class Sample2 extends Application{
    private Label lb;
    private Image im0, im1, im2, im3, im_mons, im_hero;
    private Label[][] lb1 = new Label[20][15];

    //初期位置
    private int pos_x = 1;
    private int pos_y = 0;

    private int map_data[][] = {{ 1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                { 1, 0, 0, 1, 1, 8, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                { 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                                { 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 1},
                                { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                { 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                { 1, 0, 0, 1, 0, 0, 0, 1, 1, 8, 1, 1, 1, 1, 0, 0, 0, 1, 0, 3},
                                { 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                                { 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                { 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage)throws Exception{
        //コントロールの作成
        lb = new Label("HelloWorlds");

        im0 = new Image(getClass().getResourceAsStream("img/chap-mapfield.png"));
        im1 = new Image(getClass().getResourceAsStream("img/chap-mapwall.png"));
        im2 = new Image(getClass().getResourceAsStream("img/chap-mapkey.png"));
        im3 = new Image(getClass().getResourceAsStream("img/chap-mapgoal.png"));
        im_mons = new Image(getClass().getResourceAsStream("img/chap-mapmonster.png"));
        im_hero = new Image(getClass().getResourceAsStream("img/chap-mapman.png"));

        //マップの作成
        for(int i=0; i<lb1.length; i++){
            for(int j=0; j<lb1[i].length; j++){
                if(map_data[j][i] == 0){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im0));
                }else if(map_data[j][i] == 1){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im1));
                }else if(map_data[j][i] == 2){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im2));
                }else if(map_data[j][i] == 3){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im3));
                }else if(map_data[j][i] == 8){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im_mons));
                }else if(map_data[j][i] == 9){
                    lb1[i][j] = new Label("");
                    lb1[i][j].setGraphic(new ImageView(im_hero));
                }
            }
        }

        //ペインの作成
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        VBox vb = new VBox();

        for (int i = 0; i < lb1.length; i++) {
            for (int j = 0; j < lb1[i].length; j++) {
                gp.add(lb1[i][j], i, j);
            }
        }

        vb.getChildren().add(gp);
        vb.getChildren().add(lb);

        //ペインへの追加
        bp.setTop(vb);

        //シーンの作成
        Scene sc = new Scene(bp, 1240, 1000);

        // イベントハンドラの登録
        sc.setOnKeyPressed(new KeyEventHandler());

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("サンプル");
        stage.show();
    }

    class KeyEventHandler implements EventHandler<KeyEvent> {
        public void handle(KeyEvent ke) {
            String str;
            KeyCode k = ke.getCode();

            switch (k) {
            case UP:
                if(pos_y <= 0){
                    lb.setText("これ以上は上に行けません。");
                    break;
                }

                if(map_data[pos_y - 1][pos_x] != 1){
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    pos_y = pos_y - 1;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im_hero));
                }
                break;
            case DOWN:
                if (pos_y >= 14) {
                    lb.setText("これ以上は下に行けません。");
                    break;
                }

                if(map_data[pos_y + 1][pos_x] != 1){
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    pos_y = pos_y + 1;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im_hero));
                }
                break;
            case LEFT:
                if (pos_x <= 0) {
                    lb.setText("これ以上は左に行けません。");
                    break;
                }

                if(map_data[pos_y][pos_x - 1] != 1){
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    pos_x = pos_x - 1;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im_hero));
                }
                break;
            case RIGHT:
                if (pos_x >= 19) {
                    lb.setText("これ以上は右に行けません。");
                    break;
                }

                if(map_data[pos_y][pos_x + 1] != 1){
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    pos_x = pos_x + 1;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im_hero));
                }
                break;
            default:
                lb.setText("方向キーを入力してください。");
            }

            System.out.println(k.toString());
            System.out.println(pos_x);
            System.out.println(pos_y);
        }
    }
}