package com.ilanangelesrodriguez;

public class GatoFav {
    String id;
    String imageId;
    String apikey = "live_12uBcrcKucmZQIPAZyBWWlMpacyah9xoh4vN32Sp6zZCyrhFDfUHWjtxtH5Uuarw";
    ImageGato image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ImageGato getImage() {
        return image;
    }

    public void setImage(ImageGato image) {
        this.image = image;
    }
}
