package homeworks.homework03;

public class Tv {
    private String brandName;
    private Integer screenSize;
    private String screenTechnology;
    private Integer refreshRate;
    private String operatingSystem;
    private Boolean bluetooth;
    private String countryOfOrigin;
    private static final String empty = "empty";

    public Tv(String brandName, int screenSize, int refreshRate, String operatingSystem, String countryOfOrigin) {
        this.brandName = brandName;
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.operatingSystem = operatingSystem;
        this.countryOfOrigin = countryOfOrigin;
        this.screenTechnology = empty;
        this.bluetooth = false;
    }

    public Tv(String brandName, int screenSize, String countryOfOrigin, String operatingSystem) {
        this.brandName = brandName;
        this.screenSize = screenSize;
        this.countryOfOrigin = countryOfOrigin;
        this.operatingSystem = operatingSystem;
        this.refreshRate = 60;
        this.screenTechnology = empty;
        this.bluetooth = true;
    }

    public Tv(String brandName, int screenSize, String operatingSystem) {
        this.brandName = brandName;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.countryOfOrigin = empty;
        this.refreshRate = 68;
        this.screenTechnology = empty;
        this.bluetooth = true;
    }

    public Tv(int screenSize, String countryOfOrigin) {
        this.screenSize = screenSize;
        this.countryOfOrigin = countryOfOrigin;
        this.brandName = empty;
        this.operatingSystem = empty;
        this.refreshRate = 90;
        this.screenTechnology = empty;
        this.bluetooth = false;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "brandName='" + brandName + '\'' +
                ", screenSize=" + screenSize +
                ", screenTechnology='" + screenTechnology + '\'' +
                ", refreshRate=" + refreshRate +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", bluetooth=" + bluetooth +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                '}';
    }
}
