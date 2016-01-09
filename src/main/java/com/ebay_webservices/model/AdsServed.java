/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebay_webservices.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Imran Bijapuri
 */
@XmlRootElement
// This is our Model Class and this class maps to the Adserving table in the database
public class AdsServed {
    private int id;
    private String text;
    private int impressions;
    private int clicks;
    private float CTR;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the impressions
     */
    public int getImpressions() {
        return impressions;
    }

    /**
     * @param impressions the impressions to set
     */
    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    /**
     * @return the clicks
     */
    public int getClicks() {
        return clicks;
    }

    /**
     * @param clicks the clicks to set
     */
    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    /**
     * @return the CTR
     */
    public float getCTR() {
        return CTR;
    }

    /**
     * @param CTR the CTR to set
     */
    public void setCTR(float CTR) {
        this.CTR = CTR;
    }
    
}
