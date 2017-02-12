package setting;

/**
 * Created by Albert-IM on 08/02/2017.
 */

public enum DeployPhase {
    Debug(1),
    Release(2);

    int value;

    DeployPhase(int value) {
        this.value = value;
    }

    public static DeployPhase findByValue(int value) {
        switch (value) {
            case 1:
                return Debug;
            case 2:
                return Release;
            default:
                return null;
        }
    }
}