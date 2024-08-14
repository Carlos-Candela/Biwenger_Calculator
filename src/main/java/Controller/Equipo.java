package Controller;

public class Equipo {
    private String position;
    private String name;
    private String points;
    private String value;
    private String players;
    private String lastUpdate;
    private String valance;

    public String getValance() {
        return valance;
    }

    public void setValance(String valance) {
        this.valance = valance;
    }

    public Equipo(String position, String name, String points, String value, String players, String lastUpdate) {
        this.position = position;
        this.name = name;
        this.points = points;
        this.value = value;
        this.players = players;
        this.lastUpdate = lastUpdate;
        this.valance = "0";
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getPoints() {
        return points;
    }

    public String getValue() {
        return value;
    }

    public String getPlayers() {
        return players;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", points='" + points + '\'' +
                ", value='" + value + '\'' +
                ", players='" + players + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", valance='" + valance + '\'' +
                '}';
    }
}
