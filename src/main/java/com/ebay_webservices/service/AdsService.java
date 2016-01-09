package com.ebay_webservices.service;

import com.ebay_webservices.database.Database;
import com.ebay_webservices.model.Ads;
import com.ebay_webservices.model.AdsServed;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imran Bijapuri
 */
//This is the lone service class and this performs all the communication with the backend database.
public class AdsService {

    private Database database = new Database();

    public void addAds(String text) {
        try {
            Connection conn = database.getConnection();
            CallableStatement stmt = conn.prepareCall("{call insert_ad(?)}");
            stmt.setString(1, text);
            stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Ads getadbyId(int id) {
        Ads ads = new Ads();
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from ad where ad_id = " + id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ads.setId(rs.getInt("ad_id"));
                ads.setText(rs.getString("ad_text"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ads;
    }

    public void deladbyId(int id) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("delete from ad where ad_id = " + id);
            stmt.executeUpdate();
            stmt = conn.prepareStatement("delete from adserving where ad_id = " + id);
            stmt.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updatetrackclick(int adId) {
        try {
            Connection conn = database.getConnection();
            CallableStatement stmt = conn.prepareCall("{call updatetrackclick(?)}");
            stmt.setInt(1, adId);
            stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trackimpression(int adId) {
        try {
            Connection conn = database.getConnection();
            CallableStatement stmt = conn.prepareCall("{call updatetrackimpression(?)}");
            stmt.setInt(1, adId);
            stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AdsServed getAd() {
        AdsServed adsserved = new AdsServed();
        try {
            Connection conn = database.getConnection();
            CallableStatement stmt = conn.prepareCall("{call getAd()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                adsserved.setCTR(rs.getFloat("CTR"));
                adsserved.setClicks(rs.getInt("clicks"));
                adsserved.setId(rs.getInt("ad_id"));
                adsserved.setImpressions(rs.getInt("impressions"));
                adsserved.setText(rs.getString("ad_text"));
            }

        } catch (Exception ex) {
            Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adsserved;
    }
}
