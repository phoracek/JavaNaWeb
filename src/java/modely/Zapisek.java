package modely;

public class Zapisek {

    private String nadpis;
    private String obsah;

    public Zapisek(String nadpis, String obsah) {
        this.nadpis = nadpis;
        this.obsah = obsah;
    }

    public void setNadpis(String nadpis) {
        this.nadpis = nadpis;
    }

    public void setObsah(String obsah) {
        this.obsah = obsah;
    }

    public String getNadpis() {
        return nadpis;
    }

    public String getObsah() {
        return obsah;
    }
}