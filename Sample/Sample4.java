import mybeans.*;
import java.util.*;
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


public class Sample4 extends Application{
    private Label lb;
    private Image im0, im1, im2, im3, im_mons, im_hero;
    private Label[][] lb1 = new Label[20][15];

    //初期位置
    private int pos_x = 1;
    private int pos_y = 0;

    private ArrayList mapdata;
    private int map_data[][] = new int[15][20];

    //鍵の有無
    private int key_value = 0;

    //場面番号
    private int stage_number = 1;

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
        for (int i = 0; i < lb1.length; i++) {
            for (int j = 0; j < lb1[i].length; j++) {
                if (map_data[j][i] == 0) {
                    lb1[i][j] = new Label("");
                } else if (map_data[j][i] == 1) {
                    lb1[i][j] = new Label("");
                } else if (map_data[j][i] == 2) {
                    lb1[i][j] = new Label("");
                } else if (map_data[j][i] == 3) {
                    lb1[i][j] = new Label("");
                } else if (map_data[j][i] == 8) {
                    lb1[i][j] = new Label("");
                } else if (map_data[j][i] == 9) {
                    lb1[i][j] = new Label("");
                }
            }
        }
        getNewMap();

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
                //画面範囲チェック
                if(pos_y <= 0){
                    lb.setText("これ以上は上に行けません。");
                    break;
                }

                //移動先のデータを確認
                if(ChkMapData(pos_x,pos_y - 1) == 1){
                    break;
                }

                //移動の処理
                lb1[pos_x][pos_y - 1].setGraphic(new ImageView(im_hero));
                if(map_data[pos_y][pos_x] == 3){
                    if(key_value != 1){
                        map_data[pos_y][pos_x] = 3;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im3));
                    }else{
                        map_data[pos_y][pos_x] = 0;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im0));                                
                    }
                }else{
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                }
                
                pos_y = pos_y - 1;

                //移動後のゴール判定
                if(map_data[pos_y][pos_x] == 3){
                    if(key_value == 1){
                        getNewMap();
                    }
                }
                break;
            case DOWN:
                // 画面範囲チェック
                if (pos_y >= 14) {
                    lb.setText("これ以上は下に行けません。");
                    break;
                }

                // マップデータの確認
                if(ChkMapData(pos_x,pos_y + 1) == 1){
                    break;
                }

                // 移動の処理
                lb1[pos_x][pos_y + 1].setGraphic(new ImageView(im_hero));
                if (map_data[pos_y][pos_x] == 3) {
                    if (key_value != 1) {
                        map_data[pos_y][pos_x] = 3;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im3));
                    } else {
                        map_data[pos_y][pos_x] = 0;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    }
                } else {
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                }

                pos_y = pos_y + 1;
                // 移動後のゴール判定
                if (map_data[pos_y][pos_x] == 3) {
                    if (key_value == 1) {
                        getNewMap();
                    }
                }
                break;
            case LEFT:
                // 画面範囲チェック
                if (pos_x <= 0) {
                    lb.setText("これ以上は左に行けません。");
                    break;
                }

                // マップデータの確認
                if(ChkMapData(pos_x - 1,pos_y) == 1){
                    break;
                }

                // 移動の処理
                lb1[pos_x - 1][pos_y].setGraphic(new ImageView(im_hero));
                if (map_data[pos_y][pos_x] == 3) {
                    if (key_value != 1) {
                        map_data[pos_y][pos_x] = 3;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im3));
                    } else {
                        map_data[pos_y][pos_x] = 0;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    }
                } else {
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                }

                pos_x = pos_x - 1;
                // 移動後のゴール判定
                if (map_data[pos_y][pos_x] == 3) {
                    if (key_value == 1) {
                        getNewMap();
                    }
                }
                break;
            case RIGHT:
                // 画面範囲チェック
                if (pos_x >= 19) {
                    lb.setText("これ以上は右に行けません。");
                    break;
                }

                // マップデータの確認
                if(ChkMapData(pos_x + 1,pos_y) == 1){
                    break;
                }

                // 移動の処理
                lb1[pos_x + 1][pos_y].setGraphic(new ImageView(im_hero));
                if (map_data[pos_y][pos_x] == 3) {
                    if(key_value != 1) {
                        map_data[pos_y][pos_x] = 3;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im3));
                    }else {
                        map_data[pos_y][pos_x] = 0;
                        lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                    }
                }else {
                    map_data[pos_y][pos_x] = 0;
                    lb1[pos_x][pos_y].setGraphic(new ImageView(im0));
                }

                pos_x = pos_x + 1;
                // 移動後のゴール判定
                if (map_data[pos_y][pos_x] == 3) {
                    if (key_value == 1) {
                        getNewMap();
                    }
                }
                break;
            case ESCAPE:
                System.exit(1);
            default:
                lb.setText("方向キーを入力してください。");
            }

            System.out.print(k.toString() + " ");
            System.out.print(pos_x + " ");
            System.out.print(pos_y + " ");
            System.out.println(key_value);
        }
    }

    //マップデータの値の判定
    public int ChkMapData(int x, int y){
        int return_code = 0;
        if (map_data[y][x] == 1) {
            return_code = 1;
        } else if (map_data[y][x] == 2) {
            key_value = 1;
        } else if (map_data[y][x] == 3) {
            if (key_value == 1) {
                lb.setText("ゴール！！");
            } else {
                lb.setText("鍵がありません");
            }
        }
        return return_code;
    }

    //新しいマップの作成
    public void getNewMap(){
        System.out.println("新しいマップを生成");

        if(stage_number == 1){
            // 開始位置の変更
            pos_x = 1;
            pos_y = 0;

            // Beanの作成
            StageOneDataBean sdb = new StageOneDataBean();
            mapdata = sdb.getData();
            stage_number++;
        }else if(stage_number == 2){
            // 開始位置の変更
            pos_x = 0;
            pos_y = 8;

            // Beanの作成
            StageTwoDataBean sdb = new StageTwoDataBean();
            mapdata = sdb.getData();
            stage_number++;
        }else{
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("サンプル");
            al.getDialogPane().setHeaderText("終了です。");
            al.show();
            System.exit(1);
        }

        // DBからデータを取得
        for (int row = 0; row < mapdata.size(); row++) {
            ArrayList rowdata = (ArrayList) (mapdata.get(row));
            for (int column = 0; column < rowdata.size(); column++) {
                map_data[row][column] = Integer.parseInt(rowdata.get(column).toString());
            }
        }

        // マップの作成
        for (int i = 0; i < lb1.length; i++) {
            for (int j = 0; j < lb1[i].length; j++) {
                if (map_data[j][i] == 0) {
                    lb1[i][j].setGraphic(new ImageView(im0));
                } else if (map_data[j][i] == 1) {
                    lb1[i][j].setGraphic(new ImageView(im1));
                } else if (map_data[j][i] == 2) {
                    lb1[i][j].setGraphic(new ImageView(im2));
                } else if (map_data[j][i] == 3) {
                    lb1[i][j].setGraphic(new ImageView(im3));
                } else if (map_data[j][i] == 8) {
                    lb1[i][j].setGraphic(new ImageView(im_mons));
                } else if (map_data[j][i] == 9) {
                    lb1[i][j].setGraphic(new ImageView(im_hero));
                }
            }
        }
    }
}