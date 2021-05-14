import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Spell {

    public PathTransition execute(int n, Pokemon user, Enemy enemy, boolean flag, Pane board, Circle ball){
        switch (n){
            // thunder
            case 0:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), user.getAttack() * 2));
                } else {
                    user.setHP(damageInRange(user.getHP(), enemy.getAttack() * 2));
                }
                return animation_ball(board, flag, 0, ball);
            case 1:
                if (flag) {
                    int fact = (user.getLevel() < 10) ? 1 : (int) (2 * Math.pow(2, user.getLevel() / 10));
                    enemy.setDefence((enemy.getDefence() <= fact) ? 0 : (enemy.getDefence() - fact));
                } else {
                    user.setDefence((user.getDefence() <= 5) ? 0 : user.getDefence() - 5);
                }
                return animation_DefenceDown(board, flag, 1, ball, false);
            case 2:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), user.getAttack() * 2));
                } else {
                    user.setHP(damageInRange(user.getHP(), enemy.getAttack() * 2));
                }
                return animation_ball(board, flag, 2, ball);

            case 4:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), user.getLevel() * 10));
                } else {
                    user.setHP(damageInRange(user.getHP(), enemy.getHP() * 4 / 10));
                }
                return animation_ball(board, flag, 4, ball);


            case 10:
                if (flag) {
                    if (enemy.getDefence() == 0) enemy.setHP(0);
                    else enemy.setHP(damageInRange(enemy.getHP(), 40 * (user.getLevel() / 10 + 1)));
                } else {
                    user.setHP(damageInRange(user.getHP(), 200));
                }
                return animation_ball(board, flag, 10, ball);
            case 11:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), 200));
                } else {
                    user.setHP(damageInRange(user.getHP(), 200));
                }
                return animation_ball(board, flag, 11, ball);
            case 12:
                if (flag) {
                    user.setHP(damageInRange(user.getHP(),50));
                    enemy.setHP(damageInRange(enemy.getHP(), 300));
                } else {
                    user.setHP(damageInRange(user.getHP(), 160));
                }
                return animation_ball(board, flag, 12, ball);
            default:
                return null;
        }
    }

    public PathTransition animation_DefenceDown(Pane board, boolean user, int type, Circle ball, boolean uord) {
        Path path = new Path();
        PathTransition pt = new PathTransition();
        ball.setRadius(40);
        ball.toFront();
        if (user) {
            ball.setCenterX(800);
            ball.setCenterY(100);
            if (uord) {
                path.getElements().add(new MoveTo(800, 200));
                path.getElements().add(new LineTo(800, 100));
            } else {
                path.getElements().add(new MoveTo(800, 100));
                path.getElements().add(new LineTo(800, 200));
            }
        } else {
            ball.setCenterX(260);
            ball.setCenterY(440);
            if (uord) {
                path.getElements().add(new MoveTo(260, 500));
                path.getElements().add(new LineTo(260, 400));
            } else {
                path.getElements().add(new MoveTo(260, 400));
                path.getElements().add(new LineTo(260, 500));
            }
        }

        if (type == 1) ball.setFill(new ImagePattern(new Image("Pics/Spell/down.png")));

        board.getChildren().add(ball);
        pt.setNode(ball);
        pt.setDuration(Duration.millis(750));
        pt.setPath(path);
        pt.setAutoReverse(true);
        pt.setCycleCount(2);
        return pt;
    }

    public PathTransition animation_ball(Pane board, boolean user, int type, Circle ball) {
        Path path = new Path();
        PathTransition pt = new PathTransition();
        if (user) {
            ball.setCenterX(260);
            ball.setCenterY(470);
            ball.setRadius(40);
            ball.toFront();
            path.getElements().add(new MoveTo(260,470));
            path.getElements().add(new LineTo(800,130));
        } else {
            ball.setCenterX(800);
            ball.setCenterY(130);
            ball.setRadius(40);
            ball.toFront();
            path.getElements().add(new MoveTo(800,130));
            path.getElements().add(new LineTo(260,470));
        }

        if (type == 0) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/thunder.png")));
            ball.setRadius(60);
        }
        else if (type == 2) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/water_ball.png")));
            ball.setRadius(60);
        }
        else if (type == 10) ball.setFill(new ImagePattern(new Image("Pics/Spell/thunderBall.png")));
        else if (type == 11) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/crystalball.jpg")));
            ball.setRadius(100);
        }
        board.getChildren().add(ball);
        pt.setNode(ball);

        pt.setDuration(Duration.millis(1500));
        pt.setPath(path);

        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        return pt;
    }

    public int mana_gain(int curr, int gain){
        return Math.min(curr + gain, 100);
    }

    public int damageInRange(int HP, int dmg){
        if (HP <= dmg) return 0;
        else return HP - dmg;
    }
}
