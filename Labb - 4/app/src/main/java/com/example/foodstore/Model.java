package com.example.foodstore;

import java.io.Serializable;

public class Model implements Serializable {

        private String name;
        private String price;
        private String description;
        private String company;

        //konstruktor
        public Model(String name, String price, String description, String company){
            this.name = name;
            this.price = price;
            this.description = description;
            this.company = company;
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getPrice(){
        return price;
        }

        public void setPrice(String price){
        this.price = price;
        }

        public String getDescription(){
        return description;
    }

        public void setDescription(String description){
        this.description = description;
    }

       public String getCompany(){
        return company;
    }

       public void setCompany(String company){
        this.company = company;
    }


}
