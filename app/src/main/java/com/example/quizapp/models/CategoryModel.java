package com.example.quizapp.models;

public class CategoryModel {

    String caterogyId, caterogyName, caterogyImage;

    public CategoryModel() {
    }

    public CategoryModel(String caterogyId, String caterogyName, String caterogyImage) {
        this.caterogyId = caterogyId;
        this.caterogyName = caterogyName;
        this.caterogyImage = caterogyImage;
    }

    public String getCaterogyId() {
        return caterogyId;
    }

    public void setCaterogyId(String caterogyId) {
        this.caterogyId = caterogyId;
    }

    public String getCaterogyName() {
        return caterogyName;
    }

    public void setCaterogyName(String caterogyName) {
        this.caterogyName = caterogyName;
    }

    public String getCaterogyImage() {
        return caterogyImage;
    }

    public void setCaterogyImage(String caterogyImage) {
        this.caterogyImage = caterogyImage;
    }
}
