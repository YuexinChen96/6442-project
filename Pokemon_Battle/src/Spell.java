import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Spell {

    public PathTransition execute(int n, Pokemon user, Enemy enemy, boolean flag, Pane board, Circle ball){
        // spell 1 - fire ball
        switch (n){
            case 0:
                if (flag) {
                    enemy.setHP(damageInRange(enemy.getHP(), 40));
                } else {
                    user.setHP(damageInRange(user.getHP(), 40));
                }
                return animation_ball(board, flag, 0, ball);

            default:
                return null;
        }
    }

    public PathTransition animation_ball(Pane board, boolean user, int type, Circle ball) {
        Path path = new Path();
        PathTransition pt = new PathTransition();
        if (user) {
            ball = new Circle(260, 470, 50);
            ball.toFront();
            path.getElements().add(new MoveTo(260,470));
            path.getElements().add(new LineTo(800,130));
        } else {
            ball = new Circle(800, 130, 50);
            ball.toFront();
            path.getElements().add(new MoveTo(800,130));
            path.getElements().add(new LineTo(260,470));
        }

        if (type == 0) ball.setFill(Color.RED);
        else if (type == 1) ball.setFill(Color.LIGHTBLUE);
        board.getChildren().add(ball);
        pt.setNode(ball);

        pt.setDuration(Duration.millis(3000));
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
