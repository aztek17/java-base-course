package homeworks.homework05;

import java.util.Objects;
import java.util.Random;

public class Channel {
    private String channelName;
    private int channelNumber;
    private TvShow tvShow;

    public Channel(String channelName, String tvShow) {
        setChannelName(channelName);
        setChannelNumber(generateRandomChannelNumber());
        setTvShow(new TvShow(tvShow));
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    private int generateRandomChannelNumber() {
        int min = 1;
        int max = 100;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;
        return channelNumber == channel.channelNumber && Objects.equals(channelName, channel.channelName) && Objects.equals(tvShow, channel.tvShow);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(channelName);
        result = 31 * result + channelNumber;
        result = 31 * result + Objects.hashCode(tvShow);
        return result;
    }

    @Override
    public String toString() {
        return "\n-" + channelName +
                "\nНомер канала - " + channelNumber +
                tvShow;
    }
}
