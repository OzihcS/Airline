package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class of brigade
 */
public class Brigade extends AbstractEntity{

    private Staffer pilot;
    private Staffer navigator;
    private Staffer radioman;
    private List<Staffer> stewardess;


    public Staffer getPilot() {
        return pilot;
    }

    public void setPilot(Staffer pilot) {
        this.pilot = pilot;
    }

    public Staffer getNavigator() {
        return navigator;
    }

    public void setNavigator(Staffer navigator) {
        this.navigator = navigator;
    }

    public Staffer getRadioman() {
        return radioman;
    }

    public void setRadioman(Staffer radioman) {
        this.radioman = radioman;
    }

    public List<Staffer> getStewardess() {
        return stewardess;
    }

    public void setStewardess(List<Staffer> stewardess) {
        this.stewardess = stewardess;
    }

    public void addStewardess(Staffer stewardess) {
        if (this.stewardess == null) {
            this.stewardess = new ArrayList<>();
        }
        this.stewardess.add(stewardess);
    }
}
