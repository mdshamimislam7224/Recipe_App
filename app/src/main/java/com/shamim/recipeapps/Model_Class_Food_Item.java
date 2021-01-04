package com.shamim.recipeapps;

import android.graphics.Bitmap;

public class Model_Class_Food_Item {
    int id;
    String imagename;
    Bitmap image;
    private String foodtitle;
    private String subtitle;
    private String equipment;
    private String processing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }




    public Model_Class_Food_Item(int id, String imagename, Bitmap image, String foodtitle, String subtitle, String equipment, String processing) {
        this.id = id;
        this.imagename = imagename;
        this.image = image;
        this.foodtitle = foodtitle;
        this.subtitle = subtitle;
        this.equipment = equipment;
        this.processing = processing;
    }









}



