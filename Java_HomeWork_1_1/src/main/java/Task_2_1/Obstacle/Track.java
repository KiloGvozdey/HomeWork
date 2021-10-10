package Task_2_1.Obstacle;

import Task_2_1.Teams.TeamMate;

/**
 * Класс Track сделан дочерним классом Course для того, чтобы переопределить родительский метод и для создания ссылок типа Course на объект типа Track
 */
public class Track extends Course {

    private final double distance;

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
        System.out.printf("Участник %s готовится пробежать препятствие длиной %s", teamMate.getName(), this.distance);
        if(teamMate.getEnduranceForRun() >= this.distance) {
            teamMate.setEnduranceForRun(teamMate.getEnduranceForRun() - this.distance);
            System.out.println(" и успешно справляется");
        }
        else {
            System.out.println(" и ему не хватает выносливости, он сходит с дистанции.");
            teamMate.setEnduranceForRun(-1);
        }
    }
    public Track(double distance) {
        this.distance = distance;
    }
}
