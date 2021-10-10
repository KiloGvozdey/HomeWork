package Task_2_1.Obstacle;

import Task_2_1.Teams.Team;
import Task_2_1.Teams.TeamMate;

public class Course {
    private final Course[] courses;

    /**
     Метод ниже внутри себя использует переопределенный метод дочерних классов Wall и Track
     */
    public void go(Team team){
        for (Course obstacle : courses) {
            for (TeamMate teamMate : team.getTeamMates()) {
                obstacle.crossObstacle(teamMate);
            }
            System.out.println();
        }
    }

    /**
     Метод ниже создан специально как затычка, чтобы его в дальнейшем переопределить, хотел сперва сделать этот класс абстрактным,
     но в ТЗ написано, что надо создавать препятствия через конструктор Course.
     */
    protected void crossObstacle(TeamMate teamMate){}

    /**
     Конструктор ниже насколько я понимаю, использует здесь парадигму полиморфизма,
     что позволяет создавать преграды в любом количестве и порядке.
     */
    public Course(Course ... courses) {
        this.courses = courses;
    }

}
