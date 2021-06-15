package Klases;

public class PrekesKlase {
    private int p_id;
    private String p_pav;
    private double p_kaina;
    private int p_kiekis;
    private String p_aprasas;

    //empty constructor
    public PrekesKlase(){}
    //constructor
    public PrekesKlase(int id, String pav, double kaina, int kiekis, String apraas){
        this.p_id = id;
        this.p_pav = pav;
        this.p_kaina = kaina;
        this.p_kiekis = kiekis;
        this.p_aprasas = apraas;
    }

    //setters getters
    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_pav() {
        return p_pav;
    }

    public void setP_pav(String p_pav) {
        this.p_pav = p_pav;
    }

    public double getP_kaina() {
        return p_kaina;
    }

    public void setP_kaina(double p_kaina) {
        this.p_kaina = p_kaina;
    }

    public int getP_kiekis() {
        return p_kiekis;
    }

    public void setP_kiekis(int p_kiekis) {
        this.p_kiekis = p_kiekis;
    }

    public String getP_aprasas() {
        return p_aprasas;
    }

    public void setP_aprasas(String p_aprasas) {
        this.p_aprasas = p_aprasas;
    }

    public void IsvestiPrekeId(){
        System.out.println("ID:"+this.p_id+"  Pavadinimas:"+this.p_pav+"  Kaina:"+this.p_kaina+"  Kiekis:"+this.p_kiekis+"  Aprasas:"+this.p_aprasas);
    }
}
