//package com.example.restservice.Model;
//
//import net.aksingh.owmjapis.api.APIException;
//import net.aksingh.owmjapis.core.OWM;
//import net.aksingh.owmjapis.model.CurrentWeather;
//import org.json.JSONException;
//
//
//public class Weather {
//
//        private OWM owm = new OWM("90f98eb09fdbdef267adf7d351598d14");
//
//
//        public double getCloudPercentage() throws JSONException, APIException {
//            CurrentWeather cwd = owm.currentWeatherByCityName("Gummersbach");
//            return cwd.getCloudData().getCloud();
//        }
//
//        public int getSunriseHour() throws APIException {
//            CurrentWeather cwd = owm.currentWeatherByCityName("Gummersbach");
//            return cwd.getSystemData().getSunriseDateTime().getHours();
//        }
//
//        public int getSunsetHour() throws APIException {
//            CurrentWeather cwd = owm.currentWeatherByCityName("Gummersbach");
//            return cwd.getSystemData().getSunsetDateTime().getHours();
//        }
//
//        public int getSunriseMinute() throws APIException{
//            CurrentWeather cwd = owm.currentWeatherByCityName("Gummersbach");
//            return cwd.getSystemData().getSunriseDateTime().getMinutes();
//        }
//
//        public int getSunsetMinute() throws APIException{
//            CurrentWeather cwd = owm.currentWeatherByCityName("Gummersbach");
//            return cwd.getSystemData().getSunsetDateTime().getMinutes();
//        }
//    }
//
//
