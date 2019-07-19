package com.kmj.sunrinton19;

public class ProblemModel {
    private String ImageUrl;
    private int star;
    private String name;
    private String subject;
    private String solveUrl;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSolveUrl() {
        return solveUrl;
    }

    public void setSolveUrl(String solveUrl) {
        this.solveUrl = solveUrl;
    }

    public ProblemModel(String imageUrl, int star, String name, String subject, String solveUrl) {
        ImageUrl = imageUrl;
        this.star = star;
        this.name = name;
        this.subject = subject;
        this.solveUrl = solveUrl;
    }
}
