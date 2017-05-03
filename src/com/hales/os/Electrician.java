package com.hales.os;

import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.util.MathUtil;

/**
 * Created by chales on 4/30/2017.
 */
public class Electrician extends Place {
    private String  vicinity;
    String geom, icon, id, name, openhours, photos, place_id, price_leve, scope, types;
    public double lat, lng;
    public boolean isOpen;
    public double distance;
    public double lat2, lng2;
    public  String reference;
    public String raiting;

    public Electrician(String vraiting,String geomv, String iconv, String idv, String namev, String place_idv, String reverencev, String scopev, String typesv,String vicinityv, double la, double lo) {
        super();
        geom = geomv;
        icon = iconv;
        id = idv;
        name = namev;
        place_id = place_idv;
        reference = reverencev;
        scope = scopev;
        types = typesv;
        vicinity = vicinityv;
        raiting= vraiting;

        makeGeo(geom);
        //  amIOpen(openhours);
        lat2 = la;
        lng2 = lo;
        distance = distance2(lat, lng, lat2, lng2, 'M');//111;//distanc(lat,lng,lat2,lng2,0,0);
        distance = MathUtil.round(distance * 100.0) / 100.0;
        //distance= Math.round(a * 100.0) / 100.0;
        //  System.out.println(distance2(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
        //  System.out.println(distance2(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
        //  System.out.println(distance2(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");

    }


    private double distance2(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = MathUtil.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    /*

        public  double distanc(double lat1, double lat2, double lon1,
                                      double lon2, double el1, double el2) {

            final int R = 6371; // Radius of the earth

            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c * 1000; // convert to meters

            double height = el1 - el2;

            distance = Math.pow(distance, 2) + Math.pow(height, 2);

            return Math.sqrt(distance);
        }
        */
    public void amIOpen(String open) {
        System.out.println("THISI S OPEN");
        try {
            JSONObject jsnopen = new JSONObject(open);
            System.out.println("TEST");
            // System.out.println("THIS IS A JSON open now"+jsnopen.get("open_now"));
            isOpen = (boolean) jsnopen.get("open_now");
            System.out.println(name + " is open =" + isOpen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(open);

    }

    public void makeGeo(String g) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            JSONObject jsn = new JSONObject(g);
            System.out.println("THIS IS A JSON TESTOBJEC" + jsn.toString());
            System.out.println("THIS IS A JSON TESTOBJEC" + jsn.get("location"));
            JSONObject location = new JSONObject(jsn.get("location") + "");
            System.out.println("JSON OBJECT Location" + location.toString());
            lng = location.getDouble("lng");
            lat = location.getDouble("lat");
            System.out.println("THE LAT IS" + lat);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void print() {
        System.out.println("****Start");
        System.out.println("Geo=" + geom);
        System.out.println("icon=" + icon);
        System.out.println("id=" + id);
        System.out.println("name=" + name);
        System.out.println("openours=" + openhours);
        System.out.println("photo=" + photos);
        System.out.println("placeID=" + place_id);
        System.out.println("price=" + price_leve);
        System.out.println("raiting=" + raiting);
        System.out.println("refrence=" + reference);
        System.out.println("scope=" + scope);
        System.out.println("types=" + types);
        System.out.println("****End");


    }
}
