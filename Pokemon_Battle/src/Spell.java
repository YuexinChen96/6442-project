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
//Author: Yuexin Chen
public class Spell {
    // execute Spell (n: Spell id, flag: T for user turn / F for enemy's turn, ball: animation carrier
    public PathTransition execute(int n, Pokemon user, Enemy enemy, boolean flag, Pane board, Circle ball){
        switch (n){
            // different spells
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

            case 3:
                if (flag) {
                    user.setmaxHP(user.getmaxHP() - 8);
                    if (user.getHP() > user.getmaxHP()) user.setHP(user.getmaxHP());
                    user.setAttack(user.getAttack() + 1);
                } else {
                    enemy.setHP(enemy.getHP() - 20);
                    enemy.setAttack(enemy.getAttack() + 4);
                }
                return animation_DefenceDown(board, !flag, 3, ball, true);

            case 4:
                if (flag) {
                    int add = (int) (user.getAttack() * 1.5);
                    user.setHP(health_gain(user, add));
                    enemy.setHP(damageInRange(enemy.getHP(), add));
                } else {
                    int add = (int) (enemy.getAttack() * 1.5);
                    enemy.setHP(Math.min(enemy.getHP() + add, enemy.getmaxHP()));
                    user.setHP(damageInRange(user.getHP(), add));
                }
                return animation_ball(board, !flag, 4, ball);

            case 5:
                if (flag) {
                    int add = (int) (user.getmaxHP() * 0.4);
                    user.setHP(Math.min(add + user.getHP(), user.getmaxHP()));
                } else {
                    int add = (int) (enemy.getmaxHP() * 0.3);
                    enemy.setHP(Math.min(add + enemy.getHP(), enemy.getmaxHP()));
                }
                return animation_recover(board, flag, 5, ball);

            case 6:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), user.getLevel() * 4));
                } else {
                    user.setHP(damageInRange(user.getHP(), enemy.getHP() * 4 / 10));
                }
                return animation_ball(board, flag, 6, ball);

            case 7:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), enemy.getAttack() * 2));
                } else {
                    user.setHP(damageInRange(user.getHP(), user.getAttack() * 2));
                }
                return animation_DefenceDown(board, flag, 7, ball, false);

            case 8:
                if (flag) {
                    if (user.getAttack() < enemy.getAttack()) enemy.setHP(damageInRange(enemy.getHP(), user.getAttack() * 3));
                    else enemy.setHP(damageInRange(enemy.getHP(), (int)(user.getAttack() * 1.6)));
                } else {
                    if (user.getAttack() < enemy.getAttack()) user.setHP(damageInRange(user.getHP(), enemy.getAttack() * 3));
                    else user.setHP(damageInRange(user.getHP(), (int)(enemy.getAttack() * 1.6)));
                }
                return animation_ball(board, flag, 8, ball);

            case 9:
                if (flag) {
                    user.setHP(damageInRange(user.getHP(), (int)(user.getmaxHP() * 0.1)));
                    enemy.setAttack((enemy.getAttack() < 5) ? 0 : enemy.getAttack() - 5);
                } else {
                    enemy.setHP(damageInRange(enemy.getHP(), (int)(enemy.getmaxHP() * 0.2)));
                    user.setAttack(user.getAttack() - 5);
                }
                return animation_DefenceDown(board, flag, 1, ball, false);

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
                    user.setHP(damageInRange(user.getHP(),100));
                    enemy.setHP(damageInRange(enemy.getHP(), 400));
                } else {
                    user.setHP(damageInRange(user.getHP(), 260));
                }
                return animation_ball(board, flag, 12, ball);

            case 13:
                if (flag) {
                    user.setHP(damageInRange(user.getHP(), (int)(user.getmaxHP() * 0.2)));
                    enemy.setHP(damageInRange(enemy.getHP(), (int)(enemy.getmaxHP() * 0.5)));
                } else {
                    user.setHP(damageInRange(user.getHP(), (int)(user.getmaxHP() * 0.5)));
                    enemy.setHP(damageInRange(enemy.getHP(), (int)(enemy.getmaxHP() * 0.3 )));
                }
                return animation_ball(board, flag, 13, ball);

            case 14:
                if (flag) {
                    user.setHP(damageInRange(user.getHP(), (int)(user.getmaxHP() * 0.75)));
                    user.setAttack(user.getAttack() * 2);
                    user.setDefence((int)(user.getDefence() * 1.5));
                } else {
                    enemy.setHP(damageInRange(enemy.getHP(), (int)(enemy.getmaxHP() * 0.4)));
                    enemy.setAttack(enemy.getAttack() * 2);
                    enemy.setDefence((int)(enemy.getDefence() * 1.5));
                }
                return animation_DefenceDown(board, !flag, 3, ball, true);

            default:
                return null;
        }
    }

    // animation recover, type: pictures number, ball: animation carrier
    public PathTransition animation_recover(Pane board, boolean user, int type, Circle ball) {
        Path path = new Path();
        PathTransition pt = new PathTransition();
        ball.setRadius(50);
        ball.toFront();
        if (user) {
            ball.setCenterX(260);
            ball.setCenterY(470);
            path.getElements().add(new MoveTo(260, 470));
            path.getElements().add(new LineTo(160, 470));
            path.getElements().add(new LineTo(360, 470));
            path.getElements().add(new LineTo(260, 470));

        } else {
            ball.setCenterX(800);
            ball.setCenterY(130);
            path.getElements().add(new MoveTo(800, 130));
            path.getElements().add(new LineTo(700, 130));
            path.getElements().add(new LineTo(900, 130));
            path.getElements().add(new LineTo(700, 130));

        }

        if (type == 5) ball.setFill(new ImagePattern(new Image("Pics/Spell/redball.png")));

        board.getChildren().add(ball);
        pt.setNode(ball);
        pt.setDuration(Duration.millis(1500));
        pt.setPath(path);
        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        return pt;
    }

    // animation, user: T for user's turn OW enemy's turn, type: pics index, uord: T for Up / F for down
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
        else if (type == 3) ball.setFill(new ImagePattern(new Image("Pics/Spell/up.png")));
        else if (type == 7) ball.setFill(new ImagePattern(new Image("Pics/Spell/scard.png")));

        board.getChildren().add(ball);
        pt.setNode(ball);
        pt.setDuration(Duration.millis(750));
        pt.setPath(path);
        pt.setAutoReverse(true);
        pt.setCycleCount(2);
        return pt;
    }

    // animation ball, user: T for user's turn OW enemy's turn, type: pics index, ball: animation carrier
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
        else if (type == 4) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/redball.png")));
            ball.setRadius(60);
        }
        else if (type == 6) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/fireball.png")));
            ball.setRadius(60);
        }
        else if (type == 8) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/tornado.png")));
            ball.setRadius(60);
        }
        else if (type == 10) ball.setFill(new ImagePattern(new Image("Pics/Spell/thunderBall.png")));
        else if (type == 11) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/crystalball.jpg")));
            ball.setRadius(100);
        }
        else if (type == 12) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/colorball.png")));
            ball.setRadius(100);
        }
        else if (type == 13) {
            ball.setFill(new ImagePattern(new Image("Pics/Spell/blackball.png")));
            ball.setRadius(60);
        }
        board.getChildren().add(ball);
        pt.setNode(ball);

        pt.setDuration(Duration.millis(1500));
        pt.setPath(path);

        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        return pt;
    }

    // block over HP
    public int health_gain(Pokemon tar, int gain) {
        return Math.min(tar.getHP() + gain, tar.getmaxHP());
    }

    // block negative HP
    public int damageInRange(int HP, int dmg){
        if (HP <= dmg) return 0;
        else return HP - dmg;
    }
}
