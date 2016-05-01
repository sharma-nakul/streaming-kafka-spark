package kafka.model;

/**
 * Created by Naks on 26-Apr-16.
 * Model class for sensor data *
 */
public class PublicWeatherNC {
    private String county;
    private String city;
    private String year;
    private int lowestTemp;
    private int highestTemp;
    private int warmestTemp;
    private int coldestTemp;
    private double averageMinimumTemp;
    private double averageMaximumTemp;
    private double meanTemperature;
    private double totalPrecipitation;
    private double totalSnowfall;
    private double maxLastDayPrecipitation;
    private double maxLastDaySnowfall;

    public PublicWeatherNC() {
    }

    public PublicWeatherNC(String county, String city, String year, int lowestTemp, int highestTemp, int warmestTemp, int coldestTemp, double averageMinimumTemp, double averageMaximumTemp, double meanTemperature, double totalPrecipitation, double totalSnowfall, double maxLastDayPrecipitation, double maxLastDaySnowfall) {
        this.county=county;
        this.city = city;
        this.year = year;
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
        this.warmestTemp = warmestTemp;
        this.coldestTemp = coldestTemp;
        this.averageMinimumTemp = averageMinimumTemp;
        this.averageMaximumTemp = averageMaximumTemp;
        this.meanTemperature = meanTemperature;
        this.totalPrecipitation = totalPrecipitation;
        this.totalSnowfall = totalSnowfall;
        this.maxLastDayPrecipitation = maxLastDayPrecipitation;
        this.maxLastDaySnowfall = maxLastDaySnowfall;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getLowestTemp() {
        return lowestTemp;
    }

    public void setLowestTemp(int lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    public int getHighestTemp() {
        return highestTemp;
    }

    public void setHighestTemp(int highestTemp) {
        this.highestTemp = highestTemp;
    }

    public int getWarmestTemp() {
        return warmestTemp;
    }

    public void setWarmestTemp(int warmestTemp) {
        this.warmestTemp = warmestTemp;
    }

    public int getColdestTemp() {
        return coldestTemp;
    }

    public void setColdestTemp(int coldestTemp) {
        this.coldestTemp = coldestTemp;
    }

    public double getAverageMinimumTemp() {
        return averageMinimumTemp;
    }

    public void setAverageMinimumTemp(double averageMinimumTemp) {
        this.averageMinimumTemp = averageMinimumTemp;
    }

    public double getAverageMaximumTemp() {
        return averageMaximumTemp;
    }

    public void setAverageMaximumTemp(double averageMaximumTemp) {
        this.averageMaximumTemp = averageMaximumTemp;
    }

    public double getMeanTemperature() {
        return meanTemperature;
    }

    public void setMeanTemperature(double meanTemperature) {
        this.meanTemperature = meanTemperature;
    }

    public double getTotalPrecipitation() {
        return totalPrecipitation;
    }

    public void setTotalPrecipitation(double totalPrecipitation) {
        this.totalPrecipitation = totalPrecipitation;
    }

    public double getTotalSnowfall() {
        return totalSnowfall;
    }

    public void setTotalSnowfall(double totalSnowfall) {
        this.totalSnowfall = totalSnowfall;
    }

    public double getMaxLastDayPrecipitation() {
        return maxLastDayPrecipitation;
    }

    public void setMaxLastDayPrecipitation(double maxLastDayPrecipitation) {
        this.maxLastDayPrecipitation = maxLastDayPrecipitation;
    }

    public double getMaxLastDaySnowfall() {
        return maxLastDaySnowfall;
    }

    public void setMaxLastDaySnowfall(double maxLastDaySnowfall) {
        this.maxLastDaySnowfall = maxLastDaySnowfall;
    }
}
