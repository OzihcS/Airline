package entity;

public class AdminStatistic extends AbstractEntity {

    int execute;
    int reject;

    public int getExecute() {
        return execute;
    }

    public void setExecute(int execute) {
        this.execute = execute;
    }

    public int getReject() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject = reject;
    }
}
