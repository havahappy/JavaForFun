package de.example.jackson.auto;


public class Main{
    private Number grnd_level;
    private Number humidity;
    private Number pressure;
    private Number sea_level;
    private Number temp;
    private Number temp_max;
    private Number temp_min;

    public Number getGrnd_level(){
        return this.grnd_level;
    }
    public void setGrnd_level(final Number grnd_level){
        this.grnd_level = grnd_level;
    }
    public Number getHumidity(){
        return this.humidity;
    }
    public void setHumidity(final Number humidity){
        this.humidity = humidity;
    }
    public Number getPressure(){
        return this.pressure;
    }
    public void setPressure(final Number pressure){
        this.pressure = pressure;
    }
    public Number getSea_level(){
        return this.sea_level;
    }
    public void setSea_level(final Number sea_level){
        this.sea_level = sea_level;
    }
    public Number getTemp(){
        return this.temp;
    }
    public void setTemp(final Number temp){
        this.temp = temp;
    }
    public Number getTemp_max(){
        return this.temp_max;
    }
    public void setTemp_max(final Number temp_max){
        this.temp_max = temp_max;
    }
    public Number getTemp_min(){
        return this.temp_min;
    }
    public void setTemp_min(final Number temp_min){
        this.temp_min = temp_min;
    }
}
