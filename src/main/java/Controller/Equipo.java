package Controller;

public class Equipo {

    private String name;
    private String points;
    private String value;
    private String players;

    public int getPujaMaxima() {
        return pujaMaxima;
    }

    public int getBalance() {
        return balance;
    }

    private int pujaMaxima;
    private int balance;



    public void setBalance(int valance) {
        this.balance = valance;
    }

    public Equipo(String name, String points, String value, String players) {

        this.name = name;
        this.points = points;
        this.value = value;
        this.players = players;

        this.balance = 0;
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



    @Override
    public String toString() {
        return "Equipo{" +

                ", name='" + name + '\'' +
                ", points='" + points + '\'' +
                ", value='" + value + '\'' +
                ", players='" + players + '\'' +
                ", valance='" + pujaMaxima + '\'' +
                ", valance='" + balance + '\'' +
                '}';
    }
}
