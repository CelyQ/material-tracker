package materials.tracker.domain;

public class Material {
    private int id;
    private String name;
    private int pagesProgress;
    private int pagesTotal;
    private String imageURL;

    public Material() {
    }

    public Material(String name, int pagesProgress, int pagesTotal, String imageURL) {
        this.id = id;
        this.name = name;
        this.pagesProgress = pagesProgress;
        this.pagesTotal = pagesTotal;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPagesProgress() {
        return pagesProgress;
    }

    public void setPagesProgress(int pagesProgress) {
        this.pagesProgress = pagesProgress;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(int pagesTotal) {
        this.pagesTotal = pagesTotal;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
