package school2107.bot;

public class Olimpiada {
    public int cls;
    public String sbt;
    public String cat;
    public String key;
    public int stage;
    Olimpiada(int i,String s,String c,String k,int st){
        this.cls=i;
        this.sbt=s;
        this.cat=c;
        this.key=k;
        this.stage=st;
        App.olimpiads.add(this);
    }
}
