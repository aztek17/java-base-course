package homeworks.homework05;

import java.util.List;
import java.util.Objects;

public class Tv {
    private String brandName;
    private Integer screenSize;
    private Integer refreshRate;
    private boolean isPoweredOn;
    private Channel currentChannel;
    private static final String empty = "empty";
    private List<Channel> channels;

    public Tv(String brandName, int screenSize, List<Channel> channels) {
        setBrandName(brandName);
        setScreenSize(screenSize);
        setRefreshRate(60);
        setChannels(channels);
        pressPowerButton(false);
    }

    public Tv(String brandName, boolean isPoweredOn) {
        setBrandName(brandName);
        pressPowerButton(isPoweredOn);
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getBrandName() {
        return brandName;
    }

    public boolean isPoweredOn() {
        return isPoweredOn;
    }

    public void pressPowerButton(boolean poweredOn) {
        this.isPoweredOn = poweredOn;
        if (poweredOn) {
            System.out.println("Телевизор " + getBrandName() + " включен");
            changeCurrentChannel(showAvailableChannels().getFirst().getChannelNumber());
        } else {
            System.out.println("Телевизор " + getBrandName() + " выключен");
        }
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    public void changeCurrentChannel(int currentChannel) {
        for (int i = 0; i < showAvailableChannels().size(); i++) {
            if (channels.get(i).getChannelNumber() == currentChannel) {
                this.currentChannel = channels.get(i);
                System.out.println("Канал переключен. Текущий канал: " + getCurrentChannel());
                break;
            }
        }
        if (currentChannel != getCurrentChannel().getChannelNumber()) {
            System.out.println("\nКанал не найден");
        }
    }

    public List<Channel> showAvailableChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "brandName='" + brandName + '\'' +
                ", screenSize=" + screenSize +
                ", refreshRate=" + refreshRate +
                ", isPoweredOn=" + isPoweredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Tv tv = (Tv) o;
        return isPoweredOn == tv.isPoweredOn && Objects.equals(brandName, tv.brandName) && Objects.equals(screenSize, tv.screenSize) && Objects.equals(refreshRate, tv.refreshRate) && Objects.equals(currentChannel, tv.currentChannel) && Objects.equals(channels, tv.channels);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(brandName);
        result = 31 * result + Objects.hashCode(screenSize);
        result = 31 * result + Objects.hashCode(refreshRate);
        result = 31 * result + Boolean.hashCode(isPoweredOn);
        result = 31 * result + Objects.hashCode(currentChannel);
        result = 31 * result + Objects.hashCode(channels);
        return result;
    }
}
