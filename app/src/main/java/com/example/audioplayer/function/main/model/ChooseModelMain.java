package com.example.audioplayer.function.main.model;

public class ChooseModelMain {
    private String nameFolder;
    private String sizeFolder;
    private int imageFolder;

    public ChooseModelMain(String nameFolder, String sizeFolder, int imageFolder) {
        this.nameFolder = nameFolder;
        this.sizeFolder = sizeFolder;
        this.imageFolder = imageFolder;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public void setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
    }

    public String getSizeFolder() {
        return sizeFolder;
    }

    public void setSizeFolder(String sizeFolder) {
        this.sizeFolder = sizeFolder;
    }

    public int getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(int imageFolder) {
        this.imageFolder = imageFolder;
    }
}
