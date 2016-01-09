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
// This is our Model Class and this class maps to the Ad table in the database
public class Ads {
    private int id;
    private String text;

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
}
