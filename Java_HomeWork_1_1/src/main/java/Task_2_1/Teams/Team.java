package Task_2_1.Teams;

public class Team {
    private final TeamMate[] teamMates;

    public Team(TeamMate... teamMates) {
        this.teamMates = teamMates;
    }
    public TeamMate[] getTeamMates() {
        return teamMates;
    }
    public void showResult(){
        for (TeamMate teamMate : this.getTeamMates()){
            if(teamMate.getEnduranceForRun() >= 0 && teamMate.getEnduranceForJump() >= 0){
                System.out.printf("Участник %s успешно преодолел полосу препятствий %n", teamMate.getName());
            }
            else System.out.printf("Участник %s не справился с полосой препятствий %n", teamMate.getName());
        }
    }
}
