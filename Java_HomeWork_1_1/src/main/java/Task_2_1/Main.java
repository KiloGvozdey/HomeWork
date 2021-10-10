package Task_2_1;

import Task_2_1.Exceptions.EnduranceException;
import Task_2_1.Obstacle.Course;
import Task_2_1.Obstacle.Track;
import Task_2_1.Obstacle.Wall;
import Task_2_1.Teams.Team;
import Task_2_1.Teams.TeamMate;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(new Wall(1.5), new Track(200), new Wall(2.5), new Track(300));
        Team team = null;
        try {
            team = new Team(new TeamMate("Alex", 30, 100),
                                            new TeamMate("Ivan", 2.5, 200),
                                            new TeamMate("Pavel", 1.5, 300),
                                            new TeamMate("Vladimir", 10, 1000));
        } catch (EnduranceException e) {
            System.out.println(e.getMessage());
        }

        try {
            c.go(team);
            team.showResult();
        } catch (Exception e) {
            System.out.println("=)");
        }


    }

}
