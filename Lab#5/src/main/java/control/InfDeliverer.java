package control;

public class InfDeliverer {
    private static Information inf;

    public InfDeliverer(Information information){
        this.inf = information;
    }
    public static Information infDeliver(){
        return inf;
    }
}
