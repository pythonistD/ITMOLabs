package control;

public class InfDeliverer {
    private static Information inf;

    public InfDeliverer(Information information) {
        inf = information;
    }

    public static void setInf(Information inf) {
        InfDeliverer.inf = inf;
    }

    public static Information infDeliver() {
        return inf;
    }
}
