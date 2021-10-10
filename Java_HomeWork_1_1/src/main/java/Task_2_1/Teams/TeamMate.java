package Task_2_1.Teams;

import Task_2_1.Exceptions.EnduranceException;

public class TeamMate {
    private final String name;
    private double enduranceForJump;
    private double enduranceForRun;

    public String getName() {
        return name;
    }

    public double getEnduranceForJump() {
        return enduranceForJump;
    }

    public void setEnduranceForJump(double enduranceForJump) {
        this.enduranceForJump = enduranceForJump;
    }

    public double getEnduranceForRun() {
        return enduranceForRun;
    }

    public void setEnduranceForRun(double enduranceForRun) {
        this.enduranceForRun = enduranceForRun;
    }

    /**
     * Так как для того, чтобы участник сошел с препятствия меняется их параметр выносливости на -1 и чтобы не ломалась логика
     * было решено создать исключение на создание участников с отрицательной выносливостью и выбросить его в метод main
     */
    public TeamMate(String name, double enduranceForJump, double enduranceForRun) throws EnduranceException {
            this.name = name;
        if(enduranceForJump >= 0 && enduranceForRun >= 0) {
            this.enduranceForJump = enduranceForJump;
            this.enduranceForRun = enduranceForRun;
        }
        else {
            throw new EnduranceException("Выносливость должна быть больше 0!");
        }
    }
}
