package Task_2_1.Obstacle;

import Task_2_1.Teams.TeamMate;

/**
 * Класс Wall сделан дочерним классом Course для того, чтобы переопределить родительский метод и для создания ссылок типа Course на объект типа Wall
 */
public class Wall extends Course {
    private final double height;

    /**
     * Метод ниже отвечает за вывод сообщений о текущем состоянии участников,
     * а так же при невозможности преодоления ими препятствия изменяет их параметр выносливости на -1,
     * чтобы снять их с дистанции
     */
    @Override
    protected void crossObstacle(TeamMate teamMate) {
        if(teamMate.getEnduranceForJump() == -1 || teamMate.getEnduranceForRun() == -1 ){
            return;
        }
        System.out.printf("Участник %s готовится перепрыгнуть стену высотой %s", teamMate.getName(), this.height);
        if(teamMate.getEnduranceForJump() >= this.height) {
            teamMate.setEnduranceForJump(teamMate.getEnduranceForJump() - this.height);
            System.out.println(" и успешно справляется");
        }
        else {
            System.out.println(" и ему не хватает выносливости, он сходит с дистанции.");
            teamMate.setEnduranceForJump(-1);
        }
    }

    public Wall(double height) {
        this.height = height;
    }



}
