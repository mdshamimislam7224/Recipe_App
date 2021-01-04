package com.shamim.recipeapps;

public class Model_class_Insert_data {
   private String foodtitle;
   private String equipment;
    private String processing;
    private String subtitle;

    public Model_class_Insert_data( String subtitle) {
        this.subtitle = subtitle;
    }






    public Model_class_Insert_data(String foodtitle, String equipment, String processing, String subtitle) {
        this.foodtitle = foodtitle;
        this.equipment = equipment;
        this.processing = processing;
        this.subtitle = subtitle;
    }




    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }





}
