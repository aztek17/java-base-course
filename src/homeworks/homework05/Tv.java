package homeworks.homework05;

import java.util.Objects;

public class Tv {
    private String brandName;
    private Integer screenSize;
    private String screenTechnology;
    private Integer refreshRate;
    private String operatingSystem;
    private Boolean bluetooth;
    private String countryOfOrigin;

    public boolean isPoweredOn() {
        return isPoweredOn;
    }

    public void setPoweredOn(boolean poweredOn) {
        isPoweredOn = poweredOn;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(int currentChannel) {
        this.currentChannel = currentChannel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    private boolean isPoweredOn;
    private int currentChannel;
    private int volume;
    private static final String empty = "empty";

    public Tv(String brandName, int screenSize, int refreshRate, String operatingSystem, String countryOfOrigin) {
        setBrandName(brandName);
        setScreenSize(screenSize);
        setRefreshRate(refreshRate);
        setOperatingSystem(operatingSystem);
        setCountryOfOrigin(countryOfOrigin);
        setScreenTechnology(empty);
        setBluetooth(false);
    }

    public Tv(String brandName, int screenSize, String countryOfOrigin, String operatingSystem) {
        setBrandName(brandName);
        setScreenSize(screenSize);
        setCountryOfOrigin(countryOfOrigin);
        setOperatingSystem(operatingSystem);
        setRefreshRate(60);
        setScreenTechnology(empty);
        setBluetooth(true);
    }

    public Tv(String brandName, int currentChannel, int volume, boolean isPoweredOn) {
        setBrandName(brandName);
        setCurrentChannel(currentChannel);
        setVolume(volume);
        setPoweredOn(isPoweredOn);
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
                ", isPoweredOn=" + isPoweredOn +
                ", currentChannel=" + currentChannel +
                ", volume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Tv tv = (Tv) o;
        return isPoweredOn == tv.isPoweredOn && currentChannel == tv.currentChannel && volume == tv.volume
                && Objects.equals(brandName, tv.brandName) && Objects.equals(screenSize, tv.screenSize)
                && Objects.equals(screenTechnology, tv.screenTechnology) && Objects.equals(refreshRate, tv.refreshRate)
                && Objects.equals(operatingSystem, tv.operatingSystem) && Objects.equals(bluetooth, tv.bluetooth)
                && Objects.equals(countryOfOrigin, tv.countryOfOrigin);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(brandName);
        result = 31 * result + Objects.hashCode(screenSize);
        result = 31 * result + Objects.hashCode(screenTechnology);
        result = 31 * result + Objects.hashCode(refreshRate);
        result = 31 * result + Objects.hashCode(operatingSystem);
        result = 31 * result + Objects.hashCode(bluetooth);
        result = 31 * result + Objects.hashCode(countryOfOrigin);
        result = 31 * result + Boolean.hashCode(isPoweredOn);
        result = 31 * result + currentChannel;
        result = 31 * result + volume;
        return result;
    }
}
